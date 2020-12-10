/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

import java.io.DataOutputStream;
import java.io.IOException;

public class Integer32 extends IntegerGeneric {

    static final int BYTES = 4;

    public Integer32() {
        bytes = BYTES;
    }

    public Integer32(int i) {
        bytes = BYTES;
        val = i;
        setSelected(true);
    }

    public int getVal() {
        return (int) val;
    }

    @Override
    public void encode(DataOutputStream os) throws IOException {
        if (isOptional() && !isSelected()) {
            os.writeByte(0x01);
            return;
        }
        os.writeByte(0x55);
        os.writeInt((int) val);
    }

    @Override
    public String toString() {
        return String.valueOf((int) val);
    }
}
