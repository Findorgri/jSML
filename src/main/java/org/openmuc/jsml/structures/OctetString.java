/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class OctetString extends ASNObject {

    private static final int MAX_LENGTH = 32000;
    private byte[] value;

    public OctetString() {
    }

    public OctetString(byte[] octetString) {
        set(octetString);
        setSelected(true);
    }

    public OctetString(String octetString) {
        this(toArray(octetString));
    }

    public byte[] getValue() {
        return value;
    }

    public void set(byte[] octetString) {
        if (octetString != null) {
            if (octetString.length < MAX_LENGTH) {
                this.value = octetString;
            }
        }
        else {
            this.value = new byte[0];
        }
    }

    @Override
    public void encode(DataOutputStream os) throws IOException {
        if (isOptional() && !isSelected()) {
            os.writeByte(0x01);
            return;
        }
        int numTlField = 1;
        while ((Math.pow(2, 4d * numTlField) - 1 - numTlField) < value.length) {
            numTlField++;
        }

        for (int i = numTlField; i > 0; i--) {

            int firstFourBits = 0x00;
            if (i > 1) {
                firstFourBits = 0x80;
            }
            os.writeByte((firstFourBits & 0xff) | (((value.length + numTlField) >> ((i - 1) * 4)) & 0xf));

        }

        for (byte element : value) {
            os.write(element);
        }
    }

    @Override
    public boolean decode(DataInputStream is) throws IOException {
        int tlLength = 1;
        byte typeLength = is.readByte();
        if (isOptional() && (typeLength == 0x01)) {
            setSelected(false);
            return true;
        }
        if (((typeLength & 0x70) >> 4) != 0) {
            return false;
        }
        int length = typeLength & 0x0f;

        while ((typeLength & 0x80) == 0x80) {
            tlLength++;
            typeLength = is.readByte();
            if (((typeLength & 0x70) >> 4) != 0) {
                return false;
            }
            length = ((length & 0xffffffff) << 4) | (typeLength & 0x0f);
        }

        length = length - tlLength;

        value = new byte[length];

        for (int i = 0; i < length; i++) {
            value[i] = is.readByte();
        }

        setSelected(true);

        return true;
    }

    /**
     * @return bytes of the OctetString as byte[]
     */
    public byte[] toBytes() {
        return value;
    }

    /**
     * Returns a hash code for this octet string.
     */
    @Override
    public int hashCode() {
        return java.util.Arrays.hashCode(value);
    }

    /**
     * Compares this octet string to the specified object.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof OctetString) {
            return Arrays.equals(value, ((OctetString) other).toBytes());
        }
        return false;
    }

    @Override
    public String toString() {
        if (value == null) {
            return "not set";
        }
        else {
            return toHexString();
        }
    }

    /**
     * @return decimal representation of the OBIS Code e.g. 1.0.81.7.1.255
     */
    public String toDecimalString() {
        StringBuilder asBytes = new StringBuilder();

        for (int i = 0; i < value.length - 1; i++) {
            asBytes.append(value[i] & 0xFF).append(".");
        }
        asBytes.append(value[value.length - 1] & 0xFF);

        return asBytes.toString();
    }

    /**
     * @return hex representation of the OBIS Code 01 00 34 07 00 FF
     */
    public String toHexString() {

        StringBuilder asBytes = new StringBuilder();
        for (byte element : value) {
            asBytes.append(String.format("%02X ", element));
        }

        return asBytes.toString().trim();
    }

    public static byte[] toArray(String octetString) {
        if (octetString != null) {
            int ln = octetString.length();
            if (ln > 2 && (ln & 1) == 0 && octetString.startsWith("0x")) {
                byte[] buf = new byte[(ln >> 1) - 1];
                for (int i = 2; i < ln; i += 2) {
                    buf[(i >> 1) - 1] = (byte) ((Character.digit(octetString.charAt(i), 16) << 4)
                            + Character.digit(octetString.charAt(i + 1), 16));
                }
                return buf;
            }
            else {
                return octetString.getBytes();
            }
        }
        return new byte[0];
    }

}
