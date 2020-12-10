/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.transport;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.openmuc.jrxtx.SerialPort;

/**
 * Class to send messages via OutputStream
 */
public class SerialTransmitter {

    private final DataOutputStream os;

    public SerialTransmitter(SerialPort serialPort) throws IOException {
        os = new DataOutputStream(new BufferedOutputStream(serialPort.getOutputStream()));
    }

    public void write(byte[] message) throws IOException {
        new Transport().send(os, message);
    }

    /**
     * @deprecated use {@link #close()} instead
     * @throws IOException
     *             if an I/O error occurs.
     */
    @Deprecated
    public void closeStream() throws IOException {
        close();
    }

    public void close() throws IOException {
        os.close();
    }
}
