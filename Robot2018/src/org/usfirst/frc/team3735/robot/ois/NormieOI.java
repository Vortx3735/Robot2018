package org.usfirst.frc.team3735.robot.ois;

import org.usfirst.frc.team3735.robot.commands.drive.simple.DriveAddSensitiveLeft;
import org.usfirst.frc.team3735.robot.commands.drive.simple.DriveAddSensitiveRight;
import org.usfirst.frc.team3735.robot.util.oi.DriveOI;
import org.usfirst.frc.team3735.robot.util.oi.XboxController;

public class NormieOI implements DriveOI{

	public XboxController main;
	public XboxController co;

	public NormieOI() {

		main = new XboxController(0);
		co = new XboxController(1);

		//Baby Driver
		//main.pov180.whenPressed(new DriveGoToPeg());

		
		main.x.whileHeld(new DriveAddSensitiveLeft());
		main.y.whileHeld(new DriveAddSensitiveRight());




	}
	
	@Override
	public double getDriveMove() {
		return main.getLeftY();
	}

	@Override
	public double getDriveTurn() {
		return main.getRightX();
	}
	
	@Override
	public double getFODMag() {
		return 0;
	}
	
	@Override
	public double getFODAngle(){
		return 0;
	}

	@Override
	public boolean isOverriddenByDrive(){
		return Math.abs(getDriveMove()) > .1 || Math.abs(getDriveTurn()) > .1;
	}

	@Override
	public void log() {
//		SmartDashboard.putNumber("right joystick angle", getMainRightAngle());
//		SmartDashboard.putNumber("right joystick magnitude",
//				getMainRightMagnitude());

	}




}
