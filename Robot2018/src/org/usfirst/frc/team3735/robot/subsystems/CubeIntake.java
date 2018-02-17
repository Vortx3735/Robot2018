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
	
	private Solenoid solenoid;
	
	private Setting cubeIntakeSpeed;
	
	private boolean solenoidOut;
	
	public CubeIntake(){
		leftMotor = new WPI_VictorSPX(RobotMap.CubeIntake.leftMotor);
		rightMotor = new WPI_VictorSPX(RobotMap.CubeIntake.rightMotor);
		
		solenoid = new Solenoid(RobotMap.CubeIntake.solenoid);
		
		cubeIntakeSpeed = new Setting("Cube Intake Speed", Constants.CubeIntake.cubeIntakeSpeed);
		
		solenoidOut = false;
		
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
	
	
	
	public void solenoidOut(){
		solenoid.set(true);
		solenoidOut = true;
	}
	
	public void solenoidIn(){
		solenoid.set(false);
		solenoidOut = false;
	}
	
	public void switchSolenoid(){
		if(solenoidOut){
			solenoidIn();
		}else{
			solenoidOut();
		}	
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

