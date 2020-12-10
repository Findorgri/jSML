/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml;

import java.util.List;

import org.openmuc.jsml.structures.EMessageBody;
import org.openmuc.jsml.structures.SmlFile;
import org.openmuc.jsml.structures.SmlMessage;
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

/**
 * Class to parse a SML_FILE
 */
public class GenericParser {

    /**
     * Prints the whole SML_File
     * 
     * @param smlFile
     *            the SML file
     */
    public static void printFile(SmlFile smlFile) {

        List<SmlMessage> smlMessages = smlFile.getMessages();

        for (SmlMessage smlMessage : smlMessages) {

            EMessageBody messageBody = smlMessage.getMessageBody().getTag();

            switch (messageBody) {

            case OPEN_REQUEST:
                parseOpenRequest(smlMessage);
                break;
            case OPEN_RESPONSE:
                parseOpenResponse(smlMessage);
                break;
            case CLOSE_REQUEST:
                parseCloseRequest(smlMessage);
                break;
            case CLOSE_RESPONSE:
                parseCloseResponse(smlMessage);
                break;
            case GET_PROFILE_PACK_REQUEST:
                parseGetProfilePackRequest(smlMessage);
                break;
            case GET_PROFILE_PACK_RESPONSE:
                parseGetProfilePackResponse(smlMessage);
                break;
            case GET_PROFILE_LIST_REQUEST:
                parseGetProfileListRequest(smlMessage);
                break;
            case GET_PROFILE_LIST_RESPONSE:
                parseGetProfileListResponse(smlMessage);
                break;
            case GET_PROC_PARAMETER_REQUEST:
                parseGetProcParameterRequest(smlMessage);
                break;
            case GET_PROC_PARAMETER_RESPONSE:
                parseGetProcParameterResponse(smlMessage);
                break;
            case SET_PROC_PARAMETER_REQUEST:
                parseSetProcParameterRequest(smlMessage);
                break;
            case GET_LIST_REQUEST:
                parseGetListRequest(smlMessage);
                break;
            case GET_LIST_RESPONSE:
                parseGetListResponse(smlMessage);
                break;
            case ATTENTION_RESPONSE:
                parseAttentionResponse(smlMessage);
                break;
            default:
                println("type not found");
            }
        }
    }

    // ========================= Responses =================================

    private static void parseGetListResponse(SmlMessage smlMessage) {
        println("Got GetListResponse");
        SmlGetListRes smlListRes = smlMessage.getMessageBody().getChoice();
        // TODO working on indents for better human readability
        println(smlListRes.toStringIndent(" "));
    }

    private static void parseAttentionResponse(SmlMessage smlMessage) {
        println("Got AttentionResponse");
        SmlAttentionRes smlAttentionRes = smlMessage.getMessageBody().getChoice();
        println(smlAttentionRes.toString());
    }

    private static void parseGetProcParameterResponse(SmlMessage smlMessage) {
        println("Got GetProcParameterResponse");
        SmlGetProcParameterRes smlGetProcParameterRes = smlMessage.getMessageBody().getChoice();
        println(smlGetProcParameterRes.toString());
    }

    private static void parseGetProfileListResponse(SmlMessage smlMessage) {
        println("Got GetProfileListResponse");
        SmlGetProfileListRes smlGetProfileListRes = smlMessage.getMessageBody().getChoice();
        println(smlGetProfileListRes.toString());
    }

    private static void parseOpenResponse(SmlMessage smlMessage) {
        println("Got OpenResponse");
        SmlPublicOpenRes smlPublicOpenRes = smlMessage.getMessageBody().getChoice();
        println(smlPublicOpenRes.toString());
    }

    private static void parseCloseResponse(SmlMessage smlMessage) {
        println("Got CloseResponse");
        SmlPublicCloseRes smlPublicCloseRes = smlMessage.getMessageBody().getChoice();
        println(smlPublicCloseRes.toString());
    }

    private static void parseGetProfilePackResponse(SmlMessage smlMessage) {
        println("Got GetProfilePackResponse");
        SmlGetProfilePackRes smlGetProfilePackRes = smlMessage.getMessageBody().getChoice();
        println(smlGetProfilePackRes.toString());
    }

    // ========================= Requests =================================

    private static void parseOpenRequest(SmlMessage smlMessage) {
        println("Got OpenRequest");
        SmlPublicOpenReq smlPublicOpenReq = smlMessage.getMessageBody().getChoice();
        println(smlPublicOpenReq.toString());
    }

    private static void parseCloseRequest(SmlMessage smlMessage) {
        println("Got CloseRequest");
        SmlPublicCloseReq smlPublicCloseRes = smlMessage.getMessageBody().getChoice();
        println(smlPublicCloseRes.toString());
    }

    private static void parseGetProfileListRequest(SmlMessage smlMessage) {
        println("Got GetProfileListRequest");
        SmlGetProfileListReq smlGetProfileListReq = smlMessage.getMessageBody().getChoice();
        println(smlGetProfileListReq.toString());
    }

    private static void parseGetProfilePackRequest(SmlMessage smlMessage) {
        println("Got GetProfilePackRequest");
        SmlGetProfilePackReq smlGetProfilePackReq = smlMessage.getMessageBody().getChoice();
        println(smlGetProfilePackReq.toString());
    }

    private static void parseGetProcParameterRequest(SmlMessage smlMessage) {
        println("Got GetProcParameterRequest");
        SmlGetProcParameterReq smlGetProcParameterReq = smlMessage.getMessageBody().getChoice();
        println(smlGetProcParameterReq.toString());
    }

    private static void parseSetProcParameterRequest(SmlMessage smlMessage) {
        println("Got SetProcParameterRequest");
        SmlSetProcParameterReq smlSetProcParameterReq = smlMessage.getMessageBody().getChoice();
        println(smlSetProcParameterReq.toString());
    }

    private static void parseGetListRequest(SmlMessage smlMessage) {
        println("Got GetListRequest");
        SmlGetListReq smlGetListReq = smlMessage.getMessageBody().getChoice();
        println(smlGetListReq.toString());
    }

    private static void println(String... strings) {
        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            sb.append(string);
        }
        System.out.println(sb);
    }

    private GenericParser() {
        /*
         * private constructor to hide the implicit public one, since static methods should be accessed in static way so
         * there is no need of public constructor
         */
    }
}
