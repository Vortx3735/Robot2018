package org.usfirst.frc.team3735.robot.commands.cubeintake;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.settings.Func;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CubeRollerSet extends Command {

    private Func speed;
    
    private boolean flipped = false;

	public CubeRollerSet(Func spd) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this(spd, false);
    }
	
	public CubeRollerSet(double spd) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this(Func.getFunc(spd));
    }
	
	public CubeRollerSet(double spd, boolean flipped) {
		this(Func.getFunc(spd), flipped);
	}
	public CubeRollerSet(Func spd, boolean flipped) {
		requires(Robot.cubeIntake);
		this.flipped = flipped;
    	this.speed = spd;
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(flipped) {
    		Robot.cubeIntake.setLeftMotorCurrent(speed.getValue());
    		Robot.cubeIntake.setRightMotorCurrent(-speed.getValue());
    	}else {
        	Robot.cubeIntake.setMotorsCurrent(speed.getValue());
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.cubeIntake.setMotorsCurrent(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
