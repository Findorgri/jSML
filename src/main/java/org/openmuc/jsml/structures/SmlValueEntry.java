/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

public class SmlValueEntry extends Sequence {

    private SmlValue value;
    private SmlSignature valueSignature; // OPTIONAL

    public SmlValueEntry(SmlValue value, SmlSignature valueSignature) {
        this.value = value;
        this.valueSignature = valueSignature;

        if (this.valueSignature == null) {
            this.valueSignature = new SmlSignature();
        }

        setOptionalAndSeq();
        setSelected(true);
    }

    public void setOptionalAndSeq() {
        valueSignature.setOptional();
        seqArray(new ASNObject[] { value, valueSignature });
    }

    @Override
    protected void createElements() {
        value = new SmlValue();
        valueSignature = new SmlSignature();

        setOptionalAndSeq();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n  SML_ValueEntry{\n");
        sb.append("    value:    " + value.toString() + "\n");
        sb.append("    valueSignature:   " + valueSignature.toString() + "\n");
        sb.append("  }\n");
        return sb.toString();
    }

    public SmlValue getValue() {
        return value;
    }

    public SmlSignature getValueSignature() {
        return valueSignature;
    }

    public void setValue(SmlValue value) {
        this.value = value;
    }

    public void setValueSignature(SmlSignature valueSignature) {
        this.valueSignature = valueSignature;
    }

    public SmlValueEntry() {
    }

}
