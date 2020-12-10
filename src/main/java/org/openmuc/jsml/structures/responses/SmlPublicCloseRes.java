/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures.responses;

import org.openmuc.jsml.structures.Sequence;
import org.openmuc.jsml.structures.SmlSignature;

public class SmlPublicCloseRes extends Sequence {

    protected SmlSignature globalSignature;

    public SmlPublicCloseRes() {
    }

    public SmlPublicCloseRes(SmlSignature globalSignature) {
        if (globalSignature != null) {
            this.globalSignature = globalSignature;
        }
        else {
            this.globalSignature = new SmlSignature();
        }

        setOptionalAndSeq();

        setSelected(true);
    }

    public SmlSignature getGlobalSignature() {
        return globalSignature;
    }

    public void setOptionalAndSeq() {
        globalSignature.setOptional();

        seqArray(globalSignature);

    }

    @Override
    protected void createElements() {
        globalSignature = new SmlSignature();

        setOptionalAndSeq();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SML_PublicCloseRes{\n");
        sb.append("  globalSignature:   " + globalSignature.toString() + "\n");
        sb.append("}\n");
        return sb.toString();
    }

}
