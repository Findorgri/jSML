/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

import org.openmuc.jsml.EObis;

public class SmlPeriodEntry extends Sequence {

    private OctetString objName;
    private SmlUnit unit;
    private Integer8 scaler;
    private SmlValue value;
    private SmlSignature valueSignature; // OPTIONAL

    public OctetString getObjName() {
        return objName;
    }

    public SmlUnit getUnit() {
        return unit;
    }

    public Integer8 getScaler() {
        return scaler;
    }

    public SmlValue getValue() {
        return value;
    }

    public SmlSignature getValueSignature() {
        return valueSignature;
    }

    public void setObjName(OctetString objName) {
        this.objName = objName;
    }

    public void setUnit(SmlUnit unit) {
        this.unit = unit;
    }

    public void setScaler(Integer8 scaler) {
        this.scaler = scaler;
    }

    public void setValue(SmlValue value) {
        this.value = value;
    }

    public void setValueSignature(SmlSignature valueSignature) {
        this.valueSignature = valueSignature;
    }

    public SmlPeriodEntry(OctetString objName, SmlUnit unit, Integer8 scaler, SmlValue value,
            SmlSignature valueSignature) {
        this.objName = objName;
        this.unit = unit;
        this.scaler = scaler;
        this.value = value;
        this.valueSignature = valueSignature;

        if (this.valueSignature == null) {
            this.valueSignature = new SmlSignature();
        }

        setOptionalAndSeq();
        setSelected(true);
    }

    public SmlPeriodEntry() {
    }

    public void setOptionalAndSeq() {
        valueSignature.setOptional();
        seqArray(new ASNObject[] { objName, unit, scaler, value, valueSignature });
    }

    @Override
    protected void createElements() {
        objName = new OctetString();
        unit = new SmlUnit();
        scaler = new Integer8();
        value = new SmlValue();
        valueSignature = new SmlSignature();

        setOptionalAndSeq();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n    SML_PeriodEntry{\n");
        sb.append("      objName:         " + EObis.getInstance(objName) + " " + objName.toString() + "\n");
        sb.append("      unit:            " + unit.toString() + "\n");
        sb.append("      scaler:          " + scaler.toString() + "\n");
        sb.append("      value:           " + value.toString() + "\n");
        sb.append("      valueSignature:  " + valueSignature.toString() + "\n");

        sb.append("}\n");
        return sb.toString();
    }

}
