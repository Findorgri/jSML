/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures.responses;

import org.openmuc.jsml.structures.OctetString;
import org.openmuc.jsml.structures.Sequence;
import org.openmuc.jsml.structures.SmlList;
import org.openmuc.jsml.structures.SmlSignature;
import org.openmuc.jsml.structures.SmlTime;

public class SmlGetListRes extends Sequence {

    protected OctetString clientId; // OPTIONAL,
    protected OctetString serverId;
    protected OctetString listName; // OPTIONAL,
    protected SmlTime actSensorTime; // OPTIONAL,
    protected SmlList valList;
    protected SmlSignature listSignature; // OPTIONAL,
    protected SmlTime actGatewayTime; // OPTIONAL

    public SmlGetListRes() {
    }

    public SmlGetListRes(OctetString clientId, OctetString serverId, OctetString listName, SmlTime actSensorTime,
            SmlList valList, SmlSignature listSignature, SmlTime actGatewayTime) {

        if (serverId == null) {
            throw new IllegalArgumentException("SmlGetListRes: serverId is not optional and must not be null!");
        }
        if (valList == null) {
            throw new IllegalArgumentException("SmlGetListRes: valList is not optional and must not be null!");
        }

        this.clientId = clientId;
        this.serverId = serverId;
        this.listName = listName;
        this.actSensorTime = actSensorTime;
        this.valList = valList;
        this.listSignature = listSignature;
        this.actGatewayTime = actGatewayTime;

        if (this.clientId == null) {
            this.clientId = new OctetString();
        }
        if (this.listName == null) {
            this.listName = new OctetString();
        }
        if (this.actSensorTime == null) {
            this.actSensorTime = new SmlTime();
        }
        if (this.listSignature == null) {
            this.listSignature = new SmlSignature();
        }
        if (this.actGatewayTime == null) {
            this.actGatewayTime = new SmlTime();
        }

        setOptionalAndSeq();
        setSelected(true);
    }

    public void setOptionalAndSeq() {
        clientId.setOptional();
        listName.setOptional();
        actSensorTime.setOptional();
        listSignature.setOptional();
        actGatewayTime.setOptional();

        seqArray(clientId, serverId, listName, actSensorTime, valList, listSignature, actGatewayTime);
    }

    public OctetString getClientId() {
        return clientId;
    }

    public OctetString getServerId() {
        return serverId;
    }

    public OctetString getListName() {
        return listName;
    }

    public SmlTime getActSensorTime() {
        return actSensorTime;
    }

    public SmlList getValList() {
        return valList;
    }

    public SmlSignature getListSignature() {
        return listSignature;
    }

    public SmlTime getActGatewayTime() {
        return actGatewayTime;
    }

    @Override
    protected void createElements() {
        clientId = new OctetString();
        serverId = new OctetString();
        listName = new OctetString();
        actSensorTime = new SmlTime();
        valList = new SmlList();
        listSignature = new SmlSignature();
        actGatewayTime = new SmlTime();
        setOptionalAndSeq();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SML_GetListRes{\n");
        sb.append("  serverId:        " + serverId.toString() + "\n");
        sb.append("  clientId:        " + clientId.toString() + "\n");
        sb.append("  listName:        " + listName.toString() + "\n");
        sb.append("  actSensorTime:   " + actSensorTime.toString() + "\n");
        sb.append("  valList:         " + valList.toString() + "\n");
        sb.append("  listSignature:   " + listSignature.toString() + "\n");
        sb.append("  actGatewayTime:  " + actGatewayTime.toString() + "\n");
        sb.append("}\n");
        return sb.toString();
    }

    public String toStringIndent(String level) {
        StringBuilder sb = new StringBuilder();
        sb.append("SML_GetListRes{\n");
        sb.append(level + "serverId:        " + serverId.toString() + "\n");
        sb.append(level + "clientId:        " + clientId.toString() + "\n");
        sb.append(level + "listName:        " + listName.toString() + "\n");
        sb.append(level + "actSensorTime:   " + actSensorTime.toString() + "\n");
        sb.append(level + "valList:         " + valList.toStringIndent(level + "    ") + "\n");
        sb.append(level + "listSignature:   " + listSignature.toString() + "\n");
        sb.append(level + "actGatewayTime:  " + actGatewayTime.toString() + "\n");
        sb.append("}\n");
        return sb.toString();
    }

}
