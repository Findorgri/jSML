package org.openmuc.jsml.test.structures;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.openmuc.jsml.structures.ASNObject;
import org.openmuc.jsml.structures.Integer16;
import org.openmuc.jsml.structures.SmlTime;
import org.openmuc.jsml.structures.SmlTimestamp;
import org.openmuc.jsml.structures.SmlTimestampLocal;
import org.openmuc.jsml.structures.Unsigned32;
import org.openmuc.jsml.test.Utils;

public class SmlTimeTest {

    /**
     * Verifies that a sml time object which contains a local offset and a season offset will be decoded correctly
     *
     * @throws IOException
     */
    @Test
    public void decodeTimestampWithLocalTime() throws IOException {
        String smlTimeString = "72 6203 73 655B6D5218 53003C 53003C";
        byte[] data = Utils.hexStringToByteArray(smlTimeString);
        DataInputStream is = new DataInputStream(new ByteArrayInputStream(data));

        SmlTime smlTime = new SmlTime();
        Assert.assertTrue(smlTime.decode(is));
        Assert.assertNotNull(smlTime);
        ASNObject timeChoice = smlTime.getChoice();
        Assert.assertEquals(SmlTimestampLocal.class, timeChoice.getClass());
        SmlTimestampLocal timestampLocal = (SmlTimestampLocal) timeChoice;
        Assert.assertEquals(SmlTime.TIMESTAMP_LOCAL, smlTime.getTag().getVal());
        Assert.assertEquals(1533891096, timestampLocal.getTimestamp().getVal());
        Assert.assertEquals(60, timestampLocal.getLocalOffset().getVal());
        Assert.assertEquals(60, timestampLocal.getSeasonTimeOffset().getVal());
    }

    /**
     * Verifies that a sml time object which contains a local offset and a season offset will be encoded correctly
     *
     * @throws IOException
     */
    @Test
    public void encodeTimestampWithLocalTime() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

        Integer16 seasonOffset = new Integer16((short) 60);
        Integer16 localOffset = new Integer16((short) 60);
        SmlTimestamp smlTimestamp = new SmlTimestamp(new Unsigned32(1533891096));
        SmlTimestampLocal timestampLocal = new SmlTimestampLocal(smlTimestamp, localOffset, seasonOffset);
        SmlTime smlTime = new SmlTime(SmlTime.TIMESTAMP_LOCAL, timestampLocal);
        smlTime.encode(dataOutputStream);

        String smlTimeString = "72 6203 73 655B6D5218 53003C 53003C";
        Utils.assertEqualsIgnoreWhitespace(smlTimeString, Utils.bytesToHex(byteArrayOutputStream.toByteArray()));
    }

    /**
     * Verifies that a sml time object which contains secIndex values will be encoded correctly
     *
     * @throws IOException
     */
    @Test
    public void decodeTimestampWithSecIndex() throws IOException {
        String smlTimeString = "72 6201 65 00 00 1B 30";
        byte[] data = Utils.hexStringToByteArray(smlTimeString);
        DataInputStream is = new DataInputStream(new ByteArrayInputStream(data));

        SmlTime smlTime = new SmlTime();
        Assert.assertTrue(smlTime.decode(is));
        Assert.assertNotNull(smlTime);
        Assert.assertEquals(SmlTime.SECINDEX, smlTime.getTag().getVal());
        Assert.assertEquals(6960, ((Unsigned32) smlTime.getChoice()).getVal());
    }

    /**
     * Verifies that a sml time object which contains a smlTimestamp object will be read correctly
     *
     * @throws IOException
     */
    @Test
    public void decodeTimestampWithSmlTimestamp() throws IOException {
        String smlTimeString = "72 6202 65 00 00 04 B2";

        byte[] data = Utils.hexStringToByteArray(smlTimeString);
        DataInputStream is = new DataInputStream(new ByteArrayInputStream(data));

        SmlTime time = new SmlTime();
        Assert.assertTrue(time.decode(is));
        Assert.assertNotNull(time);
        Assert.assertEquals(SmlTime.TIMESTAMP, time.getTag().getVal());
        ASNObject choice = time.getChoice();
        Assert.assertEquals(SmlTimestamp.class, choice.getClass());
        SmlTimestamp timestamp = (SmlTimestamp) choice;
        Assert.assertEquals(1202, timestamp.getVal());
    }
}
