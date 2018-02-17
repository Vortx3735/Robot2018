package org.usfirst.frc.team3735.robot.ois;

import org.usfirst.frc.team3735.robot.commands.cubeintake.CubeIntakeRollersIn;
import org.usfirst.frc.team3735.robot.commands.cubeintake.CubeIntakeRollersOut;
import org.usfirst.frc.team3735.robot.commands.cubeintake.CubeIntakeSwitchSolenoid;
import org.usfirst.frc.team3735.robot.commands.drive.simple.DriveAddSensitiveLeft;
import org.usfirst.frc.team3735.robot.commands.drive.simple.DriveAddSensitiveRight;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorCorrectLeft;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorCorrectRight;
import org.usfirst.frc.team3735.robot.util.oi.DriveOI;
import org.usfirst.frc.team3735.robot.util.oi.XboxController;

public class GTAOI implements DriveOI{

	public XboxController main;
	public XboxController co;

	public GTAOI() {

		main = new XboxController(0);
		co = new XboxController(1);
		main.rb.get();

		main.pov90.whileHeld(new DriveAddSensitiveRight());
		main.pov270.whileHeld(new DriveAddSensitiveLeft());

		main.a.whileHeld(new CubeIntakeRollersIn());
		main.b.whileHeld(new CubeIntakeRollersOut());
		main.rb.toggleWhenPressed(new CubeIntakeSwitchSolenoid());
		
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
