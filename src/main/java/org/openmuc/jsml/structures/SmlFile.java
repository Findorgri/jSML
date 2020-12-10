/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

import java.util.LinkedList;
import java.util.List;

public class SmlFile {

    private final List<SmlMessage> messages;

    /**
     * Creates an empty SML_File.
     */
    public SmlFile() {
        this(new LinkedList<SmlMessage>());
    }

    /**
     * Creates a SML_File with the given list of SML_Messages.
     * 
     * @param smlMessages
     *            the SML messages
     */
    public SmlFile(List<SmlMessage> smlMessages) {
        messages = smlMessages;
    }

    /**
     * Creates a new SML_File and adds the given SML_Message. Useful for attentionResponse.
     * 
     * @param smlFile
     *            the SML File
     */
    public SmlFile(SmlMessage smlFile) {
        this();
        add(smlFile);
    }

    /**
     * add SML_Message to SML_File
     * 
     * @param message
     *            the message
     */
    public void add(SmlMessage message) {
        messages.add(message);
    }

    /**
     * Get all SML messages.
     * 
     * @return all saved SML_Messages in this SML_File.
     */
    public List<SmlMessage> getMessages() {
        return messages;
    }
}
