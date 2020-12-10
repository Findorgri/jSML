/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures.requests;

import org.openmuc.jsml.structures.OctetString;
import org.openmuc.jsml.structures.Sequence;

public class SmlPublicCloseReq extends Sequence {
    protected OctetString globalSignature;

    public OctetString getGlobalSignature() {
        return globalSignature;
    }

    public SmlPublicCloseReq() {
    }

    public SmlPublicCloseReq(OctetString globalSignature) {
        if (globalSignature != null) {
            this.globalSignature = globalSignature;
        }
        else {
            this.globalSignature = new OctetString();
        }

        setOptionalAndSeq();

        setSelected(true);
    }

    public void setOptionalAndSeq() {
        globalSignature.setOptional();

        seqArray(globalSignature);

    }

    @Override
    protected void createElements() {
        globalSignature = new OctetString();

        setOptionalAndSeq();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sml_PublicCloseReq{\n");
        sb.append("  globalSignature:   " + globalSignature.toString() + "\n");
        sb.append("}\n");
        return sb.toString();
    }

}
