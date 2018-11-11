package com.github.pires.obd.reader.config.newcommands;

import com.github.pires.obd.commands.ObdCommand;

/**
 * Created by bdhess on 05.01.2017.
 */

public class LambdaBank1 extends ObdCommand {

    private float afr = 0;

    /**
     * <p>Constructor for Lambda Bank1.</p>
     */
    public LambdaBank1() {
        super("01 24");
    }

    /** {@inheritDoc} */
    @Override
    protected void performCalculations() {
        // ignore first two bytes [01 44] of the response
        float A = buffer.get(2);
        float B = buffer.get(3);
        afr = (((A * 256) + B) / 32768);//((A*256)+B)/32768
    }

    /** {@inheritDoc} */
    @Override
    public String getFormattedResult() {
        return String.format("%.3f", getLambda1());
    }

    /** {@inheritDoc} */
    @Override
    public String getCalculatedResult() {
        return String.valueOf(getLambda1());
    }

    /**
     * <p>getAirFuelRatio.</p>
     *
     * @return a double.
     */
    public double getLambda1() {
        return afr;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return CommandNames.AFR_BANK1.getValue();
    }

}