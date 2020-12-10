/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

public class ListOfSmlProfObjHeaderEntry extends SequenceOf {
    private SmlProfObjHeaderEntry[] headerEntries;

    public ListOfSmlProfObjHeaderEntry() {
    }

    public ListOfSmlProfObjHeaderEntry(SmlProfObjHeaderEntry[] headerEntries) {
        this.headerEntries = headerEntries;
        seqArray(headerEntries);
        setSelected(true);
    }

    @Override
    protected void createElements(int length) {
        headerEntries = new SmlProfObjHeaderEntry[length];
        for (int i = 0; i < length; i++) {
            headerEntries[i] = new SmlProfObjHeaderEntry();
        }
        seqArray(headerEntries);
    }

    public SmlProfObjHeaderEntry[] getHeaderEntries() {
        return headerEntries;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("List_of_SML_ProfObjHeaderEntry{\n");
        for (SmlProfObjHeaderEntry entry : headerEntries) {
            sb.append("  entry:          " + entry.toString() + "\n");
        }
        sb.append("}\n");

        return sb.toString();
    }
}
