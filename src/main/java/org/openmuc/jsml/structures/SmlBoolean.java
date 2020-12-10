/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SmlBoolean extends ASNObject {

    private boolean val;

    public SmlBoolean() {
    }

    public SmlBoolean(boolean b) {
        val = b;
        setSelected(true);
    }

    @Override
    public void encode(DataOutputStream os) throws IOException {
        if (isOptional() && !isSelected()) {
            os.writeByte(0x01);
            return;
        }
        os.writeByte(0x42);
        if (val) {
            os.writeByte(0x01);
        }
        else {
            os.writeByte(0x00);
        }
    }

    @Override
    public boolean decode(DataInputStream is) throws IOException {
        int typeLengthField = is.readByte();
        if (isOptional() && (typeLengthField == 0x01)) {
            setSelected(false);
            return true;
        }
        if (typeLengthField != 0x42) {
            return false;
        }
        if ((is.readByte() & 0xff) == 0x00) {
            val = false;
        }
        else {
            val = true;
        }
        return true;
    }

    public boolean getVal() {
        return val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

}
