/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

public class SmlValue extends ImplicitChoice {

    public static final int BOOLEAN = 1;
    public static final int OCTETSTRING = 2;
    public static final int INTEGER8 = 3;
    public static final int INTEGER16 = 4;
    public static final int INTEGER32 = 5;
    public static final int INTEGER64 = 6;
    public static final int UNSIGNED8 = 7;
    public static final int UNSIGNED16 = 8;
    public static final int UNSIGNED32 = 9;
    public static final int UNSIGNED64 = 10;

    public SmlValue() {
    }

    /**
     * 
     * @param choice
     *            possible values: Boolean, OctetString, Integer{8,16,32,64}, Unsigned{8-64}
     */
    public SmlValue(ASNObject choice) {
        setValue(choice);
    }

    public void setValue(ASNObject choice) {
        if (!(choice instanceof SmlBoolean || choice instanceof OctetString || choice instanceof Integer8
                || choice instanceof Integer16 || choice instanceof Integer32 || choice instanceof Integer64
                || choice instanceof Unsigned8 || choice instanceof Unsigned16 || choice instanceof Unsigned32
                || choice instanceof Unsigned64 || choice instanceof SmlListType)) {
            throw new IllegalArgumentException(
                    "SML_Value: Wrong ASNObject! " + choice.getClass().getName() + " is not allowed.");
        }

        setChoice(choice);
        setSelected(true);
    }

    @Override
    public String toString() {
        return getChoice().toString();
    }
}
