/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.openmuc.jsml.transport.Crc16;

public class SmlMessage extends Sequence {

    private OctetString transactionId;
    private Unsigned8 groupNo;
    private Unsigned8 abortOnError;
    private SmlMessageBody messageBody;
    private Unsigned16 crc16;
    private EndOfSmlMessage endOfSmlMsg;

    public SmlMessage() {
    }

    public SmlMessage(OctetString transactionId, Unsigned8 groupNo, Unsigned8 abortOnError,
            SmlMessageBody messageBody) {
        this.transactionId = transactionId;
        this.groupNo = groupNo;
        this.abortOnError = abortOnError;
        this.messageBody = messageBody;
        this.crc16 = new Unsigned16();
        this.endOfSmlMsg = new EndOfSmlMessage();
        this.seqArray(new ASNObject[] { transactionId, groupNo, abortOnError, messageBody, crc16, endOfSmlMsg });
        this.setSelected(true);
    }

    public void setTransactionId(OctetString transactionId) {
        this.transactionId = transactionId;
    }

    public void setGroupNo(Unsigned8 groupNo) {
        this.groupNo = groupNo;
    }

    public void setAbortOnError(Unsigned8 abortOnError) {
        this.abortOnError = abortOnError;
    }

    public OctetString getTransactionId() {
        return transactionId;
    }

    public Unsigned8 getGroupNo() {
        return groupNo;
    }

    public Unsigned8 getAbortOnError() {
        return abortOnError;
    }

    public SmlMessageBody getMessageBody() {
        return messageBody;
    }

    @Override
    public void encode(DataOutputStream os) throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream(50);
        DataOutputStream os2 = new DataOutputStream(bs);

        super.encode(os2);

        byte[] bytes = bs.toByteArray();

        // -4 = 3 Bytes for Unsigned16 (crc) and 1 byte for EndOfSMLMessage
        int crc = Crc16.check(bytes, bytes.length - 4);
        crc16 = new Unsigned16(crc);

        // write crc16 to byte-array (bytes.lenght-1 is the
        // EndOfSMLMessage-byte)
        bytes[bytes.length - 3] = (byte) ((crc & 0xFF00) >> 8);
        bytes[bytes.length - 2] = (byte) ((crc & 0x00FF));

        os.write(bytes);
    }

    public boolean decodeAndCheck(DataInputStream is) throws IOException {
        int size = is.available();
        is.mark(size + 1);
        if (super.decode(is)) {
            int rest = is.available();

            int crc = crc16.getVal();

            // messageEndingSize: 3 or 2 bytes (TL-Field + Data) of crc16 and 1
            // byte of
            // endOfSmlMessage
            int messageEndingSize = 4;
            if (crc <= 0xff) {
                messageEndingSize = 3;
            }

            int len = size - rest - messageEndingSize;
            byte[] message = new byte[len];
            is.reset();
            is.readFully(message);
            long skipLength = is.skip(messageEndingSize);
            if (messageEndingSize != skipLength) {
                // TODO handle this case.
            }

            int crcmessage = Crc16.check(message);

            if (crc != crcmessage) {
                // crc might have been encoded with 3 instead of 2 bytes even
                // though it is < 0xff
                return messageEndingSize == 3 && crc == Crc16.check(message, message.length - 1);
            }

            return true;
        }
        return false;
    }

    @Override
    protected void createElements() {
        transactionId = new OctetString();
        groupNo = new Unsigned8();
        abortOnError = new Unsigned8();
        messageBody = new SmlMessageBody();
        crc16 = new Unsigned16();
        endOfSmlMsg = new EndOfSmlMessage();
        seqArray(new ASNObject[] { transactionId, groupNo, abortOnError, messageBody, crc16, endOfSmlMsg });
    }

}
