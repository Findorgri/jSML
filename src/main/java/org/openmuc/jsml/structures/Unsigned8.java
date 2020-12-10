/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Unsigned8 extends ASNObject {

    private int val;

    public int getVal() {
        return val;
    }

    public Unsigned8() {
    }

    public Unsigned8(int i) {
        val = i & 0xff;
        setSelected(true);
    }

    @Override
    public void encode(DataOutputStream os) throws IOException {
        if (isOptional() && !isSelected()) {
            os.writeByte(0x01);
            return;
        }
        os.writeByte(0x62);
        os.writeByte(val);
    }

    @Override
    public boolean decode(DataInputStream is) throws IOException {
        int typeLengthField = is.readByte();
        if (isOptional() && (typeLengthField == 0x01)) {
            setSelected(false);
            return true;
        }
        if (typeLengthField != 0x62) {
            return false;
        }
        val = is.readUnsignedByte();

        setSelected(true);

        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
