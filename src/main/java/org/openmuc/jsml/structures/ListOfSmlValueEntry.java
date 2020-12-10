/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

public class ListOfSmlValueEntry extends SequenceOf {
    private SmlValueEntry[] valueListEntries;

    public ListOfSmlValueEntry() {
    }

    public ListOfSmlValueEntry(SmlValueEntry[] valueListEntries) {
        this.valueListEntries = valueListEntries;
        seqArray(valueListEntries);
        setSelected(true);
    }

    @Override
    protected void createElements(int length) {
        valueListEntries = new SmlValueEntry[length];
        for (int i = 0; i < length; i++) {
            valueListEntries[i] = new SmlValueEntry();
        }
        seqArray(valueListEntries);
    }

    public SmlValueEntry[] getvalueListEntries() {
        return valueListEntries;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("List_of_SML_ValueEntry{\n");
        for (SmlValueEntry entry : valueListEntries) {
            sb.append("  entry:          " + entry.toString() + "\n");
        }
        sb.append("}\n");

        return sb.toString();
    }
}
