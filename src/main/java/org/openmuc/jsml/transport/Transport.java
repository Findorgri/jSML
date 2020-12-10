/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.transport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.openmuc.jsml.structures.SmlFile;
import org.openmuc.jsml.structures.SmlMessage;

public class Transport {

    /**
     * Timeout in milliseconds
     */
    private int timeout = 30000;

    /**
     * Sends SML messages to output stream
     * 
     * @param os
     *            output stream
     * @param smlPackage
     *            SML package
     * @throws IOException
     *             if exception occurs while writing to output stream
     */
    public void send(DataOutputStream os, byte[] smlPackage) throws IOException {

        // use ByteArrayOutputStream so that later we can use the toByteArray
        // member function
        ByteArrayOutputStream bs = new ByteArrayOutputStream(smlPackage.length + 30);
        DataOutputStream dataByteArrayOutputStream = new DataOutputStream(bs);

        // Write start bytes of SML-TL
        writeUnsignedInt(dataByteArrayOutputStream, 0x1b1b1b1bL);
        writeUnsignedInt(dataByteArrayOutputStream, 0x01010101L);

        // insert escape sequences inside the sml-package if necessary
        int numEscapeSeqFound = 0;
        for (int i = 0; i < smlPackage.length; i += 4) {

            // if there is less than 3 bytes left to write
            if (i >= smlPackage.length - 3) {
                for (int j = i; j < smlPackage.length; j++) {
                    dataByteArrayOutputStream.write(smlPackage[j]);
                }
                break;
            }
            if ((smlPackage[i] & 0xff) == 0x1b && (smlPackage[i + 1] & 0xff) == 0x1b
                    && (smlPackage[i + 2] & 0xff) == 0x1b && (smlPackage[i + 3] & 0xff) == 0x1b) {
                writeUnsignedInt(dataByteArrayOutputStream, 0x1b1b1b1bL);
                numEscapeSeqFound++;
            }
            for (int k = 0; k < 4; k++) {
                dataByteArrayOutputStream.writeByte(smlPackage[i + k]);
            }
        }
        int numStuffBits = 4 - ((smlPackage.length + numEscapeSeqFound * 4) % 4);
        if (numStuffBits == 4) {
            numStuffBits = 0;
        }

        for (int i = 0; i < numStuffBits; i++) {
            dataByteArrayOutputStream.writeByte(0x00);
        }

        // write last bytes
        writeUnsignedInt(dataByteArrayOutputStream, 0x1b1b1b1bL);
        dataByteArrayOutputStream.writeByte(0x1a);
        dataByteArrayOutputStream.writeByte(numStuffBits & 0x00ff);

        byte[] packet = bs.toByteArray();

        // calculate crc16 of the whole packet
        int crc16 = Crc16.check(packet);

        os.write(packet);
        os.writeShort(crc16 & 0xffff);
        os.flush();

        dataByteArrayOutputStream.close();
        bs.close();
    }

    /**
     * Reads SML file from input stream
     * 
     * @param is
     *            the input stream
     * @return the SML file read
     * @throws IOException
     *             if an error occurs reading the SML file
     */
    public SmlFile getSMLFile(DataInputStream is) throws IOException {
        MessageExtractor extractor = new MessageExtractor(is, timeout);
        return handleSMLStream(extractor.getSmlMessage());
    }

    private SmlFile handleSMLStream(byte[] smlPacket) throws IOException {
        try (DataInputStream is = new DataInputStream(new ByteArrayInputStream(smlPacket));) {
            SmlFile smlFile = new SmlFile();

            while (is.available() > 0) {

                SmlMessage message = new SmlMessage();

                if (!message.decode(is)) {
                    throw new IOException("Could not decode message");
                }

                smlFile.add(message);

            }
            return smlFile;
        }
    }

    private static void writeUnsignedInt(DataOutputStream os, long value) throws IOException {
        os.writeByte((int) ((value & 0xFF000000L) >> 24));
        os.writeByte((int) ((value & 0x00FF0000L) >> 16));
        os.writeByte((int) ((value & 0x0000FF00L) >> 8));
        os.writeByte((int) (value & 0x000000FFL));
    }

    /**
     * Sets timeout in milliseconds. Default is 30000 ms.
     * 
     * @param timeout
     *            the timeout in milliseconds
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

}
