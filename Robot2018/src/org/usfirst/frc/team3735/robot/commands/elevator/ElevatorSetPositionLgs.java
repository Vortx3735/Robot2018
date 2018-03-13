package org.usfirst.frc.team3735.robot.commands.elevator;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.settings.Func;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorSetPositionLgs extends Command {
	Func inches;
	double initialPosition;
	double finalPosition;
	
	double dy;
	double k;

	final double startingPower = .1;
	Func maxPower;		
	//If we are going down, we can go 1.1 at max, because we are adding
	//say .1 to the value in Elevator.java, so we can go "1.1" down, but not only .9 up
	final double finishRadius = 1;
	
    public ElevatorSetPositionLgs(double inches) {
    	this(Func.getFunc(inches));
    	
    }
    
    public ElevatorSetPositionLgs(Func f) {
    	this(f, Func.getFunc(.7));
    }
    
    public ElevatorSetPositionLgs(Func f, Func maxPower) {
    	this.inches = f;
    	this.maxPower = maxPower;
    	requires(Robot.elevator);
    }
    
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	initialPosition = Robot.elevator.getPosition();
    	finalPosition = inches.getValue();
    	dy = finalPosition - initialPosition;
    	k = Math.abs(4 * maxPower.getValue() / (dy));
    }
    
    private double getPower() {
    	double calc = k * (Robot.elevator.getPosition() - initialPosition) * (1- (Robot.elevator.getPosition() / (dy)));
    	if(Math.abs(calc) < startingPower) {
    		calc = Math.copySign(startingPower, dy);
    	}
    	return calc;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.setPOutput(getPower());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(Math.abs(Robot.elevator.getPosition() - finalPosition) < finishRadius) {
        	return true;
        }else {
        	return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.setPOutput(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
