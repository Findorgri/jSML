/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Choice extends ASNObject {

    private Unsigned8 tag;
    /**
     * data of the choice-object
     */
    protected ASNObject choice;

    public Choice() {
        setTag(new Unsigned8());
    }

    @Override
    public void encode(DataOutputStream os) throws IOException {
        if (isOptional() && !isSelected()) {
            os.writeByte(0x01);
            return;
        }
        os.writeByte(0x72);
        getTag().encode(os);
        choice.encode(os);
    }

    public ASNObject getChoice() {
        return choice;
    }

    /**
     * Get the tag, which defines the type of the choice object.
     * 
     * @return returns the tag
     */
    public Unsigned8 getTag() {
        return tag;
    }

    protected void setTag(Unsigned8 tag) {
        this.tag = tag;
    }
}
