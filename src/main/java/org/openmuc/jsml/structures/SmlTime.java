/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

import java.io.DataInputStream;
import java.io.IOException;

public class SmlTime extends Choice {

    public static final int SECINDEX = 1;
    public static final int TIMESTAMP = 2;
    public static final int TIMESTAMP_LOCAL = 3;

    public SmlTime() {
    }

    public SmlTime(int tag, ASNObject choice) {
        setTime(tag, choice);
    }

    public void setTime(int tag, ASNObject choice) {
        if (!(tag == SECINDEX || tag == TIMESTAMP || tag == TIMESTAMP_LOCAL)) {
            throw new IllegalArgumentException("SML_Time: Wrong value for tag! " + tag + " is not allowed.");
        }

        this.choice = choice;
        setTag(new Unsigned8(tag));
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
        case SECINDEX:
            choice = new Unsigned32();
            break;
        case TIMESTAMP:
            choice = new SmlTimestamp();
            break;
        case TIMESTAMP_LOCAL:
            choice = new SmlTimestampLocal();
            break;
        default:
            throw new IllegalArgumentException("tag number not supported yet: " + getTag().getVal());
        }

        if (!choice.decode(is)) {
            return false;
        }

        setSelected(true);

        return true;
    }

    @Override
    public String toString() {
        String tagValue;

        switch (getTag().getVal()) {
        case SECINDEX:
            tagValue = "SECINDEX";
            break;
        case TIMESTAMP:
            tagValue = "TIMESTAMP";
            break;
        case TIMESTAMP_LOCAL:
            tagValue = "TIMESTAMP_LOCAL";
            break;
        default:
            tagValue = "Unknown tag: " + getTag().getVal();
            break;
        }

        String value = "unknown";
        if (choice instanceof SmlTimestamp) {
            SmlTimestamp timestamp = (SmlTimestamp) choice;
            value = String.valueOf(timestamp.getVal());
        }
        else if (choice instanceof Unsigned32) {
            Unsigned32 uint32 = (Unsigned32) choice;
            value = uint32.toString();
        }

        return tagValue + " value: " + value;
    }
}
