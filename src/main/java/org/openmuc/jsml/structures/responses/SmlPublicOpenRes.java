/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures.responses;

import org.openmuc.jsml.structures.OctetString;
import org.openmuc.jsml.structures.Sequence;
import org.openmuc.jsml.structures.SmlTime;
import org.openmuc.jsml.structures.Unsigned8;

public class SmlPublicOpenRes extends Sequence {

    protected OctetString codepage;
    protected OctetString clientId;
    protected OctetString reqFileId;
    protected OctetString serverId;
    protected SmlTime refTime;
    protected Unsigned8 smlVersion;

    public SmlPublicOpenRes() {
    }

    public SmlPublicOpenRes(OctetString codepage, OctetString clientId, OctetString reqFileId, OctetString serverId,
            SmlTime refTime, Unsigned8 smlVersion) {

        if (codepage != null) {
            this.codepage = codepage;
        }
        else {
            this.codepage = new OctetString();
        }

        if (clientId != null) {
            this.clientId = clientId;
        }
        else {
            this.clientId = new OctetString();
        }

        this.reqFileId = reqFileId;
        this.serverId = serverId;

        if (refTime != null) {
            this.refTime = refTime;
        }
        else {
            this.refTime = new SmlTime();
        }

        if (smlVersion != null) {
            this.smlVersion = smlVersion;
        }
        else {
            this.smlVersion = new Unsigned8();
        }

        setOptionalAndSeq();

        setSelected(true);
    }

    public OctetString getCodepage() {
        return codepage;
    }

    public OctetString getClientId() {
        return clientId;
    }

    public OctetString getReqFileId() {
        return reqFileId;
    }

    public OctetString getServerId() {
        return serverId;
    }

    public SmlTime getRefTime() {
        return refTime;
    }

    public Unsigned8 getSmlVersion() {
        return smlVersion;
    }

    public void setOptionalAndSeq() {
        codepage.setOptional();
        clientId.setOptional();
        refTime.setOptional();
        smlVersion.setOptional();

        seqArray(codepage, clientId, reqFileId, serverId, refTime, smlVersion);

    }

    @Override
    protected void createElements() {
        codepage = new OctetString();
        clientId = new OctetString();
        reqFileId = new OctetString();
        serverId = new OctetString();
        refTime = new SmlTime();
        smlVersion = new Unsigned8();

        setOptionalAndSeq();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SML_PublicOpenRes{\n");
        sb.append("  codepage:   " + codepage.toString() + "\n");
        sb.append("  clientId:   " + clientId.toString() + "\n");
        sb.append("  reqFileId:  " + reqFileId.toString() + "\n");
        sb.append("  serverId:   " + serverId.toString() + "\n");
        sb.append("  refTime:    " + refTime.toString() + "\n");
        sb.append("  smlVersion: " + smlVersion.toString() + "\n");
        sb.append("}\n");
        return sb.toString();
    }

}
