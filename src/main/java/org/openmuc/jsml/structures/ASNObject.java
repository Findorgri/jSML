/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class ASNObject {

    private boolean optional;
    private boolean selected;

    public ASNObject() {
        this.optional = false;
        this.selected = false;
    }

    /**
     * encodes this object and writes it to the stream os.
     * 
     * @param os
     *            output stream
     * @throws IOException
     *             if something went wrong while writing to the stream
     */
    public abstract void encode(DataOutputStream os) throws IOException;

    /**
     * decodes the data from the InputStream and writes it to an object
     * 
     * @param is
     *            input stream
     * @return true if successfully decoded
     * @throws IOException
     *             if something went wrong while reading from the stream
     */
    public abstract boolean decode(DataInputStream is) throws IOException;

    /**
     * Marks this object as optional.
     */
    public void setOptional() {
        this.optional = true;
    }

    /**
     * Marks this object as selected.
     */
    public void setSelected() {
        selected = true;
    }

    /**
     * set selected
     * 
     * @param selected
     *            true if selected
     */
    protected void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * returns if this object is selected
     * 
     * @return boolean
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * returns if this object is optional
     * 
     * @return boolean
     */
    public boolean isOptional() {
        return optional;
    }
}
