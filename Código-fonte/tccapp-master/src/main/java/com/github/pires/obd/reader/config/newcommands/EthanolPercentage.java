package com.github.pires.obd.reader.config.newcommands;

import com.github.pires.obd.commands.PercentageObdCommand;

/**
 * Created by bdhess on 04.01.2017.
 */

public class EthanolPercentage extends PercentageObdCommand{
    public EthanolPercentage() {
        super("01 52");
    }

     public EthanolPercentage(EthanolPercentage other) {
        super(other);
    }

   @Override
    public String getName() {
        return CommandNames.ETH_PERC.getValue();
    }

}
