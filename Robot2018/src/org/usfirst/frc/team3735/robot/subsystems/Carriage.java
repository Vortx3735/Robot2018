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
	private boolean solenoidOut;
	
	private Setting carriageSpeed;
	
	public Carriage(){
		carriageLeft = new WPI_TalonSRX(RobotMap.Carriage.carriageLeft);
		carriageRight = new WPI_TalonSRX(RobotMap.Carriage.carraigeRight);
		carriageSpeed = new Setting("Carriage Speed", Constants.Carriage.carriageSpeed);
		
		solenoid = new Solenoid(RobotMap.Carriage.solenoid);
		
		carriageRight.setInverted(true);
		solenoidOut = false;
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
	
	 public double getCarriageSpeedSmartDashboard(){
	    	return carriageSpeed.getValue();
	    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

