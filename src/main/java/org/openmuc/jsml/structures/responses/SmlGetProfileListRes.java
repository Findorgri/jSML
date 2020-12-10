/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures.responses;

import org.openmuc.jsml.structures.ListOfSmlPeriodEntry;
import org.openmuc.jsml.structures.OctetString;
import org.openmuc.jsml.structures.Sequence;
import org.openmuc.jsml.structures.SmlSignature;
import org.openmuc.jsml.structures.SmlTime;
import org.openmuc.jsml.structures.SmlTreePath;
import org.openmuc.jsml.structures.Unsigned32;
import org.openmuc.jsml.structures.Unsigned64;

public class SmlGetProfileListRes extends Sequence {

    protected OctetString serverId;
    protected SmlTime actTime;
    protected Unsigned32 regPeriod;
    protected SmlTreePath parameterTreePath;
    protected SmlTime valTime;
    protected Unsigned64 status;
    protected ListOfSmlPeriodEntry periodList;
    protected OctetString rawdata; // OPTIONAL,
    protected SmlSignature periodSignature; // OPTIONAL

    public SmlGetProfileListRes() {
    }

    public SmlGetProfileListRes(OctetString serverId, SmlTime actTime, Unsigned32 regPeriod,
            SmlTreePath parameterTreePath, SmlTime valTime, Unsigned64 status, ListOfSmlPeriodEntry periodList,
            OctetString rawdata, SmlSignature periodSignature) {

        this.serverId = serverId;
        this.actTime = actTime;
        this.regPeriod = regPeriod;
        this.parameterTreePath = parameterTreePath;
        this.valTime = valTime;
        this.status = status;
        this.periodList = periodList;
        this.rawdata = rawdata;
        this.periodSignature = periodSignature;

        if (this.rawdata == null) {
            this.rawdata = new OctetString();
        }
        if (this.periodSignature == null) {
            this.periodSignature = new SmlSignature();
        }

        setOptionalAndSeq();
        setSelected(true);
    }

    public void setStatus(Unsigned64 status) {
        this.status = status;
    }

    public OctetString getServerId() {
        return serverId;
    }

    public SmlTime getActTime() {
        return actTime;
    }

    public Unsigned32 getRegPeriod() {
        return regPeriod;
    }

    public SmlTreePath getParameterTreePath() {
        return parameterTreePath;
    }

    public SmlTime getValTime() {
        return valTime;
    }

    public Unsigned64 getStatus() {
        return status;
    }

    public ListOfSmlPeriodEntry getPeriodList() {
        return periodList;
    }

    public OctetString getRawdata() {
        return rawdata;
    }

    public SmlSignature getPeriodSignature() {
        return periodSignature;
    }

    public void setOptionalAndSeq() {
        rawdata.setOptional();
        periodSignature.setOptional();

        seqArray(serverId, actTime, regPeriod, parameterTreePath, valTime, status, periodList, rawdata,
                periodSignature);
    }

    @Override
    protected void createElements() {
        serverId = new OctetString();
        actTime = new SmlTime();
        regPeriod = new Unsigned32();
        parameterTreePath = new SmlTreePath();
        valTime = new SmlTime();
        status = new Unsigned64();
        periodList = new ListOfSmlPeriodEntry();
        rawdata = new OctetString();
        periodSignature = new SmlSignature();

        setOptionalAndSeq();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SML_GetProfileListRes{\n");
        sb.append("  serverId:          " + serverId.toString() + "\n");
        sb.append("  actTime:           " + actTime.toString() + "\n");
        sb.append("  regPeriod:         " + regPeriod.toString() + "\n");
        sb.append("  parameterTreePath: " + parameterTreePath.toString() + "\n");
        sb.append("  valTime:           " + valTime.toString() + "\n");
        sb.append("  status:            " + status.toString() + "\n");
        sb.append("  period_List:       " + periodList.toString() + "\n");
        sb.append("  rawdata:           " + rawdata.toString() + "\n");
        sb.append("  periodSignature:   " + periodSignature.toString() + "\n");
        sb.append("}\n");
        return sb.toString();
    }
}
