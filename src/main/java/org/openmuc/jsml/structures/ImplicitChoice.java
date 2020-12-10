/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

public abstract class ImplicitChoice extends ASNObject {

    public static final int OCTETSTRING = 0;
    public static final int SML_BOOLEAN = 4;
    public static final int INTEGER = 5;
    public static final int UNSIGNED = 6;
    public static final int SML_LIST_TYPE = 7;
    public static final int TL_FIELD = 8;

    private ASNObject choice;

    @Override
    public void encode(DataOutputStream os) throws IOException {
        if (isOptional() && !isSelected()) {
            os.writeByte(0x01);
            return;
        }
        getChoice().encode(os);
    }

    @Override
    public boolean decode(DataInputStream is) throws IOException {
        PushbackInputStream ispushback = new PushbackInputStream(is);
        DataInputStream is2 = new DataInputStream(ispushback);

        byte tlFieldChoice = (byte) ispushback.read();
        if (isOptional() && (tlFieldChoice == 0x01)) {
            setSelected(false);
            return true;
        }

        // read type-part of tlFieldChoice
        switch ((tlFieldChoice & 0xF0) >> 4) {
        case OCTETSTRING:
            setChoice(new OctetString());
            break;

        case SML_BOOLEAN:
            setChoice(new SmlBoolean());
            break;

        case INTEGER:
            // read length-part of tlFieldChoice
            switch (tlFieldChoice & 0x0F) {
            case 2:
                setChoice(new Integer8());
                break;
            case 3:
                setChoice(new Integer16());
                break;
            case 4:
            case 5:
                setChoice(new Integer32());
                break;
            case 6:
            case 7:
            case 8:
            case 9:
                setChoice(new Integer64());
                break;
            default:
                return false;
            }

            break;

        case UNSIGNED:
            // read length-part of tlFieldChoice
            switch (tlFieldChoice & 0x0F) {
            case 2:
                setChoice(new Unsigned8());
                break;
            case 3:
                setChoice(new Unsigned16());
                break;
            case 4:
            case 5:
                setChoice(new Unsigned32());
                break;
            case 6:
            case 7:
            case 8:
            case 9:
                setChoice(new Unsigned64());
                break;
            default:
                return false;
            }

            break;
        case SML_LIST_TYPE:
            setChoice(new SmlListType());
            break;
        // another TL-Field follows
        // this is only possible if an octetstring was sent
        case TL_FIELD:
            setChoice(new OctetString());
            break;
        default:
            return false;
        }

        // push back the TL-field of the choice-object
        ispushback.unread(tlFieldChoice);
        if (!getChoice().decode(is2)) {
            return false;
        }

        setSelected(true);
        return true;
    }

    public ASNObject getChoice() {
        return choice;
    }

    public String getDatatype() {
        return getChoice().getClass().getName();
    }

    protected void setChoice(ASNObject choice) {
        this.choice = choice;
    }

}
