package org.usfirst.frc.team3735.robot.ois;

import org.usfirst.frc.team3735.robot.commands.carriage.CarriageRaiseTele;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageSetRoller;

import org.usfirst.frc.team3735.robot.commands.climber.ClimberSetSpeed;
import org.usfirst.frc.team3735.robot.commands.cubeintake.CubeGrab;
import org.usfirst.frc.team3735.robot.commands.cubeintake.CubeSetRoller;
import org.usfirst.frc.team3735.robot.commands.cubeintake.CubeSetSols;
import org.usfirst.frc.team3735.robot.commands.drive.TurnTo;
import org.usfirst.frc.team3735.robot.commands.drive.simple.DriveAddSensitiveLeft;
import org.usfirst.frc.team3735.robot.commands.drive.simple.DriveAddSensitiveRight;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorCorrect;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosLgs;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosSetting;
import org.usfirst.frc.team3735.robot.commands.sequences.AutoScaleLineup;
import org.usfirst.frc.team3735.robot.commands.sequences.AutoSwitchLineup;
import org.usfirst.frc.team3735.robot.settings.Constants;
import org.usfirst.frc.team3735.robot.subsystems.Elevator;
import org.usfirst.frc.team3735.robot.triggers.CarriageOverload;
import org.usfirst.frc.team3735.robot.util.calc.VortxMath;
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
		

		
		main.pov90.whileHeld(new DriveAddSensitiveRight());
		main.pov270.whileHeld(new DriveAddSensitiveLeft());
		
//		main.pov0
//		main.pov180

//		main.y
		main.x.whileHeld(new CubeSetRoller(new Setting("Cube Intake Speed", -.7)));
		main.x.whileHeld(new CarriageSetRoller(new Setting("Carriage Intake Speed", -.5)).addT(new CarriageOverload(new Setting("Intake MaxPower", 30))));
		
		main.b.whileHeld(new CubeSetRoller(new Setting("Cube Outtake Speed", .5)));
		main.b.whileHeld(new CarriageSetRoller(new Setting("Carriage Outtake Speed", .5)));
		main.a.whileHeld(new CubeGrab());

//		main.pov0.whenPressed(new TurnTo(0));
		Setting maxp = new Setting("Logis Max P", 1);
//		main.pov0.whenPressed(new ElevatorSetPosLgs(38,maxp));
//		main.pov270.whenPressed(new ElevatorSetPosLgs(13,maxp));
//		main.pov180.whenPressed(new ElevatorSetPosLgs(0,maxp));
//		main.pov180.whenPressed(new AutoSwitchLineup(true));
//		main.pov0.whenPressed(new AutoScaleLineup(true));
		main.pov0.whenPressed(new TurnTo(0));
		main.pov90.whenPressed(new TurnTo(90));
		main.pov180.whenPressed(new TurnTo(180));
		main.pov270.whenPressed(new TurnTo(270));


//		main.lb.whenPressed(command);
		Setting spin = new Setting("Cube Spin Speed", .7);
		main.rb.whileHeld(new CubeSetRoller(spin, true));
//		main.rb.whileHeld(new CubeSetSols(false, true));
//		main.lb.whileHeld(new CubeSetSols(true, false));
		main.lb.whileHeld(new CubeSetRoller(spin.reverse(), true));

		
//		main.start
//		main.back
		

		
		co.y.whileHeld(new ClimberSetSpeed(new Setting("Climb Speed", 1)));
		co.x.whenPressed(new ClimberSetSpeed(0));

		Setting carriageFine = new Setting("Carriage Fine Speed", .2);
		co.a.whileHeld(new CarriageSetRoller(carriageFine.reverse()));
		co.b.whileHeld(new CarriageSetRoller(carriageFine));
		
		Setting carriageShoot = new Setting("Carriage Shoot Speed", 1);
		co.lt.whileHeld(new CarriageSetRoller(carriageShoot.reverse()));
		co.rt.whileHeld(new CarriageSetRoller(carriageShoot));
		co.rb.toggleWhenPressed(new CarriageRaiseTele());
		
		
		Setting elevatorTrim = new Setting("Elevator Trim", .3);
		co.pov90.whileHeld(new ElevatorCorrect(elevatorTrim));
		co.pov270.whileHeld(new ElevatorCorrect(elevatorTrim.reverse()));

		
		

//		Setting position = new Setting("Position", 0);
//		PIDSetting setting = new PIDSetting(80, .15, 60, 0 ,0, 0, "PID Setting", true);
//		SmartDashboard.putData(new ElevatorSetPositionSetting(position, setting));
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
		double d = VortxMath.handleDeadband(main.getRightY()+ co.getLeftY(), .05);
		return (Math.abs(d) > .1) ? d : 0;
	}
	
	
	public boolean isOverriddenByDrive(){
		return Math.abs(getDriveMove()) > .4 || Math.abs(getDriveTurn()) > .4;
	}
	
	public double getCarriageIntakeMove() {
		return VortxMath.handleDeadband(co.getRightY(), .05);
	}
	
	public double getIntakeTwist() {
		return VortxMath.handleDeadband(co.getRightX(), .05);
	}

	
	public void log() {
//		SmartDashboard.putNumber("right joystick angle", getMainRightAngle());
//		SmartDashboard.putNumber("right joystick magnitude",
//				getMainRightMagnitude());

	}




}
