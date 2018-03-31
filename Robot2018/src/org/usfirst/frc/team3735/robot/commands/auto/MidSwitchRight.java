package org.usfirst.frc.team3735.robot.commands.auto;

import org.usfirst.frc.team3735.robot.commands.carriage.CarriageSetRoller;
import org.usfirst.frc.team3735.robot.commands.drive.recorder.SendPreProfile;
import org.usfirst.frc.team3735.robot.commands.drive.recorder.SendProfile;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosRaw;
import org.usfirst.frc.team3735.robot.commands.sequences.AutoSwitchLineup;
import org.usfirst.frc.team3735.robot.settings.Waypoints;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MidSwitchRight extends CommandGroup {

    public MidSwitchRight(boolean complex) {
    	if(complex) {
//    		addSequential(new SendProfile(Waypoints.Auto.midSwitchRight));	//initial cross to scale
//    		addSequential(new SendPreProfile(""));
    		addSequential(new AutoSwitchLineup(true));
//    		addSequential(new ElevatorSetPosRaw(13),4);
//    		addSequential(new CarriageSetRoller(-.7), 1);
//    		addSequential(new SendProfile(Waypoints.Auto.midSwitchRight2));	//backup and go to cube
//    		addSequential(new SendProfile(Waypoints.Auto.midSwitchRight3));	//backup from cube, go to scale
//    		addSequential(new SendProfile(Waypoints.Auto.midSwitchRight4));	//backup scale, go to other cube
//    		addSequential(new SendProfile(Waypoints.Auto.midSwitchRight5));	//backup from cube, go to scale
    		
    	}else {
    		addSequential(new AutoSwitchLineup(true));

    	}
    }
}
