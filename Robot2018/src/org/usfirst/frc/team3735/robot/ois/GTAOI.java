package org.usfirst.frc.team3735.robot.ois;

import org.usfirst.frc.team3735.robot.commands.carriage.CarriageRollerSet;
import org.usfirst.frc.team3735.robot.commands.climber.ClimberExtend;
import org.usfirst.frc.team3735.robot.commands.climber.ClimberRetract;
import org.usfirst.frc.team3735.robot.commands.climber.ClimberSetSpeed;
import org.usfirst.frc.team3735.robot.commands.cubeintake.CubeGrab;
import org.usfirst.frc.team3735.robot.commands.cubeintake.CubeIntakeSwitchSolenoid;
import org.usfirst.frc.team3735.robot.commands.cubeintake.CubeRollerSet;
import org.usfirst.frc.team3735.robot.commands.drive.simple.DriveAddSensitiveLeft;
import org.usfirst.frc.team3735.robot.commands.drive.simple.DriveAddSensitiveRight;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorCorrectLeft;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorCorrectRight;
import org.usfirst.frc.team3735.robot.settings.Constants;
import org.usfirst.frc.team3735.robot.util.oi.DriveOI;
import org.usfirst.frc.team3735.robot.util.oi.XboxController;
import org.usfirst.frc.team3735.robot.util.settings.Setting;

public class GTAOI implements DriveOI{

	public XboxController main;
	public XboxController co;

	public GTAOI() {

		main = new XboxController(0);
		co = new XboxController(1);
		
		main.rb.whileHeld(new CubeGrab());

		main.pov90.whileHeld(new DriveAddSensitiveRight());
		main.pov270.whileHeld(new DriveAddSensitiveLeft());

		
		main.a.whileHeld(new CarriageRollerSet(new Setting("Carriage Intake Speed", -.5)));
		main.a.whileHeld(new CubeRollerSet(new Setting("Cube Intake Speed", -.5)));
		main.b.whileHeld(new CubeRollerSet(new Setting("Cube Outtake Speed", .5)));
		
		co.start.whenPressed(new ClimberExtend());
		co.back.whenPressed(new ClimberRetract());
		
		co.y.whileHeld(new ClimberSetSpeed(new Setting("Climb Speed", 1)));
		co.x.whenPressed(new ClimberSetSpeed(0));

		Setting carriageFine = new Setting("Carriage Fine Speed", .2);
		co.a.whileHeld(new CarriageRollerSet(carriageFine.reverse()));
		co.b.whileHeld(new CarriageRollerSet(carriageFine));
		
		Setting carriageShoot = new Setting("Carriage Shoot Speed", 1);
		co.lt.whileHeld(new CarriageRollerSet(carriageShoot.reverse()));
		co.rt.whileHeld(new CarriageRollerSet(carriageShoot));
		
		co.pov90.whileHeld(new ElevatorCorrectRight());
		co.pov270.whileHeld(new ElevatorCorrectLeft());
	}
	
	
	public double getDriveMove() {
		return (main.getRightTrigger() - main.getLeftTrigger());
		//return main.getLeftY();
	}

	public double getDriveTurn() {
		return main.getLeftX();
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
		return main.getRightY();
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
