/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

public class SmlStatus extends ImplicitChoice {

    public SmlStatus() {
    }

    public SmlStatus(ASNObject choice) {
        if (!(choice instanceof Unsigned8 || choice instanceof Unsigned16 || choice instanceof Unsigned32
                || choice instanceof Unsigned64)) {
            throw new IllegalArgumentException(
                    "SML_Status: Wrong ASNObject! " + choice.getClass().getName() + " is not allowed.");
        }

        setChoice(choice);
        setSelected(true);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SML_Status{\n");
        if (getChoice() != null) {
            sb.append("  choice:          " + getChoice().toString() + "\n");
        }
        else {
            sb.append("  choice:          not set \n");
        }

        sb.append("}\n");
        return sb.toString();
    }

    public String toStringIndent(String level) {
        StringBuilder sb = new StringBuilder();
        sb.append("SML_Status{\n");
        if (getChoice() != null) {
            sb.append(level + "choice:          " + getChoice().toString() + "\n");
        }
        else {
            sb.append(level + "choice:          not set \n");
        }

        String indent = "";
        if (level.length() >= 4) {
            indent = level.substring(0, level.length() - 4);
            sb.append(indent + "}");
        }
        else {
            sb.append(indent + "}\n");
        }
        return sb.toString();
    }
}
