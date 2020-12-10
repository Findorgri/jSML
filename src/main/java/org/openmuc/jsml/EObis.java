/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.openmuc.jsml.structures.OctetString;

/**
 * Enum is used to translate obis code in human readable string
 */
// see: DIN EN 62056-21:2007 page 12 ff
// see: SyM² specification V1.03, page 168 - List of used OBIS codes
public enum EObis {

    // ======================================================================
    // Device Attributes
    //
    // see: SyM² specification V1.03, page 107, table 45
    // ======================================================================
    ATTRIBUTE_PUBLIC_KEY_FOR_FIRMWARE_DOWNLOAD(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x84,
            (byte) 0x82, (byte) 0xFF }),
    ATTRIBUTE_STATUS_WORD(new byte[] { (byte) 0x81, (byte) 0x00, (byte) 0x60, (byte) 0x05, (byte) 0x00, (byte) 0x00 }),
    ATTRIBUTE_SECINDEX(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x60, (byte) 0x08, (byte) 0x00, (byte) 0xFF }),
    ATTRIBUTE_REMAINING_TIME_CURRENT_PERIOD(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x84,
            (byte) 0x84, (byte) 0xFF }),

    // ======================================================================
    // Device Infos
    //
    // see: SyM² specification V1.03, page 107, table 74
    // ======================================================================
    DEVICE_INFO_ROOT(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x82, (byte) 0x01, (byte) 0xFF }),
    // ----------------------------------------------------------------------
    DEVICE_INFO_DEVICE_CLASS(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x82, (byte) 0x02,
            (byte) 0xFF }),
    DEVICE_INFO_MANUFACTOR(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x82, (byte) 0x03, (byte) 0xFF }),
    DEVICE_INFO_SERVER_ID(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x82, (byte) 0x04, (byte) 0xFF }),
    DEVICE_INFO_PUBLIC_KEY(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x82, (byte) 0x05, (byte) 0xFF }),
    DEVICE_INFO_FIRMEWARE_VERSIONS(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x82, (byte) 0x06,
            (byte) 0xFF }),
    DEVICE_INFO_FIRMEWARE_NAME_MANUFACTOR(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x82, 0x08,
            (byte) 0xFF }),
    DEVICE_INFO_FIRMEWARE_VERSIONS_MANUFACTOR(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0x00, (byte) 0x02,
            (byte) 0x00, (byte) 0x00 }),

    // ======================================================================
    // Special Measurement Tuples
    //
    // see: SyM² specification V1.03, page 111, table 51
    // ======================================================================
    SPECIAL_MEASURMENT_ROOT(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x84, 0x01, (byte) 0xFF }),
    // ----------------------------------------------------------------------
    SPECIAL_MEASURMENT_CURRENT_TUPLE(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x84, 0x04,
            (byte) 0xFF }),
    SPECIAL_MEASURMENT_TUPLE_POWER_RESTORED(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x84, 0x05,
            (byte) 0xFF }),
    SPECIAL_MEASURMENT_TUPLE_POWER_RESTORED_WITHOUT_POWER_RESERVE(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7,
            (byte) 0x84, 0x06, (byte) 0xFF }),
    SPECIAL_MEASURMENT_LAST_TUPLE_BEFORE_POWER_OUTAGE(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x84,
            0x07, (byte) 0xFF }),
    SPECIAL_MEASURMENT_TUPLE_LAST_SYNCHRONIZATION(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x84, 0x08,
            (byte) 0xFF }),

    // A measurement tuples consists of:
    E_IMPORT(new byte[] { 0x01, 0x01, 0x01, 0x08, 0x00, (byte) 0xFF }),
    E_EXPORT(new byte[] { 0x01, 0x01, 0x02, 0x08, 0x00, (byte) 0xFF }),
    Q_IMPORT(new byte[] { 0x01, 0x01, 0x03, 0x08, 0x00, (byte) 0xFF }),
    Q_EXPORT(new byte[] { 0x01, 0x01, 0x04, 0x08, 0x00, (byte) 0xFF }),
    Q_QUADRANT_I(new byte[] { 0x01, 0x01, 0x05, 0x08, 0x00, (byte) 0xFF }),
    Q_QUADRANT_II(new byte[] { 0x01, 0x01, 0x06, 0x08, 0x00, (byte) 0xFF }),
    Q_QUADRANT_III(new byte[] { 0x01, 0x01, 0x07, 0x08, 0x00, (byte) 0xFF }),
    Q_QUADRANT_IV(new byte[] { 0x01, 0x01, 0x08, 0x08, 0x00, (byte) 0xFF }),

    // ======================================================================
    // Measurement Tuples
    //
    // see: SyM² specification V1.03, page 34ff, table 5
    // see: DIN EN 62056-21:2007, page ?, table 5
    // see: DIN EN 62056-21:2007, page ?, table 11
    // ======================================================================
    MEASURMENT_ROOT(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x85, 0x01, (byte) 0xFF }),
    // ----------------------------------------------------------------------
    P(new byte[] { 0x01, 0x00, 0x0f, 0x07, 0x00, (byte) 0xff }),
    P_L1(new byte[] { 0x01, 0x00, 0x23, 0x07, 0x00, (byte) 0xff }),
    P_L2(new byte[] { 0x01, 0x00, 0x37, 0x07, 0x00, (byte) 0xff }),
    P_L3(new byte[] { 0x01, 0x00, 0x4b, 0x07, 0x00, (byte) 0xff }),
    I_EFF_TO_L1(new byte[] { 0x01, 0x00, 0x1f, 0x07, 0x00, (byte) 0xff }),
    I_EFF_TO_L2(new byte[] { 0x01, 0x00, 0x33, 0x07, 0x00, (byte) 0xff }),
    I_EFF_TO_L3(new byte[] { 0x01, 0x00, 0x47, 0x07, 0x00, (byte) 0xff }),
    I_EFF_TO_N(new byte[] { 0x01, 0x00, 0x5b, 0x07, 0x00, (byte) 0xff }),
    U_EFF_TO_L1(new byte[] { 0x01, 0x00, 0x20, 0x07, 0x00, (byte) 0xff }),
    U_EFF_TO_L2(new byte[] { 0x01, 0x00, 0x34, 0x07, 0x00, (byte) 0xff }),
    U_EFF_TO_L3(new byte[] { 0x01, 0x00, 0x48, 0x07, 0x00, (byte) 0xff }),
    PHASE_ANGLE_U_L2_TO_U_L1(new byte[] { 0x01, 0x00, 0x51, 0x07, 0x01, (byte) 0xff }),
    PHASE_ANGLE_U_L3_TO_U_L1(new byte[] { 0x01, 0x00, 0x51, 0x07, 0x02, (byte) 0xff }),
    PHASE_ANGLE_I_L1_TO_U_L1(new byte[] { 0x01, 0x00, 0x51, 0x07, 0x04, (byte) 0xff }),
    PHASE_ANGLE_I_L2_TO_U_L2(new byte[] { 0x01, 0x00, 0x51, 0x07, 0x0f, (byte) 0xff }),
    PHASE_ANGLE_I_L3_TO_U_L3(new byte[] { 0x01, 0x00, 0x51, 0x07, 0x1a, (byte) 0xff }),

    // ======================================================================
    // Logbook
    // ======================================================================
    LOGBOOK_ROOT(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x84, (byte) 0xE1, (byte) 0xFF }),
    // ----------------------------------------------------------------------
    LOGBOOK_COUNTER_FAILD_VERIFICATION(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x84, (byte) 0x81,
            (byte) 0xFF }),
    LOGBOOK_ECC(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x81, (byte) 0x07, (byte) 0xFF }),

    // ======================================================================
    // Load profile
    //
    // see: DIN EN 62056-61:2007, page 32, table 21
    // see: SyM² specification V1.03, page 50, table 12
    // ======================================================================
    LOADPROFILE_ROOT_TARIFF_1(new byte[] { (byte) 0x01, (byte) 0x00, (byte) 0x63, (byte) 0x01, (byte) 0x00,
            (byte) 0xFF }),
    // ----------------------------------------------------------------------
    LOADPROFILE_E_IMPORT_TARIFF_1(new byte[] { (byte) 0x01, (byte) 0x00, (byte) 0x01, (byte) 0x08, (byte) 0x01,
            (byte) 0xFF }),
    LOADPROFILE_Q_QUADRANT_I_TARIFF_1(new byte[] { (byte) 0x01, (byte) 0x00, (byte) 0x05, (byte) 0x08, (byte) 0x01,
            (byte) 0xFF }),
    LOADPROFILE_Q_QUADRANT_IV_TARIFF_1(new byte[] { (byte) 0x01, (byte) 0x00, (byte) 0x08, (byte) 0x08, (byte) 0x01,
            (byte) 0xFF }),
    LOADPROFILE_E_EXPORT_TARIFF_1(new byte[] { (byte) 0x01, (byte) 0x00, (byte) 0x02, (byte) 0x08, (byte) 0x01,
            (byte) 0xFF }),
    LOADPROFILE_Q_QUADRANT_II_TARIFF_1(new byte[] { (byte) 0x01, (byte) 0x00, (byte) 0x06, (byte) 0x08, (byte) 0x01,
            (byte) 0xFF }),
    LOADPROFILE_Q_QUADRANT_III_TARIFF_1(new byte[] { (byte) 0x01, (byte) 0x00, (byte) 0x07, (byte) 0x08, (byte) 0x01,
            (byte) 0xFF }),

    // SyM2 V1.03 p.99
    LOADPROFILE_SIGNATURE_IMPORT(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x84, (byte) 0x02,
            (byte) 0xFF }),

    // SyM2 V1.03 p.99
    LOADPROFILE_SIGNATURE_EXPORT(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x84, (byte) 0x03,
            (byte) 0xFF }),

    // ======================================================================
    // Attention Response
    //
    // see: SML V1.03, page 29, table 2
    // ======================================================================
    ATTENTION_OK(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0xC7, (byte) 0xFD, (byte) 0x00 }),

    ATTENTION_WITHOUT_ERROR_DESCRIPTION(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0xC7, (byte) 0xFE,
            (byte) 0x00 }),
    ATTENTION_UNKNOWN_SML_DESIGNATOR(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0xC7, (byte) 0xFE,
            (byte) 0x01 }),
    ATTENTION_INADEQUATE_AUTHENTICATION(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0xC7, (byte) 0xFE,
            (byte) 0x02 }),
    ATTENTION_SERVERID_NOT_AVAILABLE(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0xC7, (byte) 0xFE,
            (byte) 0x03 }),
    ATTENTION_REQFILEID_NOT_AVAILABLE(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0xC7, (byte) 0xFE,
            (byte) 0x04 }),
    ATTENTION_ONE_OR_MORE_DESTITNATION_ATTRIBUTES_CANNOT_BE_WRITTEN(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7,
            (byte) 0xC7, (byte) 0xFE, (byte) 0x05 }),
    ATTENTION_ONE_OR_MORE_DESTITNATION_ATTRIBUTES_CANNOT_BE_READ(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7,
            (byte) 0xC7, (byte) 0xFE, (byte) 0x06 }),
    ATTENTION_COMMUNICATION_WITH_MEASURING_POINT_DISTURBED(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7,
            (byte) 0xC7, (byte) 0xFE, (byte) 0x07 }),
    ATTENTION_RAW_DATA_CANNOT_BE_INTERPRETED(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0xC7,
            (byte) 0xFE, (byte) 0x08 }),
    ATTENTION_VALUE_OUT_OF_BOUNDS(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0xC7, (byte) 0xFE,
            (byte) 0x09 }),
    ATTENTION_REQUEST_NOT_EXECUTED(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0xC7, (byte) 0xFE,
            (byte) 0x0A }),
    ATTENTION_CHECKSUM_FAULTY(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0xC7, (byte) 0xFE,
            (byte) 0x0B }),
    ATTENTION_BROADCAST_NOT_SUPPORTED(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0xC7, (byte) 0xFE,
            (byte) 0x0C }),
    ATTENTION_UNEXPECTED_SMLMESSAGE(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0xC7, (byte) 0xFE,
            (byte) 0x0D }),
    ATTENTION_UNKNOWN_OBJECT_IN_THE_LOAD_PROFILE(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0xC7,
            (byte) 0xFE, (byte) 0x0E }),
    ATTENTION_DATA_TYPE_NOT_SUPPORTED(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0xC7, (byte) 0xFE,
            (byte) 0x0F }),
    ATTENTION_OPTIONAL_ELEMENT_NOT_SUPPORTED(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0xC7,
            (byte) 0xFE, (byte) 0x10 }),

    ATTENTION_REQUESTED_PROFILE_HAS_NO_ENTRY(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0xC7,
            (byte) 0xFE, (byte) 0x11 }),

    // ======================================================================
    // SyM² Device Classes
    //
    // SyM² V1.03, page 101, table 39
    // ======================================================================
    DEVICE_CLASS_BASIS_MODULE_DIRECT_CONNECTION_3X230_400V_5_100A(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7,
            (byte) 0x82, (byte) 0x41, (byte) 0xFF }),
    DEVICE_CLASS_BASIS_MODULE_HALF_INDIRECT_CONNECTION_3X230_400V_1_6A(new byte[] { (byte) 0x81, (byte) 0x81,
            (byte) 0xC7, (byte) 0x82, (byte) 0x42, (byte) 0xFF }),
    DEVICE_CLASS_BASIS_MODULE_INDIRECT_CONNECTION_3X58_100V_1_6A(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7,
            (byte) 0x82, (byte) 0x43, (byte) 0xFF }),
    DEVICE_CLASS_BASIS_MODULE_INDIRECT_CONNECTION_3X58_100V_1_2A(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7,
            (byte) 0x82, (byte) 0x44, (byte) 0xFF }),
    DEVICE_CLASS_IMPULSE_TRANSMISSION_MODULE(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x82,
            (byte) 0x45, (byte) 0xFF }),
    DEVICE_CLASS_COMMUNICATION_MODULE_PSTN(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x82, (byte) 0x46,
            (byte) 0xFF }),
    DEVICE_CLASS_COMMUNICATION_MODULE_GSM_GPRS(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x82,
            (byte) 0x47, (byte) 0xFF }),
    DEVICE_CLASS_COMMUNICATION_MODULE_LAN_DSL(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x82,
            (byte) 0x48, (byte) 0xFF }),
    DEVICE_CLASS_NETWORK_NODE_AND_AUXILIARY_VOLTAGE_MODULE_LAN_DSL(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7,
            (byte) 0x82, (byte) 0x49, (byte) 0xFF }),
    DEVICE_CLASS_EXTERNAL_LOADPROFILE_COLLECTOR(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x82,
            (byte) 0x4A, (byte) 0xFF }),
    DEVICE_CLASS_GATEWAY(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x82, (byte) 0x4B, (byte) 0xFF }),

    // ======================================================================
    // Firmeware Version
    //
    // last byte changes/increases, 10 versions predefined here
    //
    // TODO (low priority) find flexible solutions to support all version numbers (00 to FF)
    // ======================================================================
    FIRMWARE_VERSION_1(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x82, (byte) 0x07, (byte) 0x01 }),
    FIRMWARE_VERSION_2(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x82, (byte) 0x07, (byte) 0x02 }),
    FIRMWARE_VERSION_3(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x82, (byte) 0x07, (byte) 0x03 }),
    FIRMWARE_VERSION_4(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x82, (byte) 0x07, (byte) 0x04 }),
    FIRMWARE_VERSION_5(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x82, (byte) 0x07, (byte) 0x05 }),
    FIRMWARE_VERSION_6(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x82, (byte) 0x07, (byte) 0x06 }),
    FIRMWARE_VERSION_7(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x82, (byte) 0x07, (byte) 0x07 }),
    FIRMWARE_VERSION_8(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x82, (byte) 0x07, (byte) 0x08 }),
    FIRMWARE_VERSION_9(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x82, (byte) 0x07, (byte) 0x09 }),
    FIRMWARE_VERSION_10(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x82, (byte) 0x07, (byte) 0x0A }),

    // ======================================================================
    // Communication Module
    //
    // SyM² V1.03, page 169, table 104
    // ======================================================================
    CM_NTP_PARAMETER_ROOT(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x88, (byte) 0x01, (byte) 0xFF }),
    // ----------------------------------------------------------------------
    CM_NTP_SERVER_IP(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x88, (byte) 0x02, (byte) 0xFF }),
    // NTP Service List (last byte changes/increases, 10 versions predefined here)
    // TODO (low priority) find flexible solutions to support all version numbers (00 to FF)
    CM_NTP_SERVICE_1(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x88, (byte) 0x02, (byte) 0x01 }),
    CM_NTP_SERVICE_2(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x88, (byte) 0x02, (byte) 0x02 }),
    CM_NTP_SERVICE_3(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x88, (byte) 0x02, (byte) 0x03 }),
    CM_NTP_SERVICE_4(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x88, (byte) 0x02, (byte) 0x04 }),
    CM_NTP_SERVICE_5(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x88, (byte) 0x02, (byte) 0x05 }),
    CM_NTP_SERVICE_6(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x88, (byte) 0x02, (byte) 0x06 }),
    CM_NTP_SERVICE_7(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x88, (byte) 0x02, (byte) 0x07 }),
    CM_NTP_SERVICE_8(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x88, (byte) 0x02, (byte) 0x08 }),
    CM_NTP_SERVICE_9(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x88, (byte) 0x02, (byte) 0x09 }),
    CM_NTP_SERVICE_10(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x88, (byte) 0x02, (byte) 0x0A }),

    CM_NTP_SERVER_PORT(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x88, (byte) 0x03, (byte) 0xFF }),
    CM_NTP_PERIOD_TO_SEND_SYNCHRONOUS_TOKEN(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x88,
            (byte) 0x04, (byte) 0xFF }),
    CM_NTP_OFFSET_SYNCHRONOUS_TOKEN(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x88, (byte) 0x05,
            (byte) 0xFF }),
    CM_NTP_ACCESS_ENABLED(new byte[] { (byte) 0x81, (byte) 0x81, (byte) 0xC7, (byte) 0x88, (byte) 0x06, (byte) 0xFF }),

    UNKNOWN(null);

    private static final Logger LOGGER = Logger.getLogger(EObis.class.getName());
    private final OctetString obisCode;

    /**
     * Map to store all enum elements on object creation
     */
    private static final Map<OctetString, EObis> MAP = new HashMap<>();

    static {
        for (EObis enumInstance : EObis.values()) {
            if (MAP.put(enumInstance.obisCode(), enumInstance) != null) {
                throw new IllegalArgumentException("duplicate ID: " + enumInstance.obisCode());
            }
        }
    }

    /**
     * private constructor
     */
    private EObis(byte[] obisCode) {
        this.obisCode = new OctetString(obisCode);
    }

    public static EObis getInstance(OctetString obisCode) {
        EObis enumInstance = MAP.get(obisCode);
        if (enumInstance == null) {
            LOGGER.warning("Unknown OBIS Code: " + obisCode.toHexString());
            enumInstance = EObis.UNKNOWN;
        }
        return enumInstance;
    }

    public OctetString obisCode() {
        return obisCode;
    }

    public String toDecimalString() {
        return obisCode.toDecimalString();
    }

}
