/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures.requests;

import org.openmuc.jsml.structures.OctetString;
import org.openmuc.jsml.structures.Sequence;
import org.openmuc.jsml.structures.Unsigned8;

public class SmlPublicOpenReq extends Sequence {

    protected OctetString codepage;
    protected OctetString clientId;
    protected OctetString reqFileId;
    protected OctetString serverId;
    protected OctetString username;
    protected OctetString password;
    protected Unsigned8 smlVersion;

    public SmlPublicOpenReq() {
    }

    public SmlPublicOpenReq(OctetString codepage, OctetString clientId, OctetString reqFileId, OctetString serverId,
            OctetString username, OctetString password, Unsigned8 smlVersion) {
        if (codepage != null) {
            this.codepage = codepage;
        }
        else {
            this.codepage = new OctetString();
        }

        this.clientId = clientId;
        this.reqFileId = reqFileId;

        if (serverId != null) {
            this.serverId = serverId;
        }
        else {
            this.serverId = new OctetString();
        }

        if (username != null) {
            this.username = username;
        }
        else {
            this.username = new OctetString();
        }

        if (password != null) {
            this.password = password;
        }
        else {
            this.password = new OctetString();
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

    public OctetString getUsername() {
        return username;
    }

    public OctetString getPassword() {
        return password;
    }

    public Unsigned8 getSmlVersion() {
        return smlVersion;
    }

    public void setOptionalAndSeq() {
        codepage.setOptional();
        serverId.setOptional();
        username.setOptional();
        password.setOptional();
        smlVersion.setOptional();

        seqArray(codepage, clientId, reqFileId, serverId, username, password, smlVersion);

    }

    @Override
    protected void createElements() {
        codepage = new OctetString();
        clientId = new OctetString();
        reqFileId = new OctetString();
        serverId = new OctetString();
        username = new OctetString();
        password = new OctetString();
        smlVersion = new Unsigned8();

        setOptionalAndSeq();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sml_PublicOpenReq{\n");
        sb.append("  codepage:          " + codepage.toString() + "\n");
        sb.append("  clientId:          " + clientId.toString() + "\n");
        sb.append("  reqFileId:         " + reqFileId.toString() + "\n");
        sb.append("  serverId:          " + serverId.toString() + "\n");
        sb.append("  username:          " + username.toString() + "\n");
        sb.append("  password:          " + password.toString() + "\n");
        sb.append("  smlVersion:        " + smlVersion.toString() + "\n");
        sb.append("}\n");
        return sb.toString();
    }

}
