/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.test.tl;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmuc.jsml.structures.EMessageBody;
import org.openmuc.jsml.structures.Integer32;
import org.openmuc.jsml.structures.OctetString;
import org.openmuc.jsml.structures.SmlList;
import org.openmuc.jsml.structures.SmlListEntry;
import org.openmuc.jsml.structures.SmlMessage;
import org.openmuc.jsml.structures.SmlMessageBody;
import org.openmuc.jsml.structures.SmlStatus;
import org.openmuc.jsml.structures.SmlUnit;
import org.openmuc.jsml.structures.SmlValue;
import org.openmuc.jsml.structures.Unsigned8;
import org.openmuc.jsml.structures.requests.SmlGetListReq;
import org.openmuc.jsml.structures.requests.SmlPublicCloseReq;
import org.openmuc.jsml.structures.requests.SmlPublicOpenReq;
import org.openmuc.jsml.structures.responses.SmlGetListRes;
import org.openmuc.jsml.structures.responses.SmlPublicOpenRes;
import org.openmuc.jsml.transport.TConnection;
import org.openmuc.jsml.transport.TConnectionListener;
import org.openmuc.jsml.transport.TSAP;

public class ClientServerTCPITest implements TConnectionListener {

    public static final int serverPort = 53214;

    @Before
    public void startTCPServer() throws IOException {
        TSAP sml_tSAP = null;

        sml_tSAP = new TSAP(serverPort, this);

        sml_tSAP.startListening();
    }

    @Test
    public void testClient() throws UnknownHostException, IOException {

        /* Create client TSAP */
        TSAP sml_tSAP = new TSAP();

        TConnection sml_tConnection = sml_tSAP.connectTo(InetAddress.getByName("localhost"), serverPort, 0);

        byte[] getListRequest = createGetListRequest(0, 1);
        // Assert.assertTrue("getListRequest not coded correctly",
        // Arrays.equals(getListRequest, getListRequestCheck));
        sml_tConnection.send(getListRequest);
        // sml_tConnection.receive();
        sml_tConnection.getSMLFile();
    }

    /************************************
     * Client
     */

