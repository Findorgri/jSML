/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures.requests;

import org.openmuc.jsml.structures.OctetString;
import org.openmuc.jsml.structures.Sequence;
import org.openmuc.jsml.structures.SmlTree;
import org.openmuc.jsml.structures.SmlTreePath;

public class SmlSetProcParameterReq extends Sequence {

    protected OctetString serverId; // OPTIONAL
    protected OctetString username; // OPTIONAL
    protected OctetString password; // OPTIONAL
    protected SmlTreePath parameterTreePath;
    protected SmlTree parameterTree;

    public SmlSetProcParameterReq() {
    }

    public SmlSetProcParameterReq(OctetString serverId, OctetString username, OctetString password,
            SmlTreePath parameterTreePath, SmlTree parameterTree) {

        if (parameterTreePath == null) {
            throw new IllegalArgumentException(
                    "SML_SetProcParameterReq: parameterTreePath is not optional and must not be null!");
        }
        if (parameterTree == null) {
            throw new IllegalArgumentException(
                    "SML_SetProcParameterReq: parameterTree is not optional and must not be null!");
        }

        this.serverId = serverId;
        this.username = username;
        this.password = password;
        this.parameterTreePath = parameterTreePath;
        this.parameterTree = parameterTree;

        if (serverId == null) {
            this.serverId = new OctetString();
        }
        if (username == null) {
            this.username = new OctetString();
        }
        if (password == null) {
            this.password = new OctetString();
        }

        setOptionalAndSeq();
        setSelected(true);
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

    public SmlTreePath getParameterTreePath() {
        return parameterTreePath;
    }

    public SmlTree getParameterTree() {
        return parameterTree;
    }

    public void setOptionalAndSeq() {
        serverId.setOptional();
        username.setOptional();
        password.setOptional();

        seqArray(serverId, username, password, parameterTreePath, parameterTree);
    }

    @Override
    protected void createElements() {
        serverId = new OctetString();
        username = new OctetString();
        password = new OctetString();
        parameterTreePath = new SmlTreePath();
        parameterTree = new SmlTree();

        setOptionalAndSeq();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sml_SetProcParameterReq{\n");
        sb.append("  serverId:          " + serverId.toString() + "\n");
        sb.append("  username:          " + username.toString() + "\n");
        sb.append("  password:          " + password.toString() + "\n");
        sb.append("  parameterTreePath: " + parameterTreePath.toString() + "\n");
        sb.append("}\n");
        return sb.toString();
    }

}
