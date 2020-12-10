package org.openmuc.jsml.test.structures;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.openmuc.jsml.structures.ASNObject;
import org.openmuc.jsml.structures.Integer64;
import org.openmuc.jsml.structures.OctetString;
import org.openmuc.jsml.structures.SmlListType;
import org.openmuc.jsml.structures.SmlTime;
import org.openmuc.jsml.structures.SmlValue;
import org.openmuc.jsml.structures.Unsigned32;
import org.openmuc.jsml.test.Utils;

public class SmlValueTest {

    /**
     * Verifies that a sml value containg a list type with sml time is decoded correctly
     *
     * @throws IOException
     */
    @Test
    public void decodeSmlValueChoiceListType() throws IOException {
        String smlValueStr = "72 62 01 72 62 01 65 00 00 1B 30";
        byte[] data = Utils.hexStringToByteArray(smlValueStr);
        DataInputStream is = new DataInputStream(new ByteArrayInputStream(data));

        SmlValue smlValue = new SmlValue();
        Assert.assertTrue(smlValue.decode(is));
        Assert.assertNotNull(smlValue.getChoice());
        ASNObject choice = smlValue.getChoice();
        Assert.assertEquals(SmlListType.class, choice.getClass());

        ASNObject listTypeChoice = ((SmlListType) choice).getChoice();
        Assert.assertEquals(SmlTime.class, listTypeChoice.getClass());
        ASNObject timeChoice = ((SmlTime) listTypeChoice).getChoice();

        Assert.assertEquals(6960, ((Unsigned32) timeChoice).getVal());
    }

    /**
     * Verifies that a sml value containg a list type with sml time is encoded correctly
     *
     * @throws IOException
     */
    @Test
    public void encodeSmlValueChoiceListType() throws IOException {
        SmlTime smlTime = new SmlTime(SmlTime.SECINDEX, new Unsigned32(6960));

        SmlListType smlListType = new SmlListType(smlTime);
        SmlValue smlValue = new SmlValue(smlListType);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream outputStream = new DataOutputStream(baos);

        String smlValueStr = "72 62 01 72 62 01 65 00 00 1B 30";
        smlValue.encode(outputStream);

        Utils.assertEqualsIgnoreWhitespace(smlValueStr, Utils.bytesToHex(baos.toByteArray()));
    }

    /**
     * Verifies that a sml value containg a integer 64 is decoded correctly
     *
     * @throws IOException
     */
    @Test
    public void decodeSmlValueInteger64() throws IOException {
        String smlValueStr = "5600000596B2";
        byte[] data = Utils.hexStringToByteArray(smlValueStr);
        DataInputStream is = new DataInputStream(new ByteArrayInputStream(data));

        SmlValue smlValue = new SmlValue();
        Assert.assertTrue(smlValue.decode(is));
        Assert.assertNotNull(smlValue.getChoice());
        ASNObject choice = smlValue.getChoice();
        Assert.assertEquals(Integer64.class, choice.getClass());

        Integer64 intVal = (Integer64) choice;

        Assert.assertEquals(366258, intVal.getVal());
    }

    /**
     * Verifies that a sml value containg a integer 64 is decoded correctly
     *
     * @throws IOException
     */
    @Test
    public void encodeSmlValueInteger64() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream outputStream = new DataOutputStream(baos);

        SmlValue smlValue = new SmlValue(new Integer64(366258));
        smlValue.encode(outputStream);

        String smlValueStr = "5900000000000596B2";
        Utils.assertEqualsIgnoreWhitespace(smlValueStr, Utils.bytesToHex(baos.toByteArray()));
    }

    /**
     * Verifies that a sml value containing an octet string will be decoded correctly
     *
     * @throws IOException
     */
    @Test
    public void decodeSmlValueOctetString() throws IOException {
        String smlValueStr = "8103313233343536373839302D616263646566";
        byte[] data = Utils.hexStringToByteArray(smlValueStr);
        DataInputStream is = new DataInputStream(new ByteArrayInputStream(data));

        SmlValue smlValue = new SmlValue();
        Assert.assertTrue(smlValue.decode(is));
        Assert.assertNotNull(smlValue.getChoice());
        ASNObject choice = smlValue.getChoice();
        Assert.assertEquals(OctetString.class, choice.getClass());

        OctetString octetString = (OctetString) choice;

        String expectedValue = "31 32 33 34 35 36 37 38 39 30 2D 61 62 63 64 65 66";
        Utils.assertEqualsIgnoreWhitespace(expectedValue, octetString.toHexString());
    }

    /**
     * Verifies that a sml value containing an octet string will be enc correctly
     *
     * @throws IOException
     */
    @Test
    public void enodeSmlValueSignature() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream outputStream = new DataOutputStream(baos);

        SmlValue smlValue = new SmlValue(
                new OctetString(Utils.hexStringToByteArray("313233343536373839302D616263646566")));
        smlValue.encode(outputStream);

        String expectedValue = "8103313233343536373839302D616263646566";
        Utils.assertEqualsIgnoreWhitespace(expectedValue, Utils.bytesToHex(baos.toByteArray()));
    }
}
