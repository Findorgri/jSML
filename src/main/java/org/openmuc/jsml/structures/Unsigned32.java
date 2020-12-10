/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Unsigned32 extends ASNObject {

    private long val;

    public Unsigned32() {
    }

    public Unsigned32(long val) {
        this.val = val;
        setSelected(true);
    }

    public int getVal() {
        return (int) val;
    }

    public long getLongValue() {
        return val;
    }

    @Override
    public void encode(DataOutputStream os) throws IOException {
        if (isOptional() && !isSelected()) {
            os.writeByte(0x01);
            return;
        }
        os.writeByte(0x65);
        byte[] array = ByteBuffer.allocate(4)
                .put((byte) ((val & 0xFF000000) >> 24))
                .put((byte) ((val & 0x00FF0000) >> 16))
                .put((byte) ((val & 0x0000FF00) >> 8))
                .put((byte) (val & 0x000000FF))
                .array();
        os.write(array);
    }

    @Override
    public boolean decode(DataInputStream is) throws IOException {
        int typeLengthField = is.readByte();
        if (isOptional() && (typeLengthField == 0x01)) {
            setSelected(false);
            return true;
        }
        if ((typeLengthField & 0x60) != 0x60) {
            return false;
        }
        // get length of unsigned with tl-field and subtract the tl-field
        int length = (typeLengthField & 0x0F) - 2;

        for (; length >= 0; length--) {
            val |= (long) is.readUnsignedByte() << (8 * length);
        }

        setSelected(true);
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(getLongValue());
    }

}
