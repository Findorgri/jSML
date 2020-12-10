/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

public class SmlTreePath extends SequenceOf {

    private OctetString[] pathEntry;

    public SmlTreePath() {
    }

    public SmlTreePath(OctetString[] pathEntry) {
        this.pathEntry = pathEntry;
        seqArray(pathEntry);
        setSelected(true);
    }

    public OctetString[] getPathEntry() {
        return pathEntry;
    }

    @Override
    protected void createElements(int length) {
        pathEntry = new OctetString[length];
        for (int i = 0; i < length; i++) {
            pathEntry[i] = new OctetString();
        }
        seqArray(pathEntry);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (OctetString entry : pathEntry) {
            sb.append(entry.toString() + " ");
        }

        return sb.toString().trim();
    }
}
