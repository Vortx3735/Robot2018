package org.usfirst.frc.team3735.robot.subsystems;

import org.usfirst.frc.team3735.robot.settings.Constants;
import org.usfirst.frc.team3735.robot.settings.RobotMap;
import org.usfirst.frc.team3735.robot.util.settings.Setting;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Carriage extends Subsystem {

	WPI_TalonSRX carriageLeft;
	WPI_TalonSRX carriageRight;
	
	Solenoid solenoid;
	private boolean raised;
	
	
	public Carriage(){
		carriageLeft = new WPI_TalonSRX(RobotMap.Carriage.carriageLeft);
		carriageRight = new WPI_TalonSRX(RobotMap.Carriage.carraigeRight);
		
		solenoid = new Solenoid(RobotMap.Carriage.solenoid);
		
		carriageLeft.setInverted(true);
		raised = false;
	}
	
	public void setCarriageLeftCurrent(double speed){
		carriageLeft.set(speed);
	}
	
	public void setCarriageRightCurrent(double speed){
		carriageRight.set(speed);
	}
	
	public void setCarriageCurrent(double speed){
		setCarriageLeftCurrent(speed);
		setCarriageRightCurrent(speed);
	}
	
	public void raise(){
		solenoid.set(true);
		raised = true;
	}
	
	public void lower(){
		solenoid.set(false);
		raised = false;
	}
	
	public void switchSolenoid(){
		if(raised){
			lower();
		}else{
			raise();
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

