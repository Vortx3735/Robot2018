package org.usfirst.frc.team3735.robot.commands.auto;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageRaise;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageSetRoller;
import org.usfirst.frc.team3735.robot.commands.drive.recorder.SendProfile;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosRaw;
import org.usfirst.frc.team3735.robot.settings.Waypoints;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleLeft extends CommandGroup {

    public LeftScaleLeft(boolean complex) {
    	if(complex) {
    		addSequential(new SendProfile(Waypoints.Auto.leftScaleLeft));	//initial approach to scale
    		addSequential(new ElevatorSetPosRaw(35),4);
    		addSequential(new CarriageRaise());
    		addSequential(new CarriageSetRoller(-.7), 1);
//    		addSequential(new SendProfile(Waypoints.Auto.leftScaleLeft2));	//backup and go to cube
//    		addSequential(new SendProfile(Waypoints.Auto.leftScaleLeft3));	//backup from cube, go to scale
//    		addSequential(new SendProfile(Waypoints.Auto.leftScaleLeft4));	//backup scale, go to other cube
//    		addSequential(new SendProfile(Waypoints.Auto.leftScaleLeft5));	//backup from cube, go to scale

    	}else {
    		
    	}
    }
}
