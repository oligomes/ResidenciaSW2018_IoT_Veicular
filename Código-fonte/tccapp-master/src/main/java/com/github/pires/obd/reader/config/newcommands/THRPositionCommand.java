package com.github.pires.obd.reader.config.newcommands;

import com.github.pires.obd.commands.PercentageObdCommand;


public class THRPositionCommand extends PercentageObdCommand{
    public THRPositionCommand() {
        super("01 45");
    }

     public THRPositionCommand(THRPositionCommand other) {
        super(other);
    }

   @Override
    public String getName() {
        return CommandNames.THR_POS.getValue();
    }

}
