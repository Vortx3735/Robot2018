package org.usfirst.frc.team3735.robot.subsystems;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.commands.cubeintake.CubeSetRoller;
import org.usfirst.frc.team3735.robot.settings.Constants;
import org.usfirst.frc.team3735.robot.settings.RobotMap;
import org.usfirst.frc.team3735.robot.util.hardware.VortxTalon;
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
	private Solenoid sol2;
	
	private boolean isGripping;
	
	public CubeIntake(){
		leftMotor = new WPI_VictorSPX(RobotMap.CubeIntake.leftMotor);
		rightMotor = new WPI_VictorSPX(RobotMap.CubeIntake.rightMotor);
		
		rightMotor.setInverted(true);
		solenoid = new Solenoid(RobotMap.CubeIntake.solenoid);
		sol2 = new Solenoid(RobotMap.CubeIntake.solenoid2);
//		cubeIntakeSpeed = new Setting("Cube Intake Speed", Constants.CubeIntake.cubeIntakeSpeed);
		
		isGripping = false;

	}
	
	public void setLeftMotorCurrent(double speed){
		leftMotor.set(speed);
	}
	
	public void setRightMotorCurrent(double speed){
		rightMotor.set(speed);
	}
	
	public void setMotorsCurrent(double speed){
		setLeftMotorCurrent(speed + Robot.oi.getCarriageIntakeMove() + Robot.oi.getIntakeTwist());
		setRightMotorCurrent(speed + Robot.oi.getCarriageIntakeMove() - Robot.oi.getIntakeTwist());
	}
//	
//	public double getDashboardSpeed(){
//		return cubeIntakeSpeed.getValueFetched();
//	}
	
	
	
	public void grab(){
		solenoid.set(true);
		sol2.set(true);
		isGripping = true;
	}
	
	public void release(){
		solenoid.set(false);
		sol2.set(false);

		isGripping = false;
	}
	
	public void switchSolenoid(){
		if(isGripping){
			release();
		}else{
			grab();
		}	
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CubeSetRoller(0));
    }
}

