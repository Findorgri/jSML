package org.openmuc.jsml.structures;

import java.io.DataInputStream;
import java.io.IOException;

public class SmlListType extends Choice {

    public static final int SML_TIME = 1;

    public SmlListType(SmlTime smlTime) {
        this.choice = smlTime;
        setTag(new Unsigned8(SML_TIME));
    }

    public SmlListType() {
        setSelected(false);
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

        choice = new SmlTime();
        if (!choice.decode(is)) {
            return false;
        }

        setSelected(true);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SML_ListType{\n").append("             choice:          ");
        if (choice instanceof SmlTime) {
            SmlTime smlTime = (SmlTime) choice;
            sb.append(smlTime.toString());
        }
        else {
            sb.append("unknown");
        }
        sb.append("\n         }\n");
        return sb.toString();
    }
}
