/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

import org.openmuc.jsml.EObis;

public class SmlListEntry extends Sequence {

    private OctetString objName;
    private SmlStatus status; // OPTIONAL,
    private SmlTime valTime; // OPTIONAL,
    private SmlUnit unit; // OPTIONAL,
    private Integer8 scaler; // OPTIONAL,
    private SmlValue value;
    private SmlSignature valueSignature; // OPTIONAL

    public SmlListEntry() {
    }

    public SmlListEntry(OctetString objName, SmlStatus status, SmlTime valTime, SmlUnit unit, Integer8 scaler,
            SmlValue value, SmlSignature valueSignature) {

        if (objName == null) {
            throw new IllegalArgumentException("SML_ListEntry: objName is not optional and must not be null!");
        }
        if (value == null) {
            throw new IllegalArgumentException("SML_ListEntry: value is not optional and must not be null!");
        }

        this.objName = objName;
        this.status = status;
        this.valTime = valTime;
        this.unit = unit;
        this.scaler = scaler;
        this.value = value;
        this.valueSignature = valueSignature;

        if (this.status == null) {
            this.status = new SmlStatus();
        }
        if (this.valTime == null) {
            this.valTime = new SmlTime();
        }
        if (this.unit == null) {
            this.unit = new SmlUnit();
        }
        if (this.scaler == null) {
            this.scaler = new Integer8();
        }
        if (this.valueSignature == null) {
            this.valueSignature = new SmlSignature();
        }

        setOptionalAndSeq();
        setSelected(true);
    }

    public OctetString getObjName() {
        return objName;
    }

    public SmlStatus getStatus() {
        return status;
    }

    public SmlTime getValTime() {
        return valTime;
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

    public void setOptionalAndSeq() {
        status.setOptional();
        valTime.setOptional();
        unit.setOptional();
        scaler.setOptional();
        valueSignature.setOptional();

        seqArray(new ASNObject[] { objName, status, valTime, unit, scaler, value, valueSignature });
    }

    @Override
    protected void createElements() {
        objName = new OctetString();
        status = new SmlStatus();
        valTime = new SmlTime();
        unit = new SmlUnit();
        scaler = new Integer8();
        value = new SmlValue();
        valueSignature = new SmlSignature();
        setOptionalAndSeq();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SML_ListEntry{\n");
        sb.append("               objName:         " + objName.toString() + "\n");
        sb.append("               status:          " + status.toString() + "\n");
        sb.append("               valTime:         " + valTime.toString() + "\n");
        sb.append("               unit:            " + unit.toString() + "\n");
        sb.append("               scaler:          " + scaler.toString() + "\n");
        sb.append("               value:           " + value.toString() + "\n");
        sb.append("               valueSignature:  " + valueSignature.toString() + "\n");
        sb.append("            }\n");
        return sb.toString();
    }

    public String toStringIndent(String level) {
        StringBuilder sb = new StringBuilder();
        sb.append("SML_ListEntry{\n");
        sb.append(level + "objName:         " + EObis.getInstance(objName).name() + "    " + objName.toString() + "\n");
        sb.append(level + "status:          " + status.toStringIndent(level + "    ") + "\n");
        sb.append(level + "valTime:         " + valTime.toString() + "\n");
        sb.append(level + "unit:            " + unit.toString() + "\n");
        sb.append(level + "scaler:          " + scaler.toString() + "\n");
        sb.append(level + "value:           " + value.toString() + "\n");
        sb.append(level + "valueSignature:  " + valueSignature.toString() + "\n");

        String indent = "";
        if (level.length() >= 4) {
            indent = level.substring(0, level.length() - 4);
        }

        sb.append(indent + "}\n");
        return sb.toString();
    }
}
