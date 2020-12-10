/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

public class ListOfSmlTree extends SequenceOf {

    private SmlTree[] treeEntry;

    public ListOfSmlTree() {
    }

    public ListOfSmlTree(SmlTree[] treeEntry) {
        this.treeEntry = treeEntry;
        seqArray(treeEntry);
        setSelected(true);
    }

    @Override
    protected void createElements(int length) {
        treeEntry = new SmlTree[length];
        for (int i = 0; i < length; i++) {
            treeEntry[i] = new SmlTree();
        }
        seqArray(treeEntry);
    }

    public SmlTree[] getTreeEntry() {
        return treeEntry;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        if (treeEntry != null) {
            if (treeEntry.length > 0) {
                for (SmlTree entry : treeEntry) {
                    sb.append(entry.toString() + " ");
                }
            }
            else {
                sb.append("not set");
            }
        }
        else {
            sb.append("not set");
        }

        return sb.toString();

    }
}
