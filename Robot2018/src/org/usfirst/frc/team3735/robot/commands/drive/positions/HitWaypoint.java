package org.usfirst.frc.team3735.robot.commands.drive.positions;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.subsystems.Drive;
import org.usfirst.frc.team3735.robot.triggers.HasPassedWaypoint;
import org.usfirst.frc.team3735.robot.util.calc.VortxMath;
import org.usfirst.frc.team3735.robot.util.cmds.VortxCommand;
import org.usfirst.frc.team3735.robot.util.profiling.Location;
import org.usfirst.frc.team3735.robot.util.profiling.Position;
import org.usfirst.frc.team3735.robot.util.settings.Setting;

/**
 * 
 * @author Andrew
 * 
 * This class 
 *
 */
public class HitWaypoint extends VortxCommand{
	
	public Location target;
	private double speed;
	private boolean isReversed;
	
	
	//use 3.8 for navx assist!!!
	private double maxSpeed = .5;
	private double minSpeed = .2;
	
	private static Setting maxTurn = new Setting("Max Turn Profiling", 40);

	public HitWaypoint(Location target, boolean rev) {
		this(target, null, rev);
		
	}
	
	
	public HitWaypoint(Location target, Location from, boolean rev) {
		requires(Robot.drive);
		requires(Robot.navigation);
		this.target = target;
		addT(new HasPassedWaypoint(target, from));
		isReversed = rev;
	}
	
	@Override
	protected void initialize() {
		super.initialize();

	}
	

	@Override
	protected void execute() {
		super.execute();
		
		setControllerAngle();
		
		double err = Robot.navigation.getController().getError();
		
		//lower the speed for greater turn values
		speed =((maxSpeed-minSpeed)*Math.exp(-Math.abs(.05*err))) + minSpeed;
		if(isReversed) {
			speed *= -1;
		}
		Robot.drive.setNavxAssist(VortxMath.limit(err, -1 * maxTurn.getValue(), maxTurn.getValue()));
		Robot.drive.limitedDrive(speed, 0);
		
		
	}
	
	public void setControllerAngle() {
		Position p = Robot.navigation.getPosition();
		double targetYaw = Robot.navigation.getYawToLocation(target);
		if(isReversed) {
			targetYaw = VortxMath.navLimit(targetYaw + 180);
		}
		Robot.navigation.getController().setSetpoint(targetYaw);
	}

	@Override
	protected boolean isFinished() {
		return super.isFinished() || Robot.navigation.getPosition().distanceFrom(target) < 25;
	}

	@Override
	protected void end() {
		super.end();
		Robot.drive.setNavxAssist(0);
	}

	@Override
	protected void interrupted() {
		super.interrupted();
		end();
	}


}
