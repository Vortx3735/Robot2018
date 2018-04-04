package org.usfirst.frc.team3735.robot.subsystems;


//import org.usfirst.frc.team3735.robot.settings.RobotMap;

//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import org.usfirst.frc.team3735.robot.settings.Constants;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;


public class LEDS extends Subsystem {
	I2C com = new I2C(I2C.Port.kOnboard, 5);
	
	public LEDS()
	{
		byte[] real = new byte[1];
		byte[] dummy = new byte[0];
		real[0] = 5;
		com.transaction(real, 1, dummy, 0);
	}
	//teleop
	public void SendDataAutonomous()
	{
		byte[] real = new byte[1];
		byte[] dummy = new byte[0];
		real[0] = 10;
		com.transaction(real, 1, dummy, 0);
		Timer.delay(.03);
	}
	//auto
	public void SendDataTeleop()
	{
		byte[] real = new byte[1];
		byte[] dummy = new byte[0];
		real[0] = 30;
		com.transaction(real, 1, dummy, 0);
		Timer.delay(.03);
	}
		
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
		
}
