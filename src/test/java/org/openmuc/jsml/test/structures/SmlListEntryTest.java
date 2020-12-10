package org.openmuc.jsml.test.structures;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.openmuc.jsml.structures.ASNObject;
import org.openmuc.jsml.structures.SmlListEntry;
import org.openmuc.jsml.structures.SmlListType;
import org.openmuc.jsml.structures.SmlTime;
import org.openmuc.jsml.structures.Unsigned32;
import org.openmuc.jsml.test.Utils;

public class SmlListEntryTest {

    /**
     * The message is structured as following: 77 valListEntry(Sequence): 07810060080001 objName: 81 00 60 08 00 01 01
     * status: not set 01 valTime: not set 01 unit: not set 01 scaler: not se 72 value(Choice): 6201 value: 1 =>
     * SML_Time (0x01) 72 SML_TIME(Choice): 6201 value: 1 => SML_Time (0x01) 6500001B30 secIndex: 6960 01
     * valueSignature: not set
     *
     */
    private static final String SML_VAL_LIST_ENTRY_ONLY_TIME = "77 07 81 00 60 08 00 01 01 01 01 01 72 62 01 72 62 01 65 00 00 1B 30 01";

    @Test
    public void test_val_list_entry_only_time() throws IOException {
        SmlListEntry smlListEntry = new SmlListEntry();
        byte[] data = Utils.hexStringToByteArray(SML_VAL_LIST_ENTRY_ONLY_TIME);
        DataInputStream is = new DataInputStream(new ByteArrayInputStream(data));
        boolean decode = smlListEntry.decode(is);
        Assert.assertTrue(decode);
        Assert.assertNotNull(smlListEntry.getObjName());
        Assert.assertEquals("81 00 60 08 00 01", smlListEntry.getObjName().toHexString());
        Assert.assertEquals(0, smlListEntry.getValTime().getTag().getVal());
        Assert.assertEquals("EMPTY", smlListEntry.getUnit().toString());
        Assert.assertEquals(0x00, smlListEntry.getScaler().getVal());
        Assert.assertNull(smlListEntry.getValueSignature().getValue());
        Assert.assertNotNull(smlListEntry.getValTime());

        ASNObject choice = smlListEntry.getValue().getChoice();
        Assert.assertEquals(SmlListType.class, choice.getClass());
        ASNObject listEntryChoice = ((SmlListType) choice).getChoice();

        Assert.assertEquals(SmlTime.class, listEntryChoice.getClass());
        ASNObject timeChoice = ((SmlTime) listEntryChoice).getChoice();
        Assert.assertEquals(Unsigned32.class, timeChoice.getClass());
        Assert.assertEquals(6960, ((Unsigned32) timeChoice).getVal());
    }

}
