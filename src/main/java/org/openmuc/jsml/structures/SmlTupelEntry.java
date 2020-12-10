/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

public class SmlTupelEntry extends Sequence {

    private OctetString serverId;
    private SmlTime secIndex;
    private Unsigned64 status;
    private SmlUnit pAunit;
    private Integer8 pAscaler;
    private Integer64 pAvalue;
    private SmlUnit r1unit;
    private Integer8 r1scaler;
    private Integer64 r1value;
    private SmlUnit r4unit;
    private Integer8 r4scaler;
    private Integer64 r4value;
    private OctetString pAr1r4signature;
    private SmlUnit mAunit;
    private Integer8 mAscaler;
    private Integer64 mAvalue;
    private SmlUnit r2unit;
    private Integer8 r2scaler;
    private Integer64 r2value;
    private SmlUnit r3unit;
    private Integer8 r3scaler;
    private Integer64 r3value;
    private OctetString mAr2r3signature;

    public SmlTupelEntry() {
    }

    public SmlTupelEntry(OctetString serverId, SmlTime secIndex, Unsigned64 status, SmlUnit unitPA, Integer8 scalerPA,
            Integer64 valuePA, SmlUnit unitR1, Integer8 scalerR1, Integer64 valueR1, SmlUnit unitR4, Integer8 scalerR4,
            Integer64 valueR4, OctetString signaturePAR1R4, SmlUnit unitMA, Integer8 scalerMA, Integer64 valueMA,
            SmlUnit unitR2, Integer8 scalerR2, Integer64 valueR2, SmlUnit unitR3, Integer8 scalerR3, Integer64 valueR3,
            OctetString signatureMAR2R3) {

        this.serverId = serverId;
        this.secIndex = secIndex;
        this.status = status;
        pAunit = unitPA;
        pAscaler = scalerPA;
        pAvalue = valuePA;
        r1unit = unitR1;
        r1scaler = scalerR1;
        r1value = valueR1;
        r4unit = unitR4;
        r4scaler = scalerR4;
        r4value = valueR4;
        pAr1r4signature = signaturePAR1R4;
        mAunit = unitMA;
        mAscaler = scalerMA;
        mAvalue = valueMA;
        r2unit = unitR2;
        r2scaler = scalerR2;
        r2value = valueR2;
        r3unit = unitR3;
        r3scaler = scalerR3;
        r3value = valueR3;
        mAr2r3signature = signatureMAR2R3;

        setOptionalAndSeq();
        setSelected(true);
    }

    public void setOptionalAndSeq() {
        seqArray(new ASNObject[] { serverId, secIndex, status, pAunit, pAscaler, pAvalue, r1unit, r1scaler, r1value,
                r4unit, r4scaler, r4value, pAr1r4signature, mAunit, mAscaler, mAvalue, r2unit, r2scaler, r2value,
                r3unit, r3scaler, r3value, mAr2r3signature });
    }

    @Override
    protected void createElements() {
        serverId = new OctetString();
        secIndex = new SmlTime();
        status = new Unsigned64();
        pAunit = new SmlUnit();
        pAscaler = new Integer8();
        pAvalue = new Integer64();
        r1unit = new SmlUnit();
        r1scaler = new Integer8();
        r1value = new Integer64();
        r4unit = new SmlUnit();
        r4scaler = new Integer8();
        r4value = new Integer64();
        pAr1r4signature = new OctetString();
        mAunit = new SmlUnit();
        mAscaler = new Integer8();
        mAvalue = new Integer64();
        r2unit = new SmlUnit();
        r2scaler = new Integer8();
        r2value = new Integer64();
        r3unit = new SmlUnit();
        r3scaler = new Integer8();
        r3value = new Integer64();
        mAr2r3signature = new OctetString();

        setOptionalAndSeq();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nSML_TupelEntry{\n");
        sb.append("  serverId:           " + serverId.toString() + "\n");
        sb.append("  secIndex:           " + secIndex.toString() + "\n");
        sb.append("  status:             " + status.toString() + "\n");
        sb.append("  unit_pA:            " + pAunit.toString() + "\n");
        sb.append("  scaler_pA:          " + pAscaler.toString() + "\n");
        sb.append("  value_pA:           " + pAvalue.toString() + "\n");
        sb.append("  unit_R1:            " + r1unit.toString() + "\n");
        sb.append("  scaler_R1:          " + r1scaler.toString() + "\n");
        sb.append("  value_R1:           " + r1value.toString() + "\n");
        sb.append("  unit_R4:            " + r4unit.toString() + "\n");
        sb.append("  scaler_R4:          " + r4scaler.toString() + "\n");
        sb.append("  value_R4:           " + r4value.toString() + "\n");
        sb.append("  signature_pA_R1_R4: " + pAr1r4signature.toString() + "\n");
        sb.append("  unit_mA:            " + mAunit.toString() + "\n");
        sb.append("  scaler_mA:          " + mAscaler.toString() + "\n");
        sb.append("  value_mA:           " + mAvalue.toString() + "\n");
        sb.append("  unit_R2:            " + r2unit.toString() + "\n");
        sb.append("  scaler_R2:          " + r2scaler.toString() + "\n");
        sb.append("  value_R2:           " + r2value.toString() + "\n");
        sb.append("  unit_R3:            " + r3unit.toString() + "\n");
        sb.append("  scaler_R3:          " + r3scaler.toString() + "\n");
        sb.append("  value_R3:           " + r3value.toString() + "\n");
        sb.append("  signature_mA_R2_R3: " + mAr2r3signature.toString() + "\n");
        sb.append("}\n");
        return sb.toString();
    }

    public OctetString serverId() {
        return serverId;
    }

    public SmlTime secIndex() {
        return secIndex;
    }

    public Unsigned64 status() {
        return status;
    }

    public SmlUnit pAunit() {
        return pAunit;
    }

    public Integer8 pAscaler() {
        return pAscaler;
    }

    public Integer64 pAvalue() {
        return pAvalue;
    }

    public SmlUnit r1unit() {
        return r1unit;
    }

    public Integer8 r1scaler() {
        return r1scaler;
    }

    public Integer64 r1value() {
        return r1value;
    }

    public SmlUnit r4unit() {
        return r4unit;
    }

    public Integer8 r4scaler() {
        return r4scaler;
    }

    public Integer64 r4value() {
        return r4value;
    }

    public OctetString pAr1r4signature() {
        return pAr1r4signature;
    }

    public SmlUnit mAunit() {
        return mAunit;
    }

    public Integer8 mAscaler() {
        return mAscaler;
    }

    public Integer64 mAvalue() {
        return mAvalue;
    }

    public SmlUnit r2unit() {
        return r2unit;
    }

    public Integer8 r2scaler() {
        return r2scaler;
    }

    public Integer64 r2value() {
        return r2value;
    }

    public SmlUnit r3unit() {
        return r3unit;
    }

    public Integer8 r3scaler() {
        return r3scaler;
    }

    public Integer64 r3value() {
        return r3value;
    }

    public OctetString mAr2r3signature() {
        return mAr2r3signature;
    }
}
