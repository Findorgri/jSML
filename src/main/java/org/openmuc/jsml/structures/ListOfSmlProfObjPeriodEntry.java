/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

public class ListOfSmlProfObjPeriodEntry extends SequenceOf {
    private SmlProfObjPeriodEntry[] profObjPeriodEntries;

    public ListOfSmlProfObjPeriodEntry() {
    }

    public ListOfSmlProfObjPeriodEntry(SmlProfObjPeriodEntry[] profObjPeriodEntries) {
        this.profObjPeriodEntries = profObjPeriodEntries;
        seqArray(profObjPeriodEntries);
        setSelected(true);
    }

    @Override
    protected void createElements(int length) {
        profObjPeriodEntries = new SmlProfObjPeriodEntry[length];
        for (int i = 0; i < length; i++) {
            profObjPeriodEntries[i] = new SmlProfObjPeriodEntry();
        }
        seqArray(profObjPeriodEntries);
    }

    public SmlProfObjPeriodEntry[] getProfObjPeriodEntries() {
        return profObjPeriodEntries;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("List_of_SML_ProfObjPeriodEntry{\n");
        for (SmlProfObjPeriodEntry entry : profObjPeriodEntries) {
            sb.append("  entry:          " + entry.toString() + "\n");
        }
        sb.append("}\n");

        return sb.toString();
    }
}
