package org.openmuc.jsml.test.structures;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openmuc.jsml.structures.Integer32;
import org.openmuc.jsml.structures.SmlFile;
import org.openmuc.jsml.structures.SmlList;
import org.openmuc.jsml.structures.SmlListEntry;
import org.openmuc.jsml.structures.SmlMessage;
import org.openmuc.jsml.structures.Unsigned16;
import org.openmuc.jsml.structures.Unsigned32;
import org.openmuc.jsml.structures.Unsigned64;
import org.openmuc.jsml.structures.responses.SmlGetListRes;
import org.openmuc.jsml.test.Utils;
import org.openmuc.jsml.transport.Transport;

public class DecodeFileTest {

    static final byte[] EASYMETER_Q3BA1022 = Utils
            .hexStringToByteArray("1B1B1B1B010101017605043B66D962006200726500000101760101074553595133420B064553590"
                    + "104C5FA2EF701016338BE007605043B66DA6200620072650000070177010B064553590104C5FA2EF70172620165"
                    + "0B1A82477977078181C78203FF01010101044553590177070100010800FF0101621E52FC690000001408499D070"
                    + "177070100020800FF0101621E52FC6900000021E2BD91830177070100010801FF0101621E520165000D20570177"
                    + "070100010802FF0101621E520165000000880177070100020801FF0101621E5201650016348D017707010002080"
                    + "2FF0101621E520165000000860177070100010700FF0101621B52FE5500002AC80177070100600505FF01010101"
                    + "630180010101635257007605043B66DB62006200726500000201710163274400001B1B1B1B1A013050");

    @Test
    public void testEasyMeterQ3BA1022() throws IOException {
        SmlListEntry[] valListEntry = decode(EASYMETER_Q3BA1022);

        checkUnsigned64(valListEntry[1], 30, (byte) -4, 86038387975l);
        checkUnsigned32(valListEntry[6], 30, (byte) 1, 134);
        checkInteger32(valListEntry[7], 27, (byte) -2, 10952);
        checkUnsigned16(valListEntry[8], 0, (byte) 0, 384);
    }

    private SmlListEntry[] decode(byte[] message) throws IOException {
        DataInputStream is = new DataInputStream(new ByteArrayInputStream(message));
        SmlFile smlFile = new Transport().getSMLFile(is);
        List<SmlMessage> smlMessages = smlFile.getMessages();
        printMessages(smlMessages);

        SmlGetListRes smlGetListRes = smlMessages.get(1).getMessageBody().getChoice();
        SmlList valList = smlGetListRes.getValList();
        SmlListEntry[] valListEntry = valList.getValListEntry();
        return valListEntry;
    }

    private void printMessages(List<SmlMessage> smlMessages) {
        for (SmlMessage smlMessage : smlMessages) {
            System.out.println(smlMessage.getMessageBody().getChoice() + "\n");
        }
    }

    private void checkUnsigned64(SmlListEntry smlListEntry, int expectedUnit, byte expectedScalar, long expectedValue) {
        checkUnitAndScalar(smlListEntry, expectedUnit, expectedScalar);
        Assert.assertEquals(expectedValue, ((Unsigned64) smlListEntry.getValue().getChoice()).getVal());
    }

    private void checkInteger32(SmlListEntry smlListEntry, int expectedUnit, byte expectedScalar, int expectedValue) {
        checkUnitAndScalar(smlListEntry, expectedUnit, expectedScalar);
        Assert.assertEquals(expectedValue, ((Integer32) smlListEntry.getValue().getChoice()).getVal());
    }

    private void checkUnsigned32(SmlListEntry smlListEntry, int expectedUnit, byte expectedScalar, long expectedValue) {
        checkUnitAndScalar(smlListEntry, expectedUnit, expectedScalar);
        Assert.assertEquals(expectedValue, ((Unsigned32) smlListEntry.getValue().getChoice()).getVal());
    }

    private void checkUnsigned16(SmlListEntry smlListEntry, int expectedUnit, byte expectedScalar, int expectedValue) {
        checkUnitAndScalar(smlListEntry, expectedUnit, expectedScalar);
        Assert.assertEquals(expectedValue, ((Unsigned16) smlListEntry.getValue().getChoice()).getVal());
    }

    private void checkUnitAndScalar(SmlListEntry smlListEntry, int expectedUnit, byte expectedScalar) {
        Assert.assertEquals(expectedUnit, smlListEntry.getUnit().getVal());
        Assert.assertEquals(expectedScalar, smlListEntry.getScaler().getVal());
    }

}
