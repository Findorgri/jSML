/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

import java.io.DataOutputStream;
import java.io.IOException;

public abstract class ListOf extends ASNObject {

    private ASNObject[] seqArray;

    @Override
    public void encode(DataOutputStream os) throws IOException {

        if (isOptional() && !isSelected()) {
            os.writeByte(0x01);
            return;
        }

        if (seqArray == null || seqArray.length < 1) {
            throw new IOException("ListOf contains no elements");
        }

        int numTlField = 1;

        while ((Math.pow(2, 4d * numTlField) - 1) < seqArray.length) {
            numTlField++;
        }

        for (int i = numTlField; i > 0; i--) {
            int firstFourBits;
            if (i < numTlField) {
                if (i > 1) {
                    firstFourBits = 0x80;
                }
                else {
                    firstFourBits = 0x00;
                }
            }
            else {
                if (numTlField > 1) {
                    firstFourBits = 0xf0;
                }
                else {
                    firstFourBits = 0x70;
                }
            }
            os.writeByte((firstFourBits & 0xff) | (((seqArray.length) >> ((i - 1) * 4)) & 0xf));
        }

        for (ASNObject element : seqArray) {
            element.encode(os);
        }
    }

    public ASNObject[] seqArray() {
        return seqArray;
    }

    protected void seqArray(ASNObject... seqArray) {
        this.seqArray = seqArray;
    }

}
