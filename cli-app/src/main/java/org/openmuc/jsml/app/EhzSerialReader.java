/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openmuc.jrxtx.DataBits;
import org.openmuc.jrxtx.FlowControl;
import org.openmuc.jrxtx.Parity;
import org.openmuc.jrxtx.SerialPort;
import org.openmuc.jrxtx.SerialPortBuilder;
import org.openmuc.jrxtx.StopBits;
import org.openmuc.jsml.GenericParser;
import org.openmuc.jsml.internal.cli.CliParameter;
import org.openmuc.jsml.internal.cli.CliParameterBuilder;
import org.openmuc.jsml.internal.cli.CliParseException;
import org.openmuc.jsml.internal.cli.CliParser;
import org.openmuc.jsml.internal.cli.StringCliParameter;
import org.openmuc.jsml.structures.SmlFile;
import org.openmuc.jsml.transport.SerialReceiver;

/**
 * Sample application to read SML Messages via serial interface. Tested with eHZ Meter
 */
public class EhzSerialReader {

    public static void main(String[] args) throws IOException {
        StringCliParameter serialPortName = new CliParameterBuilder("-sp")
                .setDescription(
                        "The serial port used for communication. Examples are /dev/ttyS0 (Linux) or COM1 (Windows).")
                .setMandatory()
                .buildStringParameter("serial_port");

        List<CliParameter> cliParameters = new ArrayList<>();
        cliParameters.add(serialPortName);

        CliParser cliParser = new CliParser("jsml-ehz-reader",
                "Reads an eHz using its optical port over a serial connection.");
        cliParser.addParameters(cliParameters);

        try {
            cliParser.parseArguments(args);
        } catch (CliParseException e1) {
            Print.printErr("Error parsing command line parameters: " + e1.getMessage());
            Print.println(cliParser.getUsageString());
            System.exit(1);
        }

        SerialPort serialPort = setupSerialPort(serialPortName.getValue());

        SerialReceiver receiver = new SerialReceiver(serialPort);

        for (int j = 0; j < 2; j++) {
            Print.println("\n\n == round " + j + " == \n\n");
            SmlFile smlFile = receiver.getSMLFile();
            Print.println("Got SML_File");

            Print.println("======================== ");
            GenericParser.printFile(smlFile);
        }

        receiver.close();
        serialPort.close();

        Print.println("\n *** Reader finished ***");
    }

    private static SerialPort setupSerialPort(String serialPortName) throws IOException {
        SerialPortBuilder serialPortBuilder = SerialPortBuilder.newBuilder(serialPortName);
        serialPortBuilder.setBaudRate(9600)
                .setDataBits(DataBits.DATABITS_8)
                .setStopBits(StopBits.STOPBITS_1)
                .setParity(Parity.NONE)
                .setFlowControl(FlowControl.RTS_CTS);

        return serialPortBuilder.build();
    }

}
