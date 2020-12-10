/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

public class SmlList extends SequenceOf {

    private SmlListEntry[] valListEntry;

    public SmlListEntry[] getValListEntry() {
        return valListEntry;
    }

    public SmlList() {
    }

    public SmlList(SmlListEntry[] valListEntry) {
        this.valListEntry = valListEntry;
        seqArray(valListEntry);
        setSelected(true);
    }

    @Override
    protected void createElements(int length) {
        valListEntry = new SmlListEntry[length];
        for (int i = 0; i < length; i++) {
            valListEntry[i] = new SmlListEntry();
        }
        seqArray(valListEntry);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SML_List{\n");
        for (SmlListEntry entry : valListEntry) {
            sb.append("         entry:           " + entry.toString() + "\n");
        }
        sb.append("}\n");
        return sb.toString();
    }

    public String toStringIndent(String level) {
        StringBuilder sb = new StringBuilder();
        sb.append("SML_List{\n");
        for (SmlListEntry entry : valListEntry) {
            sb.append(level + "entry:           " + entry.toStringIndent(level + "    ") + "\n");
        }

        String indent = "";
        if (level.length() >= 4) {
            indent = level.substring(0, level.length() - 4);

        }

        sb.append(indent + "}\n");

        return sb.toString();
    }

}
