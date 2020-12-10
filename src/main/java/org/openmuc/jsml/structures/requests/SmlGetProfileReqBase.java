package org.openmuc.jsml.structures.requests;

import org.openmuc.jsml.structures.ListOfSmlObjReqEntry;
import org.openmuc.jsml.structures.OctetString;
import org.openmuc.jsml.structures.Sequence;
import org.openmuc.jsml.structures.SmlBoolean;
import org.openmuc.jsml.structures.SmlTime;
import org.openmuc.jsml.structures.SmlTree;
import org.openmuc.jsml.structures.SmlTreePath;

abstract class SmlGetProfileReqBase extends Sequence {

    protected OctetString serverId; // OPTIONAL,
    protected OctetString username; // OPTIONAL,
    protected OctetString password; // OPTIONAL,
    protected SmlBoolean withRawdata; // OPTIONAL,
    protected SmlTime beginTime; // OPTIONAL,
    protected SmlTime endTime; // OPTIONAL,
    protected SmlTreePath parameterTreePath;
    protected ListOfSmlObjReqEntry objectList; // OPTIONAL,
    protected SmlTree dasDetails; // OPTIONAL

    public SmlGetProfileReqBase() {
    }

    public SmlGetProfileReqBase(OctetString serverId, OctetString username, OctetString password,
            SmlBoolean withRawdata, SmlTime beginTime, SmlTime endTime, SmlTreePath parameterTreePath,
            ListOfSmlObjReqEntry objectList, SmlTree dasDetails) {

        if (parameterTreePath == null) {
            throw new IllegalArgumentException("parameterTreePath is not optional and must not be null!");
        }

        this.serverId = nunNullVal(serverId, new OctetString());
        this.username = nunNullVal(username, new OctetString());
        this.password = nunNullVal(password, new OctetString());
        this.withRawdata = nunNullVal(withRawdata, new SmlBoolean());
        this.beginTime = nunNullVal(beginTime, new SmlTime());
        this.endTime = nunNullVal(endTime, new SmlTime());
        this.parameterTreePath = parameterTreePath;
        this.objectList = nunNullVal(objectList, new ListOfSmlObjReqEntry());
        this.dasDetails = nunNullVal(dasDetails, new SmlTree());

        setOptionalAndSeq();

        setSelected(true);

    }

    private static <T> T nunNullVal(T value, T defaulValue) {
        return value == null ? defaulValue : value;
    }

    public OctetString getServerId() {
        return serverId;
    }

    public OctetString getUsername() {
        return username;
    }

    public OctetString getPassword() {
        return password;
    }

    public SmlBoolean getWithRawdata() {
        return withRawdata;
    }

    public SmlTime getBeginTime() {
        return beginTime;
    }

    public SmlTime getEndTime() {
        return endTime;
    }

    public SmlTreePath getParameterTreePath() {
        return parameterTreePath;
    }

    public ListOfSmlObjReqEntry getObjectList() {
        return objectList;
    }

    public SmlTree getDasDetails() {
        return dasDetails;
    }

    public void setOptionalAndSeq() {
        serverId.setOptional();
        username.setOptional();
        password.setOptional();
        withRawdata.setOptional();
        beginTime.setOptional();
        endTime.setOptional();
        objectList.setOptional();
        dasDetails.setOptional();

        seqArray(serverId, username, password, withRawdata, beginTime, endTime, parameterTreePath, objectList,
                dasDetails);
    }

    @Override
    protected void createElements() {
        serverId = new OctetString();
        username = new OctetString();
        password = new OctetString();
        withRawdata = new SmlBoolean();
        beginTime = new SmlTime();
        endTime = new SmlTime();
        parameterTreePath = new SmlTreePath();
        objectList = new ListOfSmlObjReqEntry();
        dasDetails = new SmlTree();

        setOptionalAndSeq();
    }

    protected String getStructureName() {
        return "Sml_GetProfileReqBase";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getStructureName());
        sb.append("{\n");
        sb.append("  serverId:          " + serverId.toString() + "\n");
        sb.append("  username:          " + username.toString() + "\n");
        sb.append("  password:          " + password.toString() + "\n");
        sb.append("  withRawdata:       " + withRawdata.toString() + "\n");
        sb.append("  beginTime:         " + beginTime.toString() + "\n");
        sb.append("  endTime:           " + endTime.toString() + "\n");
        sb.append("  parameterTreePath: " + parameterTreePath.toString() + "\n");
        sb.append("  objectList:        " + objectList.toString() + "\n");
        sb.append("  dasDetails:        " + dasDetails.toString() + "\n");
        sb.append("}\n");
        return sb.toString();
    }

}
