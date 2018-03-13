package org.usfirst.frc.team3735.robot.ois;

import org.usfirst.frc.team3735.robot.commands.carriage.CarriageRollerSet;
import org.usfirst.frc.team3735.robot.commands.climber.ClimberExtend;
import org.usfirst.frc.team3735.robot.commands.climber.ClimberRetract;
import org.usfirst.frc.team3735.robot.commands.climber.ClimberSetSpeed;
import org.usfirst.frc.team3735.robot.commands.cubeintake.CubeGrab;
import org.usfirst.frc.team3735.robot.commands.cubeintake.CubeRollerSet;
import org.usfirst.frc.team3735.robot.commands.drive.simple.DriveAddSensitiveLeft;
import org.usfirst.frc.team3735.robot.commands.drive.simple.DriveAddSensitiveRight;

import org.usfirst.frc.team3735.robot.util.oi.DriveOI;
import org.usfirst.frc.team3735.robot.util.oi.GraphiteJoystick;
import org.usfirst.frc.team3735.robot.util.oi.GraysWheel;
import org.usfirst.frc.team3735.robot.util.oi.XboxController;
import org.usfirst.frc.team3735.robot.util.settings.Setting;

public class WheelOI implements DriveOI{

	GraysWheel wheel;
	GraphiteJoystick stick;
	XboxController box;
	
	public WheelOI(){
		wheel = new GraysWheel(0);
		stick = new GraphiteJoystick(1);
		box = new XboxController(2);
		
		stick.trig.whileHeld(new CubeGrab());

//		main.pov90.whileHeld(new DriveAddSensitiveRight());
//		main.pov270.whileHeld(new DriveAddSensitiveLeft());
		
		
		stick.left.whileHeld(new CarriageRollerSet(new Setting("Carriage Intake Speed", -.5)));
		stick.left.whileHeld(new CubeRollerSet(new Setting("Cube Intake Speed", -.5)));
		stick.right.whileHeld(new CubeRollerSet(new Setting("Cube Outtake Speed", .5)));
		
		
		
		box.start.whenPressed(new ClimberExtend());
		box.back.whenPressed(new ClimberRetract());
		
		box.y.whileHeld(new ClimberSetSpeed(new Setting("Climb Speed", 1)));
		box.x.whenPressed(new ClimberSetSpeed(0));

		Setting carriageFine = new Setting("Carriage Fine Speed", .2);
		box.a.whileHeld(new CarriageRollerSet(carriageFine.reverse()));
		box.b.whileHeld(new CarriageRollerSet(carriageFine));
		
		Setting carriageShoot = new Setting("Carriage Shoot Speed", 1);
		stick.pov0.whileHeld(new CarriageRollerSet(carriageShoot));
		stick.pov180.whileHeld(new CarriageRollerSet(carriageShoot.reverse()));
		
		box.lt.whileHeld(new CarriageRollerSet(carriageShoot.reverse()));
		box.rt.whileHeld(new CarriageRollerSet(carriageShoot));
		
//		box.pov90.whileHeld(new ElevatorCorrectRight());
//		box.pov270.whileHeld(new ElevatorCorrectLeft());
	}
	
	public double getDriveMove() {
		return stick.getGY();
		//return main.getLeftY();
	}

	public double getDriveTurn() {
		return wheel.getGX();
		//return main.getRightX();
	}
	
	@Override
	public double getFODMag() {
		//return main.getRightMagnitude();
		return 0;
	}
	
	public double getFODAngle(){
		//return main.getRightAngle();
		return 0;
	}

	public double getElevatorMove() {
		return box.getRightY();
	}
	
	public boolean isOverriddenByDrive(){
		return Math.abs(getDriveMove()) > .4 || Math.abs(getDriveTurn()) > .4;
	}

	
	public void log() {
//		SmartDashboard.putNumber("right joystick angle", getMainRightAngle());
//		SmartDashboard.putNumber("right joystick magnitude",
//				getMainRightMagnitude());

	}
}
