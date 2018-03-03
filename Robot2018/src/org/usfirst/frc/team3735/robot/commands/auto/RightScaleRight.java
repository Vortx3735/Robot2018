package org.usfirst.frc.team3735.robot.commands.auto;

import org.usfirst.frc.team3735.robot.commands.Wait;
import org.usfirst.frc.team3735.robot.commands.drive.recorder.SendProfile;
import org.usfirst.frc.team3735.robot.settings.Waypoints;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScaleRight extends CommandGroup {

    public RightScaleRight() {
    	addSequential(new SendProfile(Waypoints.Auto.rightScaleRight));
    	addSequential(new Wait(.2));
    	addSequential(new SendProfile(Waypoints.Auto.rightScaleRight2));
    }
}
