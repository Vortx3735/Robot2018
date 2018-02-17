package org.usfirst.frc.team3735.robot.subsystems;

import org.usfirst.frc.team3735.robot.commands.elevator.BlankPID;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorMove;
import org.usfirst.frc.team3735.robot.settings.Constants;
import org.usfirst.frc.team3735.robot.settings.RobotMap;
import org.usfirst.frc.team3735.robot.util.VortxTalon;
import org.usfirst.frc.team3735.robot.util.settings.Setting;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	VortxTalon elevatorLeft;
	VortxTalon elevatorRight;
	
	//private Setting carriageSpeed;
	
	private Setting elevatorMultiplier;
	private Setting correctionMultiplier;

	
	public Elevator(){
		elevatorLeft = new VortxTalon(RobotMap.Elevator.elevatorLeft, "Elevator Left", true);
		elevatorRight = new VortxTalon(RobotMap.Elevator.elevatorRight, "Elevator Right", true);
		
		elevatorMultiplier = new Setting("Elevator Move Multiplier", Constants.Elevator.elevatorMultiplier);
		correctionMultiplier = new Setting("Elevator Trim Multiplier", Constants.Elevator.correctionMultiplier);
		
		elevatorLeft.setNeutralMode(NeutralMode.Brake);
		elevatorRight.setNeutralMode(NeutralMode.Brake);
		
		elevatorRight.setInverted(true);
		
		setUpSensors();
		resetEncoderPositions();
	}
	
	public void setUpSensors(){
		elevatorLeft.initSensor(FeedbackDevice.QuadEncoder);
		elevatorRight.initSensor(FeedbackDevice.QuadEncoder);
	}
	

	
	public void setupForPositionControl() {

	}

	
	public void resetEncoderPositions(){
		elevatorLeft.resetPosition();
		elevatorRight.resetPosition();
	}
	
	public void setElevatorMotorsCurrent(double speed){
		setElevatorLeftCurrent(speed);
		setElevatorRightCurrent(speed);
    }
	
	public void setElevatorLeftCurrent(double speed){
		elevatorLeft.set(ControlMode.PercentOutput, speed);
	} 
	public void setElevatorRightCurrent(double speed){
		elevatorRight.set(ControlMode.PercentOutput, speed);
	} 
	
	public void setElevatorLeftPosition(double position){
		elevatorLeft.set(ControlMode.Position, position);	
	}
	
	public void setElevatorRightPosition(double position){
		elevatorRight.set(ControlMode.Position, position);
	}
	
	public void setElevatorPostion(double position){
		setElevatorLeftPosition(position);
		setElevatorRightPosition(position);
	}
//	public void moveElevatorInches(double inches){
//		double ticksToMove = (inches*Constants.Elevator.ticksPerInch);
//		elevatorLeft.set(ControlMode.Position, ticksToMove);
//		elevatorRight.set(ControlMode.Position, ticksToMove);
//	}

    
    public double getMultiplierSmartDashboard(){
    	return elevatorMultiplier.getValue();
    }
    public double getCorrectionMultiplierSmartDashboard(){
    	return correctionMultiplier.getValue();
    }
    /*
     * 2 = 
     */

    

    public void initDefaultCommand() {
    	setDefaultCommand(new ElevatorMove());
    }
    
    public void log(){
    	elevatorLeft.printToDashboard();
    	elevatorRight.printToDashboard();
    }
}

