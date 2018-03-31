package org.usfirst.frc.team3735.robot.subsystems;

import java.nio.charset.Charset;

import org.usfirst.frc.team3735.robot.commands.elevator.BlankPID;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorMoveJoystick;
import org.usfirst.frc.team3735.robot.settings.Constants;
import org.usfirst.frc.team3735.robot.settings.RobotMap;
import org.usfirst.frc.team3735.robot.util.hardware.VortxTalon;
import org.usfirst.frc.team3735.robot.util.settings.PIDSetting;
import org.usfirst.frc.team3735.robot.util.settings.Setting;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	VortxTalon elevatorLeft;
	VortxTalon elevatorRight;
	
	public static double minDown = -.07;
	public static double minUp = .33;
	
	public static double bottom = 0;
	public static double switchHeight = 10;
	public static double top = 36;

	// private Setting carriageSpeed;

	public Setting consPower = new Setting("Elevator ConsPower", 0);	//.183 on the final


	public Elevator() {
		elevatorLeft = new VortxTalon(RobotMap.Elevator.elevatorLeft, "Elevator Left");
		elevatorRight = new VortxTalon(RobotMap.Elevator.elevatorRight, "Elevator Right");
		
		elevatorLeft.setPIDSetting(new PIDSetting(80, .15, 60));
		elevatorRight.setPIDSetting(new PIDSetting(80, .15, 60));
		
		elevatorLeft.setTicksPerInch(Constants.Elevator.ticksPerInch);
		elevatorRight.setTicksPerInch(Constants.Elevator.ticksPerInch);

		elevatorLeft.putOnDash();
		elevatorRight.putOnDash();
		
		elevatorLeft.setNeutralMode(NeutralMode.Brake);
		elevatorRight.setNeutralMode(NeutralMode.Brake);

		elevatorLeft.initSensor(FeedbackDevice.QuadEncoder, true);
		elevatorRight.initSensor(FeedbackDevice.QuadEncoder, true);
		
		resetEncoderPositions();
	}


	public void setupForPositionControl() {

	}

	public void resetEncoderPositions() {
		elevatorLeft.resetPosition();
		elevatorRight.resetPosition();
	}

	public void setPOutput(double speed) {
		setLeftPOutput(speed);
		setRightPOutput(speed);
	}
	
	public void setPOutputAdjusted(double speed) {
//		System.out.print("Trying: " + speed + "\t");
//		double actual = speed;
		if((getPosition() < 4 ) && (speed == 0)) {
//			actual = 0;
			setPOutput(0);
		}else {
			setPOutput(speed + consPower.getValue());
//			actual = speed + consPower.getValue();
		}
//		System.out.println("Sending: " + actual );

		
	}

	public void setLeftPOutput(double speed) {
		elevatorLeft.set(ControlMode.PercentOutput, speed);
//		System.out.println("Left Percent" + speed);
	}

	public void setRightPOutput(double speed) {
//		elevatorRight.set(ControlMode.PercentOutput, -speed);
//		if(speed < 0) {
//			elevatorRight.setInverted(false);
//
//		}else {
//			elevatorRight.setInverted(true);
//
//		}
		elevatorRight.set(ControlMode.PercentOutput, speed);

		//System.out.println("Right Percent" + speed);
	}

	
	public void setElevatorLeftPosition(double position) {
		elevatorLeft.set(ControlMode.Position, position);
	}

	public void setElevatorRightPosition(double position) {
		elevatorRight.set(ControlMode.Position, position);
	}
	

	public void setElevatorPIDSetting(PIDSetting setting) {
		elevatorLeft.setPIDSetting(setting);
		elevatorRight.setPIDSetting(setting);
	}

	public void setElevatorPosition(double position) {
		setElevatorLeftPosition(position);
		setElevatorRightPosition(position);
	}

	public void setElevatorPosition(double position, PIDSetting setting) {
		setElevatorPIDSetting(setting);
		setElevatorPosition(position);
	}


	
	public double getPosition() {
		return (.5 * (elevatorLeft.getPosition() + elevatorRight.getPosition()));
	}


	public void initDefaultCommand() {
		setDefaultCommand(new ElevatorMoveJoystick());
	}

	public void log() {
		elevatorLeft.log();
		elevatorRight.log();
		
		SmartDashboard.putNumber("Elevator Position", getPosition());
	}
	
	public void debugLog() {
		elevatorLeft.debugLog();
		elevatorRight.debugLog();
	}
}
