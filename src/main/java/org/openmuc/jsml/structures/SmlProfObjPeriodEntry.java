/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

public class SmlProfObjPeriodEntry extends Sequence {

    private SmlTime valTime;
    private Unsigned64 status;
    private ListOfSmlValueEntry valueList;
    private SmlSignature periodSignature; // OPTIONAL

    public SmlProfObjPeriodEntry() {
    }

    public SmlProfObjPeriodEntry(SmlTime valTime, Unsigned64 status, ListOfSmlValueEntry valueList,
            SmlSignature periodSignature) {
        this.valTime = valTime;
        this.status = status;
        this.valueList = valueList;
        this.periodSignature = periodSignature;

        if (this.periodSignature == null) {
            this.periodSignature = new SmlSignature();
        }

        setOptionalAndSeq();
        setSelected(true);
    }

    public SmlTime getValTime() {
        return valTime;
    }

    public Unsigned64 getStatus() {
        return status;
    }

    public ListOfSmlValueEntry getValueList() {
        return valueList;
    }

    public SmlSignature getPeriodSignature() {
        return periodSignature;
    }

    public void setValTime(SmlTime valTime) {
        this.valTime = valTime;
    }

    public void setStatus(Unsigned64 status) {
        this.status = status;
    }

    public void setValue_List(ListOfSmlValueEntry valueList) {
        this.valueList = valueList;
    }

    public void setPeriodSignature(SmlSignature periodSignature) {
        this.periodSignature = periodSignature;
    }

    public void setOptionalAndSeq() {
        periodSignature.setOptional();
        seqArray(new ASNObject[] { valTime, status, valueList, periodSignature });
    }

    @Override
    protected void createElements() {
        valTime = new SmlTime();
        status = new Unsigned64();
        valueList = new ListOfSmlValueEntry();
        periodSignature = new SmlSignature();

        setOptionalAndSeq();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SML_ProfObjPeriodEntry{\n");
        sb.append("  valTime:          " + valTime.toString() + "\n");
        sb.append("  status:           " + status.toString() + "\n");
        sb.append("  value_List:       " + valueList.toString() + "\n");
        sb.append("  periodSignature:  " + periodSignature.toString() + "\n");
        sb.append("}\n");
        return sb.toString();
    }

}
