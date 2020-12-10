/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.transport;

import java.io.IOException;
import java.net.Socket;

/*
 * The class extends Thread and handles SML connections. Once a connection has been initiated
 * it gives the connection in form of the Connection class to the ConnectionListener and the thread is closed.
 */
public class ConnectionHandler extends Thread {

    private final Socket socket;
    private final ServerThread serverThread;

    ConnectionHandler(Socket socket, ServerThread serverThread) {
        this.socket = socket;
        this.serverThread = serverThread;
    }

    @Override
    public void run() {
        try {
            TConnection tConnection;
            try {
                tConnection = new TConnection(socket, serverThread.messageTimeout, serverThread.messageFragmentTimeout);
            } catch (IOException e) {
                System.err.println("Exception occurred when someone tried to connect.");
                return;
            }

            if (serverThread.isAlive()) {
                serverThread.connectionIndication(tConnection);
            }

            if (tConnection != null) {
                tConnection.close();
            }

        } finally {
            serverThread.removeHandler(this);
        }
    }
}
