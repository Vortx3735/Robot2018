package org.usfirst.frc.team3735.robot.subsystems;

import org.usfirst.frc.team3735.robot.settings.Constants;
import org.usfirst.frc.team3735.robot.settings.RobotMap;
import org.usfirst.frc.team3735.robot.util.settings.Setting;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private WPI_TalonSRX motor1;
	private DoubleSolenoid solenoid;
	
	Setting initialSpeed;
	Setting tensionSpeed;
	
	public Climber(){
		motor1 = new WPI_TalonSRX(RobotMap.Climber.motor);
		solenoid = new DoubleSolenoid(0, 1);
		
		initialSpeed = new Setting("Initial Climber Speed", Constants.Climber.initialSpeed);
		tensionSpeed = new Setting("Tension Climber Speed", Constants.Climber.tensionSpeed);
	}
	
	public void setMotorCurrent(double speed){
		motor1.set(speed);
	}
	
	public void extend(){
		solenoid.set(Value.kForward);
	}
	
	public void retract(){
		solenoid.set(Value.kReverse);
	}
	
	public double getInitialSpeedSmartDashboard(){
    	return initialSpeed.getValueFetched();
    }
	
	public double getTensionSpeedSmartDashboard(){
    	return tensionSpeed.getValueFetched();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

