/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures.requests;

import org.openmuc.jsml.structures.OctetString;
import org.openmuc.jsml.structures.Sequence;

public class SmlGetListReq extends Sequence {

    protected OctetString clientId;
    protected OctetString serverId; // OPTIONAL
    protected OctetString username; // OPTIONAL
    protected OctetString password; // OPTIONAL
    protected OctetString listName; // OPTIONAL

    public SmlGetListReq() {
    }

    public SmlGetListReq(OctetString clientId, OctetString serverId, OctetString username, OctetString password,
            OctetString listName) {

        if (clientId == null) {
            throw new IllegalArgumentException("SML_GetListReq: clientId is not optional and must not be null!");
        }

        this.clientId = clientId;
        this.serverId = serverId;
        this.username = username;
        this.password = password;
        this.listName = listName;

        if (this.serverId == null) {
            this.serverId = new OctetString();
        }
        if (this.username == null) {
            this.username = new OctetString();
        }
        if (this.password == null) {
            this.password = new OctetString();
        }
        if (this.listName == null) {
            this.listName = new OctetString();
        }

        setOptionalAndSeq();
        setSelected(true);
    }

    public OctetString getClientId() {
        return clientId;
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

    public OctetString getListName() {
        return listName;
    }

    public void setOptionalAndSeq() {
        serverId.setOptional();
        username.setOptional();
        password.setOptional();
        listName.setOptional();

        seqArray(clientId, serverId, username, password, listName);
    }

    @Override
    protected void createElements() {

        clientId = new OctetString();
        serverId = new OctetString();
        username = new OctetString();
        password = new OctetString();
        listName = new OctetString();

        setOptionalAndSeq();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sml_GetListReq{\n");
        sb.append("  clientId:          " + clientId.toString() + "\n");
        sb.append("  serverId:          " + serverId.toString() + "\n");
        sb.append("  username:          " + username.toString() + "\n");
        sb.append("  password:          " + password.toString() + "\n");
        sb.append("  listName:          " + listName.toString() + "\n");
        sb.append("}\n");
        return sb.toString();
    }

}
