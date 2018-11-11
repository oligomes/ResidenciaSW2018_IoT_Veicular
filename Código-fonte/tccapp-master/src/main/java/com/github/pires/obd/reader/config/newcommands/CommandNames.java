package com.github.pires.obd.reader.config.newcommands;

public enum CommandNames {

    INAIR_TMP("Intake air temp. sensor"),
    ACC_POSD("Accel Pedal Position D"),
    ACC_POSE("Accel Pedal Position E"),
    THR_POS("Relative throttle position"),
    CHRG_AIR_TEMP("Charge air cooler temp."),
    AFR_BANK1("Oxygen Sensor 1"),
    ETH_PERC("Ethanol Percentage");

    private final String value;

    /**
     * @param value Command description
     */
    CommandNames(String value) {
        this.value = value;
    }

    /**
     * <p>Getter for the field <code>value</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public final String getValue() {
        return value;
    }

}