    /**
     * This sample builds a SML-File containing a PublicOpenRequest, a GetListRequest and a PublicCloseRequest
     * 
     * @return
     */
    public static byte[] createGetListRequest(int groupNumber, int transactionIDCounter) {

        ByteArrayOutputStream bs = new ByteArrayOutputStream(50);
        DataOutputStream os = new DataOutputStream(bs);

        // --- create a SML_Message with SML_PublicOpenRequest to start the
        // SML_File
        // make the transacionId and increment the transactionIDCounter
        OctetString transactionId = new OctetString(("" + transactionIDCounter++).getBytes());
        // make the group number and increment the group number
        Unsigned8 groupNum = new Unsigned8(groupNumber);
        // continue on error
        Unsigned8 abortOnError = new Unsigned8(0x00);
        // create clientId
        int iClientId = 0x1;
        OctetString clientId = new OctetString(("" + iClientId).getBytes());
        // create reqFileId
        int iReqFileId = 0x11;
        OctetString reqFileId = new OctetString(("" + iReqFileId).getBytes());
        // create the SML_Message
        SmlPublicOpenReq openRequest = new SmlPublicOpenReq(null, clientId, reqFileId, null, null, null, null);
        SmlMessageBody smlMessageBody = new SmlMessageBody(EMessageBody.OPEN_REQUEST.id(), openRequest);
        SmlMessage openRequestMessage = new SmlMessage(transactionId, groupNum, abortOnError, smlMessageBody);

        // --- create a SML_Message with SML_GetProfileListReq with test values
        // make new transactionId
        transactionId = new OctetString(("" + transactionIDCounter++).getBytes());
        OctetString listName = new OctetString(new byte[] { 0x01, 0x00, 0x01, 0x08, 0x01, (byte) 0xff });
        SmlGetListReq getListRequest = new SmlGetListReq(clientId, null, null, null, listName);
        smlMessageBody = new SmlMessageBody(EMessageBody.GET_LIST_REQUEST.id(), getListRequest);
        SmlMessage getListRequestMessage = new SmlMessage(transactionId, groupNum, abortOnError, smlMessageBody);

        // --- create a SML_Message with SML_PublicCloseRequest to end the
        // SML_File
        // make new transactionId
        transactionId = new OctetString(("" + transactionIDCounter++).getBytes());
        SmlPublicCloseReq closeRequest = new SmlPublicCloseReq(null);
        smlMessageBody = new SmlMessageBody(EMessageBody.CLOSE_REQUEST.id(), closeRequest);
        SmlMessage closeRequestMessage = new SmlMessage(transactionId, groupNum, abortOnError, smlMessageBody);

        // encode every SML_Messages and write the encoded data to the stream os
        try {
            openRequestMessage.encode(os);
            getListRequestMessage.encode(os);
            closeRequestMessage.encode(os);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return bs.toByteArray();
    }

    /**
     * This sample builds a SML-File containing a PublicOpenResponse, a GetListResponse and a PublicCloseResponse
     * 
     * @return
     */
    public static byte[] createGetListResponse(int groupNumber, int transactionIDCounter) {

        ByteArrayOutputStream bs = new ByteArrayOutputStream(50);
        DataOutputStream os = new DataOutputStream(bs);

        // --- create a SML_Message with SML_PublicOpenRequest to start the
        // SML_File
        // make the transacionId and increment the transactionIDCounter
        OctetString transactionId = new OctetString(("" + transactionIDCounter++).getBytes());
        // make the group number and increment the group number
        Unsigned8 groupNum = new Unsigned8(groupNumber);
        // continue on error
        Unsigned8 abortOnError = new Unsigned8(0x00);
        // create clientId
        int iServerId = 0x1;
        OctetString serverId = new OctetString(("" + iServerId).getBytes());
        // create reqFileId
        int iReqFileId = 0x11;
        OctetString reqFileId = new OctetString(("" + iReqFileId).getBytes());
        // create the SML_Message
        SmlPublicOpenRes openResponse = new SmlPublicOpenRes(null, null, reqFileId, serverId, null, null);
        SmlMessageBody smlMessageBody = new SmlMessageBody(EMessageBody.OPEN_RESPONSE.id(), openResponse);
        SmlMessage openRequestMessage = new SmlMessage(transactionId, groupNum, abortOnError, smlMessageBody);

        // --- create a SML_Message with SML_GetProfileListReq with test values
        // make new transactionId
        transactionId = new OctetString(("" + transactionIDCounter++).getBytes());

        new SmlStatus(new Unsigned8(0));
        SmlUnit unit = new SmlUnit(new Unsigned8(30));
        SmlValue value = new SmlValue(new Integer32(0));

        SmlListEntry[] valListEntries = new SmlListEntry[1];
        valListEntries[0] = new SmlListEntry(new OctetString(new byte[] { 0x01, 0x00, 0x01, 0x08, 0x01, (byte) 0xff }),
                null, null, unit, null, value, null);
        SmlList valList = new SmlList(valListEntries);
        SmlGetListRes getListResponse = new SmlGetListRes(null, serverId, null, null, valList, null, null);

        smlMessageBody = new SmlMessageBody(EMessageBody.GET_LIST_RESPONSE.id(), getListResponse);
        SmlMessage getListRequestMessage = new SmlMessage(transactionId, groupNum, abortOnError, smlMessageBody);

        // --- create a SML_Message with SML_PublicCloseRequest to end the
        // SML_File
        // make new transactionId
        transactionId = new OctetString(("" + transactionIDCounter++).getBytes());
        SmlPublicCloseReq closeRequest = new SmlPublicCloseReq(null);
        smlMessageBody = new SmlMessageBody(EMessageBody.CLOSE_RESPONSE.id(), closeRequest);
        SmlMessage closeRequestMessage = new SmlMessage(transactionId, groupNum, abortOnError, smlMessageBody);

        // encode every SML_Messages and write the encoded data to the stream os
        try {
            openRequestMessage.encode(os);
            getListRequestMessage.encode(os);
            closeRequestMessage.encode(os);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return bs.toByteArray();
    }

    /************************************
     * Server
     * 
     * @throws IOException
     */

    @Override
    public void connectionIndication(TConnection sml_tConnection) {
        try {
            // sml_tConnection.receive();
            sml_tConnection.getSMLFile();
            byte[] getListResponse = createGetListResponse(0, 1);
            sml_tConnection.send(getListResponse);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }

    }

    @Override
    public void serverStoppedListeningIndication(IOException e) {
        // TODO Auto-generated method stub

    }

}
