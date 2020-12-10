/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

import org.openmuc.jsml.EUnit;

public class SmlUnit extends Unsigned8 {

    public SmlUnit() {
    }

    /**
     * @param val
     *            number of unit (e.g. IEC 62056-62)
     */
    public SmlUnit(Unsigned8 val) {
        super(val.getVal());
        EUnit.from(super.getVal());
    }

    @Override
    public String toString() {
        return EUnit.from(super.getVal()).toString();
    }

}
