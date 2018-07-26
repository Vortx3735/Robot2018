package org.usfirst.frc.team3735.robot.commands.drive.movedistance;

import org.usfirst.frc.team3735.robot.settings.Constants;
import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.calc.VortxMath;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveMoveDistancePID extends Command {
	
	private double deltaDistance;
	private double startTicksLeft;
	private double startTicksRight;
	private double endTicksLeft;
	private double endTicksRight;
	
	private double timeOnTarget = 0;
	private double finishTime = 0.5;
	
	private double p = .025;
	private double i = 0;
	private double d = 0;
	private double f = 0;

    public DriveMoveDistancePID(double distance){
    	requires(Robot.drive);
    	this.deltaDistance = distance;
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTicksLeft = Robot.drive.getLeftPositionInches()/Constants.Drive.InchesPerTick;
    	startTicksRight = Robot.drive.getRightPositionInches()/Constants.Drive.InchesPerTick;
    	double ticksToGo = deltaDistance/Constants.Drive.InchesPerTick;
    	endTicksLeft = startTicksLeft + ticksToGo;
    	endTicksRight = startTicksRight + ticksToGo;
    	
    	Robot.drive.setupForPositionControl();
    	Robot.drive.setPIDSettings(p,i,d,f);
    	
    	timeOnTarget = 0;
    	
    	System.out.println("Left ticks " + startTicksLeft);
    	System.out.println("Right distance " + startTicksRight);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		Robot.drive.setLeftRightDistance(endTicksLeft, endTicksRight);
		double ticksRight = endTicksRight-Robot.drive.getRightPositionInches()/Constants.Drive.InchesPerTick;
		double ticksLeft = endTicksRight-Robot.drive.getLeftPositionInches()/Constants.Drive.InchesPerTick;
		System.out.println( " need to go " + ticksLeft +  ", " + ticksRight +  " ticks");
    	if(isOnTarget()){
    		timeOnTarget += .02;
    	}else{
    		timeOnTarget = 0;
    	}
    }
    
    private boolean isOnTarget(){
    	return 	VortxMath.isWithinThreshold(Robot.drive.getLeftPositionInches()/Constants.Drive.InchesPerTick,
										   	endTicksLeft,
										   	Constants.Drive.driveTolerance) &&
    			VortxMath.isWithinThreshold(Robot.drive.getRightPositionInches()/Constants.Drive.InchesPerTick,
						   				   	endTicksRight,
						   				   	Constants.Drive.driveTolerance);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		return timeOnTarget >= finishTime;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.setupDriveForSpeedControl();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

}

