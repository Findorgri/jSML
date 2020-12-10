/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

import java.io.DataInputStream;
import java.io.IOException;

public abstract class Sequence extends ListOf {

    protected abstract void createElements();

    @Override
    public boolean decode(DataInputStream is) throws IOException {
        byte typeLength = is.readByte();
        if (isOptional() && (typeLength == 0x01)) {
            setSelected(false);
            return true;
        }
        createElements();
        setSelected(true);
        if (((typeLength & 0x70) >> 4) != 7) {
            return false;
        }
        int length = (typeLength & 0x0f);

        while ((typeLength & 0x80) == 0x80) {
            typeLength = is.readByte();
            if (((typeLength & 0x70) >> 4) != 0) {
                return false;
            }
            length = ((length & 0xffffffff) << 4) | (typeLength & 0x0f);
        }

        if (length != seqArray().length) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            if (!seqArray()[i].decode(is)) {
                return false;
            }
        }
        setSelected(true);
        return true;
    }

}
