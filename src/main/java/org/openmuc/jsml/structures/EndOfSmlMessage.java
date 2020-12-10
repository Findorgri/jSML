/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class EndOfSmlMessage extends ASNObject {

    @Override
    public void encode(DataOutputStream os) throws IOException {
        os.writeByte(0);
    }

    @Override
    public boolean decode(DataInputStream is) throws IOException {
        return is.readByte() == 0x00;
    }

}
