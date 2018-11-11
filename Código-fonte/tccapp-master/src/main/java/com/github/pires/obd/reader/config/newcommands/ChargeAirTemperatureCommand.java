package com.github.pires.obd.reader.config.newcommands;

import com.github.pires.obd.commands.ObdCommand;

/**
 * Created by bdhess on 05.01.2017.
 */

public class ChargeAirTemperatureCommand extends ObdCommand {

    private float temp = 0;

    /**
     * <p>ChargeAirTemperatureCommand.</p>
     */
    public ChargeAirTemperatureCommand() {

        super("01 77");
    }

    /** {@inheritDoc} */
    @Override
    protected void performCalculations() {
        // ignore first two bytes [01 44] of the response
        // Byte A is list of supported values
        // Byte B is Bank1 Sensor1 with 1°C resolution + 40°C
        float B = buffer.get(3);
        temp = B - 40;// B-40
    }

    /** {@inheritDoc} */
    @Override
    public String getFormattedResult() {
        return String.format("%.3f", getTemp());
    }

    /** {@inheritDoc} */
    @Override
    public String getCalculatedResult() {
        return String.valueOf(getTemp());
    }

    /**
     * <p>getTemp.</p>
     *
     * @return a double.
     */
    public float getTemp() {
        return temp;
    }

    /**
     * <p>Constructor for AirIntakeTemperatureCommand.</p>
     *
     * @param other a {@link com.github.pires.obd.commands.temperature.AirIntakeTemperatureCommand} object.
     */

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return CommandNames.CHRG_AIR_TEMP.getValue();
    }

}
