package org.usfirst.frc.team3735.robot.commands.climber;

import org.usfirst.frc.team3735.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ClimberRetract extends InstantCommand {

    public ClimberRetract() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.climber.retract();
    }
}
