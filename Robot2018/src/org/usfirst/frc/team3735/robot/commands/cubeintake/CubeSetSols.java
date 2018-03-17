package org.usfirst.frc.team3735.robot.commands.cubeintake;

import org.usfirst.frc.team3735.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CubeSetSols extends Command {

    private boolean left;
	private boolean right;

	public CubeSetSols(boolean left, boolean right) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.left = left;
    	this.right = right;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.cubeIntake.setLeftSol(left);
    	Robot.cubeIntake.setRightSol(right);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.cubeIntake.setLeftSol(left);
    	Robot.cubeIntake.setRightSol(right);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.cubeIntake.setLeftSol(false);
    	Robot.cubeIntake.setRightSol(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
