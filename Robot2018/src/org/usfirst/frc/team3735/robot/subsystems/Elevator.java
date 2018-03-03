package org.usfirst.frc.team3735.robot.subsystems;

import org.usfirst.frc.team3735.robot.commands.elevator.BlankPID;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorMove;
import org.usfirst.frc.team3735.robot.settings.Constants;
import org.usfirst.frc.team3735.robot.settings.RobotMap;
import org.usfirst.frc.team3735.robot.util.VortxTalon;
import org.usfirst.frc.team3735.robot.util.settings.PIDSetting;
import org.usfirst.frc.team3735.robot.util.settings.Setting;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	TalonSRX elevatorLeft;
	TalonSRX elevatorRight;

	// private Setting carriageSpeed;

	private Setting elevatorMultiplier;
	private Setting correctionMultiplier;

	public Elevator() {
		elevatorLeft = new WPI_TalonSRX(RobotMap.Elevator.elevatorLeft);// "Elevator Left", true);
		elevatorRight = new WPI_TalonSRX(RobotMap.Elevator.elevatorRight);//, "Elevator Right", true);

		elevatorMultiplier = new Setting("Elevator Move Multiplier", Constants.Elevator.elevatorMultiplier);
		correctionMultiplier = new Setting("Elevator Trim Multiplier", Constants.Elevator.correctionMultiplier);

		elevatorLeft.setNeutralMode(NeutralMode.Brake);
		elevatorRight.setNeutralMode(NeutralMode.Brake);

		//elevatorRight.setInverted(true);
		
		elevatorLeft.configPeakOutputForward(1, 0);
		elevatorLeft.configPeakOutputReverse(-1, 0);
		
		elevatorRight.configPeakOutputForward(1, 0);
		elevatorRight.configPeakOutputReverse(-1, 0);

		setUpSensors();
		resetEncoderPositions();
	}

	public void setUpSensors() {
//		elevatorLeft.initSensor(FeedbackDevice.QuadEncoder);
//		elevatorRight.initSensor(FeedbackDevice.QuadEncoder);
	}

	public void setupForPositionControl() {

	}

	public void resetEncoderPositions() {
//		elevatorLeft.resetPosition();
//		elevatorRight.resetPosition();
		
		elevatorLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		elevatorLeft.setSelectedSensorPosition(0, 0, 0);
		elevatorLeft.setSensorPhase(true);
		
		elevatorRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		elevatorRight.setSelectedSensorPosition(0, 0, 0);
		elevatorRight.setSensorPhase(true);
	}

	public void setElevatorMotorsCurrent(double speed) {
//		if (speed != 0) {
//			double difference = (elevatorLeft.getSelectedSensorPosition(0)
//					- elevatorRight.getSelectedSensorPosition(0));
//			if (speed > 0) {
//				if (difference >= 50) {
//					setElevatorLeftCurrent(speed - .02);
//					setElevatorRightCurrent(speed);
//				} else if (difference <= -50) {
//					setElevatorLeftCurrent(speed);
//					setElevatorRightCurrent(speed - .02);
//				} else {
//					setElevatorLeftCurrent(speed);
//					setElevatorRightCurrent(speed);
//				}
//			}else if (speed < 0){
//				if (difference >= 50) {
//					setElevatorLeftCurrent(speed - .02);
//					setElevatorRightCurrent(speed);
//				} else if (difference <= -50) {
//					setElevatorLeftCurrent(speed);
//					setElevatorRightCurrent(speed - .02);
//				} else {
//					setElevatorLeftCurrent(speed);
//					setElevatorRightCurrent(speed);
//				}
//				
//			}
//		}
		
		setElevatorLeftCurrent(speed);
		setElevatorRightCurrent(speed);
	}

	public void setElevatorLeftCurrent(double speed) {
		elevatorLeft.set(ControlMode.PercentOutput, speed);
		System.out.println("Left Percent" + speed);
	}

	public void setElevatorRightCurrent(double speed) {
//		elevatorRight.set(ControlMode.PercentOutput, -speed);
		if(speed < 0) {
			elevatorRight.setInverted(false);

		}else {
			elevatorRight.setInverted(true);

		}
		elevatorRight.set(ControlMode.PercentOutput, Math.abs(speed));

		System.out.println("Right Percent" + speed);
	}

	
	public void setElevatorLeftPosition(double position) {
		elevatorLeft.set(ControlMode.Position, position);
	}

	public void setElevatorRightPosition(double position) {
		elevatorRight.set(ControlMode.Position, position);
	}

	public void setElevatorPIDSetting(PIDSetting setting) {
//		elevatorLeft.setPIDSetting(setting);
//		elevatorRight.setPIDSetting(setting);
	}

	public void setElevatorPosition(double position) {
		setElevatorLeftPosition(position);
		setElevatorRightPosition(position);
	}

	public void setElevatorPosition(double position, PIDSetting setting) {
		setElevatorPosition(position);
		setElevatorPIDSetting(setting);
	}
	// public void moveElevatorInches(double inches){
	// double ticksToMove = (inches*Constants.Elevator.ticksPerInch);
	// elevatorLeft.set(ControlMode.Position, ticksToMove);
	// elevatorRight.set(ControlMode.Position, ticksToMove);
	// }

	public double getMultiplierSmartDashboard() {
		return elevatorMultiplier.getValue();
	}

	public double getCorrectionMultiplierSmartDashboard() {
		return correctionMultiplier.getValue();
	}
	/*
	 * 2 =
	 */

	public void initDefaultCommand() {
		setDefaultCommand(new ElevatorMove());
	}

	public void log() {
//		elevatorLeft.printToDashboard();
//		elevatorRight.printToDashboard();
	}
}
