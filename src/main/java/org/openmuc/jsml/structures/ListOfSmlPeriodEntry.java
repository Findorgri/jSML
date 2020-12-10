/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

public class ListOfSmlPeriodEntry extends SequenceOf {
    private SmlPeriodEntry[] periodEntries;

    public ListOfSmlPeriodEntry() {
    }

    public ListOfSmlPeriodEntry(SmlPeriodEntry[] periodEntries) {
        this.periodEntries = periodEntries;
        seqArray(periodEntries);
        setSelected(true);
    }

    @Override
    protected void createElements(int length) {
        periodEntries = new SmlPeriodEntry[length];
        for (int i = 0; i < length; i++) {
            periodEntries[i] = new SmlPeriodEntry();
        }
        seqArray(periodEntries);
    }

    public SmlPeriodEntry[] getPeriodEntries() {
        return periodEntries;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("List_of_SML_PeriodEntry{\n");
        for (SmlPeriodEntry entry : periodEntries) {
            sb.append("  entry:          " + entry.toString() + "\n");
        }
        sb.append("}\n");

        return sb.toString();
    }
}
