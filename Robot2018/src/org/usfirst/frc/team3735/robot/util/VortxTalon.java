package org.usfirst.frc.team3735.robot.util;
import org.usfirst.frc.team3735.robot.commands.elevator.BlankPID;
import org.usfirst.frc.team3735.robot.util.settings.PIDSetting;
import org.usfirst.frc.team3735.robot.util.settings.Setting;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard; 

public class VortxTalon extends WPI_TalonSRX{
	
	private PIDSetting setting; 
	private String name;
	
	double ticksPerInch = 1;
	
	public VortxTalon(int id){
		this(id, "Talon " + id);
	}
	
	public VortxTalon(int id, String name){
		super(id);
		this.name = name;
	}
	
	public void putOnDash(){
		SmartDashboard.putData(name, this);
	}

	public void setPID(double kp, double ki, double kd){
		this.config_kP(0, kp, 0);
		this.config_kI(0, ki, 0);
		this.config_kD(0, kd, 0);
		
		setting.setPID(kp, ki, kd);
	}
	
	public void setPIDF(double kp, double ki, double kd, double kf) {
		this.setPID(kp,ki,kd);
		this.config_kF(0, kf, 0);

		setting.setPID(kp, ki, kd, kf);
	}
	
	public void setPIDSetting(PIDSetting setting){
		this.setPIDF(setting.getP(), setting.getI(), setting.getD(), setting.getF());
		this.config_IntegralZone(0, (int)(setting.getiZone()*ticksPerInch), 0);
		this.configClosedloopRamp(setting.getRampRate(), 0);
		this.setting = setting;
	}
	
	public void setTicksPerInch(double ticks){
		this.ticksPerInch = ticks;
	}
	
	public void initSensor(FeedbackDevice device){
		this.configSelectedFeedbackSensor(device, 0, 0);
		this.setSelectedSensorPosition(0, 0, 0);
		this.setSensorPhase(true);
		this.configNominalOutputForward(0, 0);
		this.configNominalOutputReverse(0, 0);
		this.configPeakOutputForward(1, 0);
		this.configPeakOutputReverse(-1, 0);
	}
	
	public void resetPosition(){
		this.setSelectedSensorPosition(0, 0, 0);
	}
	
	@Override
	public void set(ControlMode mode, double value){
		if(mode == ControlMode.Position){
			value *= ticksPerInch;
		}
		super.set(mode, value);
	}
	
	public void setWithPID(double value, PIDSetting setting){
		setPIDSetting(setting);
		set(ControlMode.Position, value);
	}
	
	public PIDSetting getPIDSetting(){
		return setting;
	}

	public void printToDashboard(){
		SmartDashboard.putNumber((name + " Pos") , this.getSelectedSensorPosition(0));
		SmartDashboard.putNumber((name + " Inches"),this.getSelectedSensorPosition(0)/ticksPerInch);
	}
	

	
}
