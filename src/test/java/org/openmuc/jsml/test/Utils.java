package org.openmuc.jsml.test;

import org.junit.Assert;

public class Utils {

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    /**
     * Transfers a string with hex encoded values to a byte array
     * 
     * @param hexData
     *            data to convert empty spaces will be stripped
     * @return byte array out of the data
     */
    public static byte[] hexStringToByteArray(String hexData) {
        hexData = hexData.replaceAll(" ", "");
        int len = hexData.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexData.charAt(i), 16) << 4)
                    + Character.digit(hexData.charAt(i + 1), 16));
        }
        return data;
    }

    /**
     * Converts a byte array to a hex string
     *
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    /**
     * Asserts two strings but ignore containing whitespace diffs
     *
     * @param expectedValue
     * @param value
     */
    public static void assertEqualsIgnoreWhitespace(String expectedValue, String value) {
        expectedValue = expectedValue.replaceAll(" ", "");
        value = value.replaceAll(" ", "");
        Assert.assertEquals(expectedValue, value);
    }
}
