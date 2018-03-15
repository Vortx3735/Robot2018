package org.usfirst.frc.team3735.robot.commands.auto;

import org.usfirst.frc.team3735.robot.commands.carriage.CarriageSetRoller;
import org.usfirst.frc.team3735.robot.commands.drive.recorder.SendProfile;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosRaw;
import org.usfirst.frc.team3735.robot.settings.Waypoints;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MidSwitchLeft extends CommandGroup {

    public MidSwitchLeft(boolean complex) {
    	if(complex) {
    		addSequential(new SendProfile(Waypoints.Auto.midSwitchLeft));	//initial cross to scale
    		addSequential(new ElevatorSetPosRaw(15),4);
    		addSequential(new CarriageSetRoller(-.7), 1);
//    		addSequential(new SendProfile(Waypoints.Auto.midSwitchLeft2));	//backup and go to cube
//    		addSequential(new SendProfile(Waypoints.Auto.midSwitchLeft3));	//backup from cube, go to scale
//    		addSequential(new SendProfile(Waypoints.Auto.midSwitchLeft4));	//backup scale, go to other cube
//    		addSequential(new SendProfile(Waypoints.Auto.midSwitchLeft5));	//backup from cube, go to scale
    		
    	}else {
    		
    	}
    }
}
