/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

import org.openmuc.jsml.EObis;

public class SmlProfObjHeaderEntry extends Sequence {

    private OctetString objName;
    private SmlUnit unit;
    private Integer8 scaler;

    public SmlProfObjHeaderEntry() {
    }

    public SmlProfObjHeaderEntry(OctetString objName, SmlUnit unit, Integer8 scaler) {
        this.objName = objName;
        this.unit = unit;
        this.scaler = scaler;

        setOptionalAndSeq();
        setSelected(true);
    }

    public OctetString getObjName() {
        return objName;
    }

    public SmlUnit getUnit() {
        return unit;
    }

    public Integer8 getScaler() {
        return scaler;
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

    public void setOptionalAndSeq() {
        seqArray(new ASNObject[] { objName, unit, scaler });
    }

    @Override
    protected void createElements() {
        objName = new OctetString();
        unit = new SmlUnit();
        scaler = new Integer8();

        setOptionalAndSeq();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n    SML_ProfObjHeaderEntry{\n");
        sb.append("      objName:         " + EObis.getInstance(objName) + "\t" + objName.toString() + "\n");
        sb.append("      unit:            " + unit.toString() + "\n");
        sb.append("      scaler:          " + scaler.toString() + "\n");
        sb.append("}\n");
        return sb.toString();
    }
}
