package org.openmuc.jsml.structures;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Generic integer class
 */
abstract class IntegerGeneric extends ASNObject {

    static final int BYTES = 8;

    int bytes;

    long val;

    IntegerGeneric() {
        bytes = BYTES;
    }

    IntegerGeneric(long i) {
        bytes = BYTES;
        val = i;
        setSelected(true);
    }

    @Override
    public void encode(DataOutputStream os) throws IOException {
        if (isOptional() && !isSelected()) {
            os.writeByte(0x01);
            return;
        }
        os.writeByte(0x59);
        os.writeLong(val);
    }

    @Override
    public boolean decode(DataInputStream is) throws IOException {
        int typeLengthField = is.readByte();
        if (isOptional() && (typeLengthField == 0x01)) {
            setSelected(false);
            return true;
        }
        if ((typeLengthField & 0x50) != 0x50) {
            return false;
        }
        // get length of unsigned with tl-field and subtract the tl-field
        int length = (typeLengthField & 0x0F) - 1;
        val = 0;

        for (int j = length - 1; j >= 0; j--) {
            val |= (long) is.readUnsignedByte() << (8 * j);
        }
        if (((val >> (8 * (length - 1))) & 0x80) == 0x80) {
            for (int i = bytes; i > length; i--) {
                val |= 0xFF << (8 * (i - 1));
            }
        }
        setSelected(true);
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

}
