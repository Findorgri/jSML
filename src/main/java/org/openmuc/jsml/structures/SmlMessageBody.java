/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.openmuc.jsml.structures.requests.SmlGetListReq;
import org.openmuc.jsml.structures.requests.SmlGetProcParameterReq;
import org.openmuc.jsml.structures.requests.SmlGetProfileListReq;
import org.openmuc.jsml.structures.requests.SmlGetProfilePackReq;
import org.openmuc.jsml.structures.requests.SmlPublicCloseReq;
import org.openmuc.jsml.structures.requests.SmlPublicOpenReq;
import org.openmuc.jsml.structures.requests.SmlSetProcParameterReq;
import org.openmuc.jsml.structures.responses.SmlAttentionRes;
import org.openmuc.jsml.structures.responses.SmlGetListRes;
import org.openmuc.jsml.structures.responses.SmlGetProcParameterRes;
import org.openmuc.jsml.structures.responses.SmlGetProfileListRes;
import org.openmuc.jsml.structures.responses.SmlGetProfilePackRes;
import org.openmuc.jsml.structures.responses.SmlPublicCloseRes;
import org.openmuc.jsml.structures.responses.SmlPublicOpenRes;

public class SmlMessageBody extends ASNObject {

    private EMessageBody tag;
    private ASNObject choice;

    public SmlMessageBody(int tag, ASNObject choice) {
        EMessageBody messageBody = EMessageBody.from(tag);
        this.choice = choice;
        this.tag = messageBody;
        setSelected(true);
    }

    public SmlMessageBody() {
        tag = null;
    }

    @Override
    public void encode(DataOutputStream os) throws IOException {
        if (isOptional() && !isSelected()) {
            os.writeByte(0x01);
            return;
        }
        os.writeByte(0x72);

        Unsigned32 intTag = new Unsigned32(this.tag.id());
        intTag.encode(os);

        choice.encode(os);
    }

    @Override
    public boolean decode(DataInputStream is) throws IOException {
        byte tlField = is.readByte();
        if (isOptional() && (tlField == 0x01)) {
            setSelected(false);
            return true;
        }
        Unsigned32 integerTag = new Unsigned32();

        if ((tlField & 0xff) != 0x72 || !integerTag.decode(is)) {
            return false;
        }

        this.tag = EMessageBody.from(integerTag.getVal());

        switch (this.tag) {

        case OPEN_REQUEST:
            choice = new SmlPublicOpenReq();
            break;
        case OPEN_RESPONSE:
            choice = new SmlPublicOpenRes();
            break;
        case CLOSE_REQUEST:
            choice = new SmlPublicCloseReq();
            break;
        case CLOSE_RESPONSE:
            choice = new SmlPublicCloseRes();
            break;
        case GET_PROFILE_LIST_REQUEST:
            choice = new SmlGetProfileListReq();
            break;
        case GET_PROFILE_LIST_RESPONSE:
            choice = new SmlGetProfileListRes();
            break;
        case GET_PROFILE_PACK_REQUEST:
            choice = new SmlGetProfilePackReq();
            break;
        case GET_PROFILE_PACK_RESPONSE:
            choice = new SmlGetProfilePackRes();
            break;
        case GET_PROC_PARAMETER_REQUEST:
            choice = new SmlGetProcParameterReq();
            break;
        case GET_PROC_PARAMETER_RESPONSE:
            choice = new SmlGetProcParameterRes();
            break;
        case SET_PROC_PARAMETER_REQUEST:
            choice = new SmlSetProcParameterReq();
            break;
        case GET_LIST_REQUEST:
            choice = new SmlGetListReq();
            break;
        case GET_LIST_RESPONSE:
            choice = new SmlGetListRes();
            break;
        case ATTENTION_RESPONSE:
            choice = new SmlAttentionRes();
            break;
        default:
            return false;
        }

        if (!choice.decode(is)) {
            return false;
        }

        setSelected(true);

        return true;
    }

    public EMessageBody getTag() {
        return tag;
    }

    @SuppressWarnings("unchecked")
    public <T extends ASNObject> T getChoice() {
        return (T) choice;
    }

}
