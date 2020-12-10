/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.test.structures;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.openmuc.jsml.structures.Integer16;
import org.openmuc.jsml.structures.Integer32;
import org.openmuc.jsml.structures.Integer64;
import org.openmuc.jsml.structures.ListOfSmlTree;
import org.openmuc.jsml.structures.OctetString;
import org.openmuc.jsml.structures.SmlTree;
import org.openmuc.jsml.structures.Unsigned16;
import org.openmuc.jsml.structures.Unsigned32;
import org.openmuc.jsml.structures.Unsigned64;

public class EncodingDecodingTest {

    @Test
    public void encodeListOfSMLTreeWithManyElements() throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream(50);
        DataOutputStream os = new DataOutputStream(bs);
        SmlTree[] sml_Trees = new SmlTree[25];
        for (int i = 0; i < 25; i++) {
            sml_Trees[i] = new SmlTree(new OctetString("test"), null, null);
        }
        ListOfSmlTree list_of_SML_Tree = new ListOfSmlTree(sml_Trees);

        list_of_SML_Tree.encode(os);

        Assert.assertEquals(bs.toByteArray()[0], (byte) 0xf1);
        Assert.assertEquals(bs.toByteArray()[1], (byte) 0x09);
    }

    @Test
    public void encodeDecodeInt() throws IOException {
        int16(Short.MIN_VALUE);
        int16(Short.MAX_VALUE);

        int32(Integer.MIN_VALUE);
        int32(Integer.MAX_VALUE);

        int64(Long.MIN_VALUE);
        int64(Long.MAX_VALUE);

        uint64(Long.MAX_VALUE);

        uint32(Integer.MAX_VALUE + 100L);

        uint16(Short.MAX_VALUE * 2 + 1);
    }

    private void int16(short val) throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        DataOutputStream os = new DataOutputStream(bs);

        Integer16 input = new Integer16(val);
        input.encode(os);

        ByteArrayInputStream bis = new ByteArrayInputStream(bs.toByteArray());
        DataInputStream is = new DataInputStream(bis);

        Integer16 output = new Integer16();
        boolean decoded = output.decode(is);

        Assert.assertTrue(decoded);
        Assert.assertEquals(val, output.getVal());
    }

    private void int32(int val) throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        DataOutputStream os = new DataOutputStream(bs);

        Integer32 input = new Integer32(val);
        input.encode(os);

        ByteArrayInputStream bis = new ByteArrayInputStream(bs.toByteArray());
        DataInputStream is = new DataInputStream(bis);

        Integer32 output = new Integer32();
        boolean decoded = output.decode(is);

        Assert.assertTrue(decoded);
        Assert.assertEquals(val, output.getVal());
    }

    private void int64(long val) throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        DataOutputStream os = new DataOutputStream(bs);

        Integer64 input = new Integer64(val);
        input.encode(os);

        ByteArrayInputStream bis = new ByteArrayInputStream(bs.toByteArray());
        DataInputStream is = new DataInputStream(bis);
        Integer64 output = new Integer64();
        boolean decoded = output.decode(is);

        Assert.assertTrue(decoded);
        Assert.assertEquals(val, output.getVal());
    }

    private void uint64(long val) throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        DataOutputStream os = new DataOutputStream(bs);

        Unsigned64 input = new Unsigned64(val);
        input.encode(os);

        ByteArrayInputStream bis = new ByteArrayInputStream(bs.toByteArray());
        DataInputStream is = new DataInputStream(bis);
        Unsigned64 output = new Unsigned64();
        boolean decoded = output.decode(is);

        Assert.assertTrue(decoded);
        Assert.assertEquals(val, output.getVal());
    }

    private void uint32(long val) throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        DataOutputStream os = new DataOutputStream(bs);

        Unsigned32 input = new Unsigned32(val);
        input.encode(os);

        ByteArrayInputStream bis = new ByteArrayInputStream(bs.toByteArray());
        DataInputStream is = new DataInputStream(bis);
        Unsigned32 output = new Unsigned32();
        boolean decoded = output.decode(is);

        Assert.assertTrue(decoded);
        Assert.assertEquals(val, output.getLongValue());
    }

    private void uint16(int val) throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        DataOutputStream os = new DataOutputStream(bs);

        Unsigned16 input = new Unsigned16(val);
        input.encode(os);

        ByteArrayInputStream bis = new ByteArrayInputStream(bs.toByteArray());
        DataInputStream is = new DataInputStream(bis);
        Unsigned16 output = new Unsigned16();
        boolean decoded = output.decode(is);

        Assert.assertTrue(decoded);
        Assert.assertEquals(val, output.getVal());
    }

}
