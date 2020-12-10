/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures.responses;

import org.openmuc.jsml.structures.OctetString;
import org.openmuc.jsml.structures.Sequence;
import org.openmuc.jsml.structures.SmlTree;
import org.openmuc.jsml.structures.SmlTreePath;

public class SmlGetProcParameterRes extends Sequence {

    protected OctetString serverId;
    protected SmlTreePath parameterTreePath;
    protected SmlTree parameterTree;

    public SmlGetProcParameterRes() {
    }

    public SmlGetProcParameterRes(OctetString serverId, SmlTreePath parameterTreePath, SmlTree parameterTree) {

        if (serverId == null) {
            throw new IllegalArgumentException(
                    "SML_GetProcParameterRes: serverId is not optional and must not be null!");
        }
        if (parameterTreePath == null) {
            throw new IllegalArgumentException(
                    "SML_GetProcParameterRes: parameterTreePath is not optional and must not be null!");
        }
        if (parameterTree == null) {
            throw new IllegalArgumentException(
                    "SML_GetProcParameterRes: parameterTree is not optional and must not be null!");
        }

        this.serverId = serverId;
        this.parameterTreePath = parameterTreePath;
        this.parameterTree = parameterTree;

        setOptionalAndSeq();
        setSelected(true);
    }

    public OctetString getServerId() {
        return serverId;
    }

    public SmlTreePath getParameterTreePath() {
        return parameterTreePath;
    }

    public SmlTree getParameterTree() {
        return parameterTree;
    }

    public void setOptionalAndSeq() {
        seqArray(serverId, parameterTreePath, parameterTree);
    }

    @Override
    protected void createElements() {
        serverId = new OctetString();
        parameterTreePath = new SmlTreePath();
        parameterTree = new SmlTree();
        setOptionalAndSeq();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SML_GetProcParameterRes{\n");
        sb.append("  serverId:          " + serverId.toString() + "\n");
        sb.append("  parameterTreePath: " + parameterTreePath.toString() + "\n");
        sb.append("  parameterTree:     " + parameterTree.toString() + "\n");
        sb.append("}\n");
        return sb.toString();
    }
}
