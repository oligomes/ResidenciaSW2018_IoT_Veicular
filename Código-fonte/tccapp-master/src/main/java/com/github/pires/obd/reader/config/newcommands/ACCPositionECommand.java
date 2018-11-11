package com.github.pires.obd.reader.config.newcommands;

import com.github.pires.obd.commands.PercentageObdCommand;

/**
 * Created by bdhess on 04.01.2017.
 */

public class ACCPositionECommand extends PercentageObdCommand{
    public ACCPositionECommand() {
        super("01 4A");
    }

     public ACCPositionECommand(ACCPositionECommand other) {
        super(other);
    }

   @Override
    public String getName() {
        return CommandNames.ACC_POSE.getValue();
    }

}
