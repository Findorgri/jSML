/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures.requests;

import org.openmuc.jsml.structures.OctetString;
import org.openmuc.jsml.structures.Sequence;
import org.openmuc.jsml.structures.SmlTreePath;

public class SmlGetProcParameterReq extends Sequence {

    protected OctetString serverId; // OPTIONAL
    protected OctetString username; // OPTIONAL
    protected OctetString password; // OPTIONAL
    protected SmlTreePath parameterTreePath;
    protected OctetString attribute; // OPTIONAL

    public SmlGetProcParameterReq() {
    }

    /**
     * 
     * @param serverId
     *            OPTIONAL
     * @param username
     *            OPTIONAL
     * @param password
     *            OPTIONAL
     * @param parameterTreePath
     *            the parameter tree path
     * @param attribute
     *            OPTIONAL
     */
    public SmlGetProcParameterReq(OctetString serverId, OctetString username, OctetString password,
            SmlTreePath parameterTreePath, OctetString attribute) {

        if (parameterTreePath == null) {
            throw new IllegalArgumentException(
                    "SMLGetProcParameterReq: parameterTreePath is not optional and must not be null!");
        }

        this.serverId = serverId;
        this.username = username;
        this.password = password;
        this.parameterTreePath = parameterTreePath;
        this.attribute = attribute;

        if (this.serverId == null) {
            this.serverId = new OctetString();
        }
        if (this.username == null) {
            this.username = new OctetString();
        }
        if (this.password == null) {
            this.password = new OctetString();
        }
        if (this.attribute == null) {
            this.attribute = new OctetString();
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

    public OctetString getAttribute() {
        return attribute;
    }

    public void setOptionalAndSeq() {
        serverId.setOptional();
        username.setOptional();
        password.setOptional();
        attribute.setOptional();

        seqArray(serverId, username, password, parameterTreePath, attribute);
    }

    @Override
    protected void createElements() {
        serverId = new OctetString();
        username = new OctetString();
        password = new OctetString();
        parameterTreePath = new SmlTreePath();
        attribute = new OctetString();

        setOptionalAndSeq();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sml_GetProcParameterReq{\n");
        sb.append("  serverId:          " + serverId.toString() + "\n");
        sb.append("  username:          " + username.toString() + "\n");
        sb.append("  password:          " + password.toString() + "\n");
        sb.append("  parameterTreePath: " + parameterTreePath.toString() + "\n");
        sb.append("  attribute:         " + attribute.toString() + "\n");
        sb.append("}\n");
        return sb.toString();
    }

}
