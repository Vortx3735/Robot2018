package org.usfirst.frc.team3735.robot.commands.cubeintake;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.settings.Func;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CubeAnglerSetPID extends Command {
	Func f;
    public CubeAnglerSetPID(Func f) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.f = f;
    	requires(Robot.angler);
    }
    
    public CubeAnglerSetPID(double n) {
    	this(Func.getFunc(n));
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.angler.controller.setSetpoint(f.getValue());
    	Robot.angler.controller.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.angler.controller.setSetpoint(f.getValue());

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.angler.controller.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
