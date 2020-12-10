/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

import org.openmuc.jsml.EObis;

public class SmlTree extends Sequence {

    private OctetString parameterName;
    private SmlProcParValue parameterValue; // OPTIONAL
    private ListOfSmlTree childList; // OPTIONAL

    public OctetString getParameterName() {
        return parameterName;
    }

    public SmlProcParValue getParameterValue() {
        return parameterValue;
    }

    public ListOfSmlTree getChildList() {
        return childList;
    }

    public SmlTree(OctetString parameterName, SmlProcParValue parameterValue, ListOfSmlTree childList) {
        this.parameterName = parameterName;
        if (parameterValue != null) {
            this.parameterValue = parameterValue;
        }
        else {
            this.parameterValue = new SmlProcParValue();
        }
        if (childList != null) {
            this.childList = childList;
        }
        else {
            this.childList = new ListOfSmlTree();
        }

        setOptionalAndSeq();
        setSelected(true);
    }

    public SmlTree() {
    }

    public void setOptionalAndSeq() {
        parameterValue.setOptional();
        childList.setOptional();
        seqArray(new ASNObject[] { parameterName, parameterValue, childList });
    }

    @Override
    protected void createElements() {
        parameterName = new OctetString();
        parameterValue = new SmlProcParValue();
        childList = new ListOfSmlTree();
        setOptionalAndSeq();
    }

    @Override
    public String toString() {

        if (parameterName == null || parameterValue == null || childList == null) {
            return "not set";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n  SML_Tree{\n");
        sb.append("    parameterName:    " + EObis.getInstance(parameterName) + "     " + parameterName.toString()
                + "\n");
        sb.append("    parameterValue:   " + parameterValue.toString() + "\n");
        sb.append("    List_of_SML_Tree: " + childList.toString() + "\n");
        sb.append("  }\n");
        return sb.toString();

    }

}
