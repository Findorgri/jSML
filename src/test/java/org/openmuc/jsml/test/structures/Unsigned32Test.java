package org.openmuc.jsml.test.structures;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.openmuc.jsml.structures.Unsigned32;
import org.openmuc.jsml.test.Utils;

public class Unsigned32Test {

    /**
     * 1B30 is 6960 in decimal
     */
    private static final String UNSIGNED_32_HEX_STRING = "6500001B30";

    /**
     * Verifies that a unsigned 32 of a byte array will be read correctly
     *
     * @throws IOException
     */
    @Test
    public void decodeValue() throws IOException {
        byte[] data = Utils.hexStringToByteArray(UNSIGNED_32_HEX_STRING);
        DataInputStream is = new DataInputStream(new ByteArrayInputStream(data));

        Unsigned32 unsigned32 = new Unsigned32();
        unsigned32.decode(is);
        Assert.assertEquals(6960, unsigned32.getVal());
        Assert.assertTrue(unsigned32.isSelected());
    }

    /**
     * Verifies that an empty unsigned 32 byte array will result in a 0 value
     *
     * @throws IOException
     */
    @Test
    public void decodeValueEmpty() throws IOException {
        byte[] data = new byte[0x01];
        DataInputStream is = new DataInputStream(new ByteArrayInputStream(data));

        Unsigned32 unsigned32 = new Unsigned32();
        unsigned32.decode(is);
        Assert.assertEquals(0, unsigned32.getVal());
        Assert.assertFalse(unsigned32.isSelected());
    }

    /**
     * Verifies that an unsigned 32 value with an integer filled in will be encoded correctly
     *
     * @throws IOException
     */
    @Test
    public void encodeUnsigned32Value() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Unsigned32 unsigned32 = new Unsigned32(6960);

        unsigned32.encode(new DataOutputStream(baos));
        Assert.assertEquals(UNSIGNED_32_HEX_STRING, Utils.bytesToHex(baos.toByteArray()));
    }

    /**
     * Verifies that an unsigned 32 value with an empty filled in and optinal set to true will be encoded correctly
     * (0x01)
     *
     * @throws IOException
     */
    @Test
    public void encodeUnsigned32ValueEmptyOptional() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Unsigned32 unsigned32 = new Unsigned32();
        unsigned32.setOptional();

        unsigned32.encode(new DataOutputStream(baos));
        Assert.assertEquals("01", Utils.bytesToHex(baos.toByteArray()));
    }

    /**
     * Verifies that an unsigned 32 value with an empty filled in will be encoded correctly
     *
     * @throws IOException
     */
    @Test
    public void encodeUnsigned32ValueNotOptional() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Unsigned32 unsigned32 = new Unsigned32();

        unsigned32.encode(new DataOutputStream(baos));
        Assert.assertEquals("6500000000", Utils.bytesToHex(baos.toByteArray()));
    }
}
