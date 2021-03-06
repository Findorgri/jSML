/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml;

public enum EUnit {
    EMPTY(0),
    YEAR(1),
    MONTH(2),
    WEEK(3),
    DAY(4),
    HOUR(5),
    MIN(6),
    SECOND(7),
    DEGREE(8),
    DEGREE_CELSIUS(9),
    CURRENCY(10),
    METRE(11),
    METRE_PER_SECOND(12),
    CUBIC_METRE(13),
    CUBIC_METRE_CORRECTED(14),
    CUBIC_METRE_PER_HOUR(15),
    CUBIC_METRE_PER_HOUR_CORRECTED(16),
    CUBIC_METRE_PER_DAY(17),
    CUBIC_METRE_PER_DAY_CORRECTED(18),
    LITRE(19),
    KILOGRAM(20),
    NEWTON(21),
    NEWTONMETER(22),
    PASCAL(23),
    BAR(24),
    JOULE(25),
    JOULE_PER_HOUR(26),
    WATT(27),
    VOLT_AMPERE(28),
    VAR(29),
    WATT_HOUR(30),
    VOLT_AMPERE_HOUR(31),
    VAR_HOUR(32),
    AMPERE(33),
    COULOMB(34),
    VOLT(35),
    VOLT_PER_METRE(36),
    FARAD(37),
    OHM(38),
    OHM_METRE(39),
    WEBER(40),
    TESLA(41),
    AMPERE_PER_METRE(42),
    HENRY(43),
    HERTZ(44),
    ACTIVE_ENERGY_METER_CONSTANT_OR_PULSE_VALUE(45),
    REACTIVE_ENERGY_METER_CONSTANT_OR_PULSE_VALUE(46),
    APPARENT_ENERGY_METER_CONSTANT_OR_PULSE_VALUE(47),
    VOLT_SQUARED_HOURS(48),
    AMPERE_SQUARED_HOURS(49),
    KILOGRAM_PER_SECOND(50),
    KELVIN(52),
    VOLT_SQUARED_HOUR_METER_CONSTANT_OR_PULSE_VALUE(53),
    AMPERE_SQUARED_HOUR_METER_CONSTANT_OR_PULSE_VALUE(54),
    METER_CONSTANT_OR_PULSE_VALUE(55),
    PERCENTAGE(56),
    AMPERE_HOUR(57),
    ENERGY_PER_VOLUME(60),
    CALORIFIC_VALUE(61),
    MOLE_PERCENT(62),
    MASS_DENSITY(63),
    PASCAL_SECOND(64),
    RESERVED(253),
    OTHER_UNIT(254),
    COUNT(255);

    private int id;

    private EUnit(int value) {
        this.id = value;
    }

    /**
     * @param id
     *            of the unit. DLMS-Unit-List, can be found in IEC 62056-62.
     * @return EUnit object
     */
    public static EUnit from(int id) {

        for (EUnit unitEnum : values()) {
            if (unitEnum.id == id) {
                return unitEnum;
            }
        }

        throw new IllegalArgumentException("Unknown id: " + id);
    }

    public int id() {
        return id;
    }

}
