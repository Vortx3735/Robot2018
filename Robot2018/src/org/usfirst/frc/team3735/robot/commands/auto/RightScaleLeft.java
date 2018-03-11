package org.usfirst.frc.team3735.robot.commands.auto;

import org.usfirst.frc.team3735.robot.commands.drive.recorder.SendProfile;
import org.usfirst.frc.team3735.robot.settings.Waypoints;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScaleLeft extends CommandGroup {

    public RightScaleLeft(boolean complex) {
    	if(complex) {
    		addSequential(new SendProfile(Waypoints.Auto.rightScaleLeft));	//initial cross to scale
    		addSequential(new SendProfile(Waypoints.Auto.rightScaleLeft2));	//backup and go to cube
    		addSequential(new SendProfile(Waypoints.Auto.rightScaleLeft3));	//backup from cube, go to scale
    		addSequential(new SendProfile(Waypoints.Auto.rightScaleLeft4));	//backup scale, go to other cube
    		addSequential(new SendProfile(Waypoints.Auto.rightScaleLeft5));	//backup from cube, go to scale
    		
    	}else {
    		
    	}
    }
}
