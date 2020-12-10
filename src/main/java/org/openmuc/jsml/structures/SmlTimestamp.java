/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

public class SmlTimestamp extends Unsigned32 {

    public SmlTimestamp() {
    }

    public SmlTimestamp(Unsigned32 val) {
        super(val.getVal());
    }

}
