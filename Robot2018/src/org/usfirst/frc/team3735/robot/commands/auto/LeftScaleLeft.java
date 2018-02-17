package org.usfirst.frc.team3735.robot.commands.auto;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.settings.Waypoints;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleLeft extends CommandGroup {

    public LeftScaleLeft() {
       Robot.navigation.setPosition(Waypoints.Starting.left);
    }
}
