/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.transport;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openmuc.jsml.structures.EMessageBody;
import org.openmuc.jsml.structures.OctetString;
import org.openmuc.jsml.structures.SmlFile;
import org.openmuc.jsml.structures.SmlMessage;
import org.openmuc.jsml.structures.SmlMessageBody;
import org.openmuc.jsml.structures.Unsigned8;
import org.openmuc.jsml.structures.responses.SmlAttentionRes;

public class TConnection {

    private static final Logger logger = Logger.getLogger(TConnection.class.getName());

    private Socket socket;
    private DataOutputStream os;
    private DataInputStream is;

    public TConnection(Socket socket, int messageTimeout, int messageFragmentTimeout) throws IOException {
        this.socket = socket;
        os = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        is = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

    }

    public void send(byte[] smlPackage) throws IOException {
        Transport transport = new Transport();
        transport.send(os, smlPackage);
    }

    /**
     * sends an SML_AttentionRes with sock
     *
     * @param attentionType
     *            see SML_AttentionRes
     * @param message
     *            OPTIONAL human readable message
     * @param serverID
     *            id of this machine
     * @param faultyMessage
     *            the SML_Message which caused this attentionResponse
     */
    public void sendAttentionResponse(OctetString attentionType, String message, OctetString serverID,
            SmlMessage faultyMessage) {
        ByteArrayOutputStream bs = new ByteArrayOutputStream(50);
        DataOutputStream os = new DataOutputStream(bs);
        // continue on error
        Unsigned8 abortOnError = new Unsigned8(0x00);
        OctetString attentionMsg = null;
        if (message != null) {
            attentionMsg = new OctetString(message.getBytes());
        }
        // send an UNEXPECTED_SMLMESSAGE AttentionResponse back to the client
        SmlAttentionRes attentionres = new SmlAttentionRes(serverID, attentionType, attentionMsg, null);
        SmlMessageBody attentionresBody = new SmlMessageBody(EMessageBody.ATTENTION_RESPONSE.id(), attentionres);
        // take the transactionId and group number from the faulty SML_Message
        SmlMessage attentionmessage = new SmlMessage(faultyMessage.getTransactionId(), faultyMessage.getGroupNo(),
                abortOnError, attentionresBody);

        // encode the attentionResponse message and write the encoded data to
        // the stream os
        try {
            attentionmessage.encode(os);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "unable to encode attention message", e);
        }
        // send it to the client
        try {
            send(bs.toByteArray());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "unable to send message to client", e);
        }
    }

    public SmlFile getSMLFile() throws IOException {
        Transport transport = new Transport();
        return transport.getSMLFile(is);
    }

    public void close() {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                // ignore
            }
            os = null;
        }

        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                // ignore
            }
            is = null;
        }

        if (socket != null && socket.isBound()) {
            try {
                socket.close();
            } catch (IOException e) {
                // ignore
            }
            socket = null;
        }
    }

}
