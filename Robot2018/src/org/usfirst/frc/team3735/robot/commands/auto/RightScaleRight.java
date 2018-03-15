package org.usfirst.frc.team3735.robot.commands.auto;

import org.usfirst.frc.team3735.robot.commands.Wait;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageSetRoller;
import org.usfirst.frc.team3735.robot.commands.drive.TurnTo;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.Move;
import org.usfirst.frc.team3735.robot.commands.drive.recorder.SendProfile;
import org.usfirst.frc.team3735.robot.commands.drive.simple.DriveBrakeUntilStopped;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosPID;
import org.usfirst.frc.team3735.robot.commands.sequences.AutoScaleLineup;
import org.usfirst.frc.team3735.robot.settings.Dms;
import org.usfirst.frc.team3735.robot.settings.Waypoints;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScaleRight extends CommandGroup {

	
    public RightScaleRight(boolean complex) {
    	if(complex) {
    		addSequential(new SendProfile(Waypoints.Auto.rightScaleRight));	//initial cross to scale
    		addSequential(new AutoScaleLineup(true));
//    		addSequential(new SendProfile(Waypoints.Auto.rightScaleRight2));	//backup and go to cube
//    		addSequential(new SendProfile(Waypoints.Auto.rightScaleRight3));	//backup from cube, go to scale
//    		addSequential(new SendProfile(Waypoints.Auto.rightScaleRight4));	//backup scale, go to other cube
//    		addSequential(new SendProfile(Waypoints.Auto.rightScaleRight5));	//backup from cube, go to scale
    		
    	}else {
    		addSequential(new Move(Dms.Field.HALFLENGTH));
    		addSequential(new DriveBrakeUntilStopped());
    		addSequential(new TurnTo(-90));
    		addParallel(new ElevatorSetPosPID(38));
    		addSequential(new Wait(.5));
    		addSequential(new Move(10));
    		addSequential(new CarriageSetRoller(.7), .3);
    		addSequential(new Move(-10));
    	}
    }
}
