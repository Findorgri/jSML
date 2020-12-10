/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Unsigned64 extends ASNObject {

    private long val;

    public Unsigned64() {
    }

    public Unsigned64(long i) {
        setVal(i);
    }

    public long getVal() {
        return val;
    }

    public void setVal(long i) {
        val = i;
        setSelected(true);
    }

    @Override
    public void encode(DataOutputStream os) throws IOException {
        if (isOptional() && !isSelected()) {
            os.writeByte(0x01);
            return;
        }
        os.writeByte(0x69);
        os.writeLong(val);
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
        return String.valueOf(val);
    }

}
