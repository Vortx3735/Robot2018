package org.usfirst.frc.team3735.robot.commands.drive;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.triggers.HasMoved;
import org.usfirst.frc.team3735.robot.util.calc.DDxLimiter;
import org.usfirst.frc.team3735.robot.util.calc.Range;
import org.usfirst.frc.team3735.robot.util.cmds.VortxCommand;


/**
 *
 */
public class MoveDDx extends VortxCommand {

	double startSpeed;
	double maxSpeed;
	HasMoved distHandler;
	
	double stoppingDist;
	
	DDxLimiter limiter;
	
	private boolean isAcc;
	private double targetSpeed;
	
    public MoveDDx(double dist, double maxSpeed, double acc) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	limiter = new DDxLimiter(0, new Range(acc));
    	distHandler = new HasMoved(dist);
    	this.maxSpeed = maxSpeed;
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startSpeed = 0;
    	distHandler.initialize();
    	isAcc = true;
    	
    	targetSpeed = maxSpeed;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Math.abs(limiter.value) >= Math.abs(maxSpeed) && isAcc || distHandler.distanceToGo() < distHandler.distanceTraveled()) {
    		stoppingDist = distHandler.distanceTraveled();
    		isAcc = false;
    	}
    	if(!isAcc) {
    		if(distHandler.distanceToGo() < stoppingDist) {
    			targetSpeed = 0;
    		}
    	}
    	Robot.drive.normalDrive(limiter.feed(targetSpeed), 0);
    	System.out.println("Target spd" + targetSpeed);
    	super.execute();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return distHandler.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
