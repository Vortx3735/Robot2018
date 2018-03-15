package org.usfirst.frc.team3735.robot.commands.auto;

import org.usfirst.frc.team3735.robot.assists.NavxAssist;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.MoveDDx;
import org.usfirst.frc.team3735.robot.settings.Dms;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class UnknownStraight extends CommandGroup {

    public UnknownStraight() {
    	addSequential(new MoveDDx(Dms.Field.TOBASELINE, .6, .05).addA(new NavxAssist()));
    }
}
