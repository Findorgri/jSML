/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.transport;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Implements SML transport layer version 1.
 * <p>
 * Extracts the next SML File from a DataInputStream.
 * </p>
 */
public class MessageExtractor {

    private final DataInputStream inputStream;

    private final ByteBuffer smlMessageBuffer = ByteBuffer.allocate(100000);

    private byte[] smlMessage = null;

    private int crc = 0xffff;

    private final byte[] buffer = new byte[8];

    private int bufferEnd = 0;

    private final long timeoutInMillies;

    private boolean startSequenceFound = false;

    public MessageExtractor(DataInputStream inputStream, long timeoutInMillies) {
        this.inputStream = inputStream;
        this.timeoutInMillies = timeoutInMillies;
        smlMessageBuffer.rewind();
    }

    public byte[] getSmlMessage() throws IOException {
        waitForStartSequence();
        waitForStopSequence();
        return smlMessage;
    }

    private void waitForStartSequence() throws IOException {
        while (!startSequenceFound) {

            fillBufferWithTimeout();

            if (checkForStartSequence()) {
                // reset CRC
                crc = 0xffff;

                clearBuffer();

                startSequenceFound = true;
                return;
            }

            dropByte();
        }
    }

    private void waitForStopSequence() throws IOException {
        boolean stopSequenceFound = false;

        while (!stopSequenceFound) {
            fillBufferWithTimeout();

            if (!checkForEscapeSequence()) {
                incrementalCrc(dropByte());
                continue;
            }

            if (checkForEscapedEscapeSequence()) {
                for (int i = 4; i < 8; i++) {
                    incrementalCrc(buffer[i]);
                }

                bufferEnd = 4;
                clearBuffer();
            }
            else if (checkForStartSequence()) {
                handleStart();
            }
            else {
                stopSequenceFound = handleEnd();
            }
        }
    }

    private boolean handleEnd() throws IOException {
        // end sequence
        boolean stopSequenceFound = true;
        int numStuffBytes = buffer[5];

        for (int i = 0; i < 6; i++) {
            incrementalCrc(buffer[i]);
        }

        int receivedCRC = (0xff & (buffer[6])) * 0x100 + (0xff & buffer[7]);

        if ((buffer[4] != 0x1a) || (numStuffBytes > 3)) {
            throw new IOException("Termination sequence is wrong");
        }

        crc ^= 0xffff;
        crc = ((crc & 0xff) << 8) | ((crc & 0xff00) >> 8);

        if (receivedCRC != crc) {
            throw new IOException("wrong crc");
        }

        int bufferLength = smlMessageBuffer.position();

        smlMessage = new byte[bufferLength - numStuffBytes];
        smlMessageBuffer.rewind();
        smlMessageBuffer.get(smlMessage);
        return stopSequenceFound;
    }

    private void handleStart() {
        // if message starts at beginning
        // reset CRC
        crc = 0xffff;

        clearBuffer();

        startSequenceFound = true;
        smlMessageBuffer.rewind();
    }

    private void clearBuffer() {
        while (bufferEnd > 0) {
            incrementalCrc(dropByte());
        }
    }

    private byte dropByte() {
        byte droppedByte = buffer[0];

        for (int i = 0; i < (bufferEnd - 1); i++) {
            buffer[i] = buffer[i + 1];
        }

        bufferEnd--;

        if (startSequenceFound) {
            smlMessageBuffer.put(droppedByte);
        }

        return droppedByte;
    }

    private void incrementalCrc(byte msgByte) {
        int index = (crc ^ msgByte) & 0xff;
        crc = (crc >> 8) ^ Crc16.tableIndexAt(index);
    }

    private void fillBufferWithTimeout() throws IOException {
        long lastRead = System.currentTimeMillis();

        while (bufferEnd < 8) {

            if ((System.currentTimeMillis() - lastRead) > timeoutInMillies) {
                throw new IOException("Timeout");
            }

            if (inputStream.available() > 0) {
                buffer[bufferEnd] = inputStream.readByte();
                bufferEnd++;
                lastRead = System.currentTimeMillis();
            }
            else {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private boolean checkForEscapeSequence() {
        return checkBuffer(0, 4, 0x1b);
    }

    private boolean checkForEscapedEscapeSequence() {
        return checkBuffer(4, 8, 0x1b);
    }

    private boolean checkForStartSequence() {
        return checkForEscapeSequence() && checkBuffer(4, 8, 0x01);
    }

    private boolean checkBuffer(int start, int stop, int checkTag) {
        for (int i = start; i < stop; i++) {
            if (buffer[i] != checkTag) {
                return false;
            }
        }
        return true;
    }
}
