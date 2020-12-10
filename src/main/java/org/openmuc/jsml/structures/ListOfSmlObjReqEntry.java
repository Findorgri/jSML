/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

public class ListOfSmlObjReqEntry extends SequenceOf {

    private OctetString[] objectListEntry;

    public ListOfSmlObjReqEntry() {
    }

    public ListOfSmlObjReqEntry(OctetString[] objectListEntry) {
        this.objectListEntry = objectListEntry;
        seqArray(objectListEntry);
        setSelected(true);
    }

    @Override
    protected void createElements(int length) {
        objectListEntry = new OctetString[length];
        for (int i = 0; i < length; i++) {
            objectListEntry[i] = new OctetString();
        }
        seqArray(objectListEntry);
    }

    public OctetString[] getObjectListEntry() {
        return objectListEntry;
    }
}
