/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.transport;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class extends Thread. It is started by SML_TSAP and listens on a socket for connections and hands them to the
 * ConnectionHandler class. It notifies ConnectionListener if the socket is closed.
 */
class ServerThread extends Thread {

    private final ServerSocket serverSocket;
    private int numConnections = 0;
    private final TSAP tSAP;
    protected int maxTPDUSizeParam;
    private final int maxConnections;
    protected int messageTimeout;
    protected int messageFragmentTimeout;

    protected ServerThread(TSAP tSAP, ServerSocket socket, int maxConnections, int messageTimeout,
            int messageFragmentTimeout) {
        this.tSAP = tSAP;
        serverSocket = socket;
        this.maxConnections = maxConnections;
        this.messageTimeout = messageTimeout;
        this.messageFragmentTimeout = messageFragmentTimeout;
    }

    @Override
    public void run() {

        Socket clientSocket = null;

        while (true) {
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                tSAP.getConnectionListener().serverStoppedListeningIndication(e);

                return;
            }

            if (numConnections < maxConnections) {
                numConnections++;
                ConnectionHandler myConnectionHandler = new ConnectionHandler(clientSocket, this);
                myConnectionHandler.start();
            }
            else {
                System.err.println(
                        "Transport Layer Server: Maximum number of connections reached. Ignoring connection request.");
            }

        }

    }

    protected void connectionIndication(TConnection tConnection) {
        tSAP.getConnectionListener().connectionIndication(tConnection);
    }

    protected void removeHandler(ConnectionHandler handler) {
        numConnections--;
    }

    /**
     * stops listening on the port but does not close all connections
     */
    public void stopServer() {
        if (serverSocket != null && serverSocket.isBound()) {
            try {
                serverSocket.close();
            } catch (IOException e) {
            }
        }
    }

}
