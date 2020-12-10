/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

import java.util.logging.Logger;

public enum EMessageBody {

    OPEN_REQUEST(0x00000100),
    OPEN_RESPONSE(0x00000101),
    CLOSE_REQUEST(0x0000200),
    CLOSE_RESPONSE(0x00000201),
    GET_PROFILE_PACK_REQUEST(0x00000300),
    GET_PROFILE_PACK_RESPONSE(0x00000301),
    GET_PROFILE_LIST_REQUEST(0x00000400),
    GET_PROFILE_LIST_RESPONSE(0x00000401),
    GET_PROC_PARAMETER_REQUEST(0x00000500),
    GET_PROC_PARAMETER_RESPONSE(0x00000501),
    SET_PROC_PARAMETER_REQUEST(0x00000600),
    SET_PROC_PARAMETER_RESPONSE(0x00000601),
    GET_LIST_REQUEST(0x00000700),
    GET_LIST_RESPONSE(0x00000701),
    ATTENTION_RESPONSE(0x0000FF01);

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(EMessageBody.class.getName());

    private final int id;

    private EMessageBody(int id) {
        this.id = id;
    }

    public static EMessageBody from(int id) {
        for (EMessageBody value : values()) {
            if (value.id == id) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unknown MessageBody id: " + id);
    }

    public int id() {
        return id;
    }

}
