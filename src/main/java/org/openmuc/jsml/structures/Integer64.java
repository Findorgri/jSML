/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

public class Integer64 extends IntegerGeneric {

    public Integer64() {
    }

    public Integer64(long i) {
        val = i;
        setSelected(true);
    }

    public long getVal() {
        return val;
    }

}
