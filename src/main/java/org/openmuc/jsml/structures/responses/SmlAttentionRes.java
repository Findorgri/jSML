/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures.responses;

import org.openmuc.jsml.EObis;
import org.openmuc.jsml.structures.OctetString;
import org.openmuc.jsml.structures.Sequence;
import org.openmuc.jsml.structures.SmlTree;

public class SmlAttentionRes extends Sequence {

    OctetString serverId;
    OctetString attentionNo;
    OctetString attentionMsg; // OPTIONAL
    SmlTree attentionDetails; // OPTIONAL

    public SmlAttentionRes() {
    }

    public SmlAttentionRes(OctetString serverId, OctetString attentionNo, OctetString attentionMsg,
            SmlTree attentionDetails) {

        if (serverId == null) {
            throw new IllegalArgumentException("SML_AttentionRes: serverId is not optional and must not be null!");
        }
        if (attentionNo == null) {
            throw new IllegalArgumentException("SML_AttentionRes: attentionNo is not optional and must not be null!");
        }

        this.serverId = serverId;
        this.attentionNo = attentionNo;
        this.attentionMsg = attentionMsg;
        this.attentionDetails = attentionDetails;

        if (this.attentionMsg == null) {
            this.attentionMsg = new OctetString();
        }
        if (this.attentionDetails == null) {
            this.attentionDetails = new SmlTree();
        }

        setOptionalAndSeq();
        setSelected(true);
    }

    public OctetString getServerId() {
        return serverId;
    }

    public OctetString getAttentionNo() {
        return attentionNo;
    }

    public OctetString getAttentionMsg() {
        return attentionMsg;
    }

    public SmlTree getAttentionDetails() {
        return attentionDetails;
    }

    public void setOptionalAndSeq() {
        attentionMsg.setOptional();
        attentionDetails.setOptional();

        seqArray(serverId, attentionNo, attentionMsg, attentionDetails);
    }

    @Override
    protected void createElements() {
        serverId = new OctetString();
        attentionNo = new OctetString();
        attentionMsg = new OctetString();
        attentionDetails = new SmlTree();
        setOptionalAndSeq();
    }

    @Override
    public String toString() {
        return new StringBuilder().append("\nSML_AttentionRes{\n")
                .append("  attentionNo:           " + EObis.getInstance(attentionNo) + " " + attentionNo.toString()
                        + "\n")
                .append("  attentionMsg:          " + attentionMsg.toString() + "\n")
                .append("  attentionDetails:      " + attentionDetails.toString() + "\n")
                .append("}\n")
                .toString();
    }

}
