package org.usfirst.frc.team3735.robot.subsystems;

import org.usfirst.frc.team3735.robot.settings.Constants;
import org.usfirst.frc.team3735.robot.settings.RobotMap;
import org.usfirst.frc.team3735.robot.util.settings.Setting;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeIntake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private WPI_VictorSPX leftMotor;
	private WPI_VictorSPX rightMotor;
	
	private Solenoid leftSolenoid;
	private Solenoid rightSolenoid;
	
	private Setting cubeIntakeSpeed;
	
	private boolean leftSolenoidOut;
	private boolean rightSolenoidOut;
	
	public CubeIntake(){
		leftMotor = new WPI_VictorSPX(RobotMap.CubeIntake.leftMotor);
		rightMotor = new WPI_VictorSPX(RobotMap.CubeIntake.rightMotor);
		
		leftSolenoid = new Solenoid(RobotMap.CubeIntake.leftSolenoid);
		rightSolenoid = new Solenoid(RobotMap.CubeIntake.rightSolenoid);
		
		cubeIntakeSpeed = new Setting("Cube Intake Speed", Constants.CubeIntake.cubeIntakeSpeed);
		
		leftSolenoidOut = false;
		rightSolenoidOut = false;
		
		rightMotor.setInverted(true);
	}
	
	public void setLeftMotorCurrent(double speed){
		leftMotor.set(speed);
	}
	
	public void setRightMotorCurrent(double speed){
		rightMotor.set(speed);
	}
	
	public void setMotorsCurrent(double speed){
		setLeftMotorCurrent(speed);
		setRightMotorCurrent(speed);
	}
	
	public double getDashboardSpeed(){
		return cubeIntakeSpeed.getValueFetched();
	}
	
	public void leftSolenoidOut(){
		leftSolenoid.set(true);
		leftSolenoidOut = true;
	}
	
	public void rightSolenoidOut(){
		rightSolenoid.set(true);
		rightSolenoidOut = true;
	}
	
	public void leftSolenoidIn(){
		leftSolenoid.set(false);
		leftSolenoidOut = false;
	}
	
	public void rightSolenoidIn(){
		rightSolenoid.set(false);
		rightSolenoidOut = false;
	}
	
	public void switchLeftSolenoid(){
		if(leftSolenoidOut){
			leftSolenoidIn();
		}else{
			leftSolenoidOut();
		}
	}
	
	public void switchRightSolenoid(){
		if(rightSolenoidOut){
			rightSolenoidIn();
		}else{
			rightSolenoidOut();
		}
	}
	
	public void solenoidsOut(){
		leftSolenoidOut();
		rightSolenoidOut();
	}
	
	public void solenoidsIn(){
		leftSolenoidIn();
		rightSolenoidIn();
	}
	
	public void switchSolenoids(){
		if(leftSolenoidOut && rightSolenoidOut){
			solenoidsIn();
		}
		if((!leftSolenoidOut) && (!rightSolenoidOut)){
			solenoidsOut();
		}
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

