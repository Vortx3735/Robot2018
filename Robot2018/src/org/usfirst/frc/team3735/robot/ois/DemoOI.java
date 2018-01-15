package org.usfirst.frc.team3735.robot.ois;

import org.usfirst.frc.team3735.robot.util.oi.DriveOI;
import org.usfirst.frc.team3735.robot.util.oi.XboxController;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class DemoOI implements DriveOI{

	public XboxController main;
	public XboxController co;
	
	private boolean driveEnabled = true;
	public DemoOI() {

		main = new XboxController(0);
		co = new XboxController(1);

		//Baby Driver



		
		co.x.whenPressed(new InstantCommand(){
			@Override
			public void initialize(){
				driveEnabled = false;
			}
		});
		co.y.whenPressed(new InstantCommand(){
			@Override
			public void initialize(){
				driveEnabled = true;
			}
		});

	}
	
	@Override
	public double getDriveMove() {
		return driveEnabled ? main.getLeftY() : 0;
	}

	@Override
	public double getDriveTurn() {
		return driveEnabled ? main.getRightX() : 0;
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
