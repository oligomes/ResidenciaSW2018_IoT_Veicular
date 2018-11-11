package com.github.pires.obd.reader.config.newcommands;

import com.github.pires.obd.commands.PercentageObdCommand;

/**
 * Created by bdhess on 04.01.2017.
 */

public class ACCPositionDCommand extends PercentageObdCommand{
    public ACCPositionDCommand() {
        super("01 49");
    }

     public ACCPositionDCommand(ACCPositionDCommand other) {
        super(other);
    }

   @Override
    public String getName() {
        return CommandNames.ACC_POSD.getValue();
    }

}
