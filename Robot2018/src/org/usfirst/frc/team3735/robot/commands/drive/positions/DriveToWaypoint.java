package org.usfirst.frc.team3735.robot.commands.drive.positions;

import org.usfirst.frc.team3735.robot.assists.NavxAssist;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.DriveRaw;
import org.usfirst.frc.team3735.robot.triggers.HasMoved;
import org.usfirst.frc.team3735.robot.util.settings.Func;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveToWaypoint extends CommandGroup {

    public DriveToWaypoint() {
//    	addSequential(new DriveRaw(-.4, 0).addT(new HasMoved(new Func() {
//    		@Override
//    		public double getValue() {
//    			return getDist();
//    		}
//    	})).addA(new NavxAssist(target, true)));
    }
}
