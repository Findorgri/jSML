/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

import java.io.DataInputStream;
import java.io.IOException;

public class SmlProcParValue extends Choice {

    public static final int SMLVALUE = 1;
    public static final int SMLPERIODENTRY = 2;
    public static final int SMLTUPELENTRY = 3;
    public static final int SMLTIME = 4;
    public static final int SMLLISTENTRY = 5;

    public SmlProcParValue() {
    }

    public SmlProcParValue(int tag, ASNObject choice) {
        if (!(tag == SMLVALUE || tag == SMLPERIODENTRY || tag == SMLTUPELENTRY || SMLTIME == tag
                || SMLLISTENTRY == tag)) {
            throw new IllegalArgumentException("SML_ProcParValue: Wrong value for tag! " + tag + " is not allowed.");
        }

        setTag(new Unsigned8(tag));
        this.choice = choice;
        setSelected(true);
    }

    @Override
    public boolean decode(DataInputStream is) throws IOException {
        byte tlField = is.readByte();
        if (isOptional() && (tlField == 0x01)) {
            setSelected(false);
            return true;
        }
        if ((tlField & 0xff) != 0x72 || !getTag().decode(is)) {
            return false;
        }

        switch (getTag().getVal()) {
        case SMLVALUE:
            choice = new SmlValue();
            break;
        case SMLPERIODENTRY:
            choice = new SmlPeriodEntry();
            break;
        case SMLTUPELENTRY:
            choice = new SmlTupelEntry();
            break;
        case SMLTIME:
            choice = new SmlTime();
            break;
        case SMLLISTENTRY:
            choice = new SmlListEntry();
            break;
        default:
            throw new IllegalArgumentException("unknown tag number: " + getTag().getVal());
        }

        if (!choice.decode(is)) {
            return false;
        }

        setSelected(true);

        return true;
    }

    @Override
    public String toString() {

        String retValue = "not set";

        if (isSelected()) {
            String tagValue;

            switch (getTag().getVal()) {
            case SMLVALUE:
                tagValue = "SML_VALUE";
                break;
            case SMLPERIODENTRY:
                tagValue = "SML_PERIOD_ENTRY";
                break;
            case SMLTUPELENTRY:
                tagValue = "SML_TUPEL_ENTRY";
                break;
            case SMLTIME:
                tagValue = "SML_TIME";
                break;
            case SMLLISTENTRY:
                tagValue = "SML_LIST_ENTRY";
                break;
            default:
                tagValue = "unknown - " + getTag().getVal();
                break;
            }

            String value = "unknown";

            if (choice instanceof SmlTime) {
                SmlTime smlTime = (SmlTime) choice;
                value = smlTime.toString();
            }
            else if (choice instanceof SmlValue) {
                SmlValue smlvalue = (SmlValue) choice;
                value = smlvalue.toString();
            }
            else if (choice instanceof SmlPeriodEntry) {
                SmlPeriodEntry periodEntry = (SmlPeriodEntry) choice;
                value = periodEntry.toString();
            }
            else if (choice instanceof SmlTupelEntry) {
                SmlTupelEntry tupelEntry = (SmlTupelEntry) choice;
                value = tupelEntry.toString();
            }
            else if (choice instanceof SmlListEntry) {
                SmlListEntry listEntry = (SmlListEntry) choice;
                value = listEntry.toString();
            }

            retValue = "tag: " + tagValue + " value: " + value;
        }

        return retValue;

    }

}
