/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.app;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.net.ssl.SSLServerSocketFactory;

import org.openmuc.jsml.GenericParser;
import org.openmuc.jsml.structures.EMessageBody;
import org.openmuc.jsml.structures.OctetString;
import org.openmuc.jsml.structures.SmlFile;
import org.openmuc.jsml.structures.SmlMessage;
import org.openmuc.jsml.structures.SmlMessageBody;
import org.openmuc.jsml.structures.SmlTreePath;
import org.openmuc.jsml.structures.Unsigned8;
import org.openmuc.jsml.structures.requests.SmlGetProfileListReq;
import org.openmuc.jsml.structures.requests.SmlPublicCloseReq;
import org.openmuc.jsml.structures.requests.SmlPublicOpenReq;
import org.openmuc.jsml.transport.TConnection;
import org.openmuc.jsml.transport.TConnectionListener;
import org.openmuc.jsml.transport.TSAP;

public class SampleTcpServer implements TConnectionListener {

    private static final String USAGE = "Usage: SampleSMLClient portnum [-s]";

    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            Print.println(USAGE);
            System.exit(1);
        }

        int port = -1;
        try {
            port = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            Print.printErr("Invalid port.");
            Print.println(USAGE);
            System.exit(1);
        }

        boolean useSSL = false;

        if (args.length > 1) {
            if (args[1].equals("-s")) {
                useSSL = true;
            }
            else {
                Print.println(USAGE);
                System.exit(1);
            }
        }

        SampleTcpServer sampleSMLServer = new SampleTcpServer();

        TSAP smlTSAP = null;
        if (useSSL == false) {
            smlTSAP = new TSAP(port, sampleSMLServer);
        }
        else {
            Print.println("using ssl");
            SSLServerSocketFactory sslServerSocketFactory = (SSLServerSocketFactory) SSLServerSocketFactory
                    .getDefault();
            smlTSAP = new TSAP(port, sampleSMLServer, sslServerSocketFactory);
        }

        Print.println("Starting to listen on port: " + port);
        smlTSAP.startListening();

    }

    @Override
    public void connectionIndication(TConnection smlTConnection) {

        Print.println("A client has connected.");

        SmlFile smlFile = null;
        try {
            Print.println("Listening for an SML message from client");
            smlFile = smlTConnection.getSMLFile();
        } catch (IOException e) {
            Print.println(e.getMessage());
            return;
        }
        Print.println("Got an SML message from the client");

        GenericParser.printFile(smlFile);

        Print.println("Sending a GetProfileListRequest");
        try {
            smlTConnection.send(createGetProfileListRequest(1, 1));
        } catch (IOException e) {
            Print.printErr("unable to send GetProfileListRequest. Exception: " + e.getMessage());
            Print.printErr(e.getMessage());
        }

        Print.println("Diconnecting (closing port)\n");
        smlTConnection.close();

    }

    @Override
    public void serverStoppedListeningIndication(IOException e) {
        Print.println("Server stopped listening: " + e);
    }

    /**
     * This sample builds a SML-File containing a PublicOpenRequest, a GetProfileListRequest and a PublicCloseRequest.
     * 
     * @param groupNumber
     *            group number
     * @param transactionIDCounter
     *            transaction ID counter
     * @return encoded stream as bytes array
     */
    public static byte[] createGetProfileListRequest(int groupNumber, int transactionIDCounter) {

        ByteArrayOutputStream bs = new ByteArrayOutputStream(50);
        DataOutputStream os = new DataOutputStream(bs);

        // --- create a SML_Message with SML_PublicOpenRequest to start the
        // SML_File
        // make the transacionId and increment the transactionIDCounter
        OctetString transactionId = new OctetString(("" + transactionIDCounter++).getBytes());
        // make the group number and increment the group number
        Unsigned8 groupNum = new Unsigned8(groupNumber++);
        // continue on error
        Unsigned8 abortOnError = new Unsigned8(0x00);
        // create clientId
        int iClientId = 0x10;
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
        // make new group number
        groupNum = new Unsigned8(groupNumber++);
        SmlTreePath treePath = new SmlTreePath(new OctetString[] { new OctetString("testtreepath".getBytes()) });
        SmlGetProfileListReq getProfileListRequest = new SmlGetProfileListReq(null, null, null, null, null, null,
                treePath, null, null);
        smlMessageBody = new SmlMessageBody(EMessageBody.GET_PROFILE_LIST_REQUEST.id(), getProfileListRequest);
        SmlMessage getProfileListRequestMessage = new SmlMessage(transactionId, groupNum, abortOnError, smlMessageBody);

        // --- create a SML_Message with SML_PublicCloseRequest to end the
        // SML_File
        // make new transactionId
        transactionId = new OctetString(("" + transactionIDCounter).getBytes());
        // make new group number
        groupNum = new Unsigned8(groupNumber);
        SmlPublicCloseReq closeRequest = new SmlPublicCloseReq(null);
        smlMessageBody = new SmlMessageBody(EMessageBody.CLOSE_REQUEST.id(), closeRequest);
        SmlMessage closeRequestMessage = new SmlMessage(transactionId, groupNum, abortOnError, smlMessageBody);

        // encode every SML_Messages and write the encoded data to the stream os
        try {
            openRequestMessage.encode(os);
            getProfileListRequestMessage.encode(os);
            closeRequestMessage.encode(os);
        } catch (IOException e) {
            Print.printErr(e.getMessage());
            System.exit(1);
        }

        return bs.toByteArray();
    }

}
