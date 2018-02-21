package org.usfirst.frc.team3735.robot.ois;

import org.usfirst.frc.team3735.robot.commands.carriage.CarriageRaise;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageRollerSet;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageRollerSetCheck;
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
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorDown;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPositionSetting;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorUp;
import org.usfirst.frc.team3735.robot.settings.Constants;
import org.usfirst.frc.team3735.robot.util.oi.DriveOI;
import org.usfirst.frc.team3735.robot.util.oi.XboxController;
import org.usfirst.frc.team3735.robot.util.settings.PIDSetting;
import org.usfirst.frc.team3735.robot.util.settings.Setting;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GTAOI implements DriveOI{

	public XboxController main;
	public XboxController co;

	public GTAOI() {

		main = new XboxController(0);
		co = new XboxController(1);
		
		main.lb.whileHeld(new CubeGrab());

		main.pov90.whileHeld(new DriveAddSensitiveRight());
		main.pov270.whileHeld(new DriveAddSensitiveLeft());

		
		main.a.whileHeld(new CarriageRollerSetCheck(new Setting("Carriage Intake Speed", -.5)));
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
		co.rb.toggleWhenPressed(new CarriageRaise());
		
		co.pov90.whileHeld(new ElevatorCorrectRight());
		co.pov270.whileHeld(new ElevatorCorrectLeft());
		co.pov0.whileHeld(new ElevatorUp());
		co.pov180.whileHeld(new ElevatorDown());
		
		Setting position = new Setting("Position", 0);
		PIDSetting setting = new PIDSetting(80, .15, 60, 0 ,0);
		SmartDashboard.putData(new ElevatorSetPositionSetting(position, setting));
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
		return main.getRightY()+ co.getRightY();
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
