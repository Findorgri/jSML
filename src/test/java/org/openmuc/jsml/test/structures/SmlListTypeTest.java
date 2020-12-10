package org.openmuc.jsml.test.structures;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.openmuc.jsml.structures.ASNObject;
import org.openmuc.jsml.structures.SmlListType;
import org.openmuc.jsml.structures.SmlTime;
import org.openmuc.jsml.structures.Unsigned32;
import org.openmuc.jsml.test.Utils;

public class SmlListTypeTest {

    /**
     * Verifies that a sml list type with sml time is decoded correctly
     *
     * @throws IOException
     */
    @Test
    public void decodeSmlValueChoiceListType() throws IOException {
        String smlValueStr = "72 62 01 72 62 01 65 00 00 1B 30";
        byte[] data = Utils.hexStringToByteArray(smlValueStr);
        DataInputStream is = new DataInputStream(new ByteArrayInputStream(data));

        SmlListType smlValue = new SmlListType();
        Assert.assertTrue(smlValue.decode(is));
        Assert.assertNotNull(smlValue.getChoice());

        ASNObject listTypeChoice = smlValue.getChoice();
        Assert.assertEquals(SmlTime.class, listTypeChoice.getClass());
        ASNObject timeChoice = ((SmlTime) listTypeChoice).getChoice();

        Assert.assertEquals(6960, ((Unsigned32) timeChoice).getVal());
    }

    /**
     * Verifies that a sml list type with sml time is encoded correctly
     *
     * @throws IOException
     */
    @Test
    public void encodeSmlValueChoiceListType() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

        SmlTime smlTime = new SmlTime(SmlTime.SECINDEX, new Unsigned32(6960));
        SmlListType smlValue = new SmlListType(smlTime);

        smlValue.encode(dataOutputStream);

        String smlValueStr = "72 62 01 72 62 01 65 00 00 1B 30";
        Utils.assertEqualsIgnoreWhitespace(smlValueStr, Utils.bytesToHex(byteArrayOutputStream.toByteArray()));
    }

    /**
     * Verifies that a sml list type with sml time is encoded correctly
     *
     * @throws IOException
     */
    @Test
    public void encodeSmlValueChoiceListTypeEmpty() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

        SmlListType smlValue = new SmlListType();
        smlValue.setOptional();
        smlValue.encode(dataOutputStream);

        Utils.assertEqualsIgnoreWhitespace("01", Utils.bytesToHex(byteArrayOutputStream.toByteArray()));
    }
}
