/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.transport;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.openmuc.jrxtx.SerialPort;
import org.openmuc.jsml.structures.SmlFile;

/**
 * This class can be used to read SML Messages over a serial interface
 */
public class SerialReceiver {

    private final DataInputStream is;

    public SerialReceiver(SerialPort serialPort) throws IOException {
        this.is = new DataInputStream(new BufferedInputStream(serialPort.getInputStream()));
    }

    public SmlFile getSMLFile() throws IOException {
        Transport transport = new Transport();
        return transport.getSMLFile(is);
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
        is.close();
    }
}
