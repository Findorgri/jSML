/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures.requests;

import org.openmuc.jsml.structures.ListOfSmlObjReqEntry;
import org.openmuc.jsml.structures.OctetString;
import org.openmuc.jsml.structures.SmlBoolean;
import org.openmuc.jsml.structures.SmlTime;
import org.openmuc.jsml.structures.SmlTree;
import org.openmuc.jsml.structures.SmlTreePath;

public class SmlGetProfileListReq extends SmlGetProfileReqBase {

    public SmlGetProfileListReq() {
    }

    public SmlGetProfileListReq(OctetString serverId, OctetString username, OctetString password,
            SmlBoolean withRawdata, SmlTime beginTime, SmlTime endTime, SmlTreePath parameterTreePath,
            ListOfSmlObjReqEntry objectList, SmlTree dasDetails) {
        super(serverId, username, password, withRawdata, beginTime, endTime, parameterTreePath, objectList, dasDetails);
    }

    @Override
    protected String getStructureName() {
        return "Sml_GetProfileListReq";
    }

}
