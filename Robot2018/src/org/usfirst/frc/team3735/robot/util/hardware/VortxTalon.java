package org.usfirst.frc.team3735.robot.util.hardware;
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
		this(id, "Talon " + (int)Math.abs(id));
	}
	
	public VortxTalon(int id, String name){
		super((int)Math.abs(id));
		this.name = name;
		
		if(id < 0) {
			this.setInverted(true);
		}else {
			this.setInverted(false);
		}
	}
	
	public void putOnDash(){
//		SmartDashboard.putData(name, this);
		setting.sendToDash(name + " PID");
	}

	public void setPID(double kp, double ki, double kd){
		this.config_kP(0, kp / ticksPerInch , 0);
		this.config_kI(0, ki / ticksPerInch, 0);
		this.config_kD(0, kd / ticksPerInch, 0);
		
		setting.setPID(kp, ki, kd);
	}
	
	public void setPIDF(double kp, double ki, double kd, double kf) {
		this.setPID(kp,ki,kd);
		this.config_kF(0, kf / ticksPerInch, 0);

		setting.setPID(kp, ki, kd, kf);
	}
	
	public void setPIDSetting(PIDSetting setting){
		this.setting = setting;

		this.setPIDF(setting.getP(), setting.getI(), setting.getD(), setting.getF());
		this.config_IntegralZone(0, (int)(setting.getiZone()*ticksPerInch), 0);
		this.configClosedloopRamp(setting.getRampRate(), 0);
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
			setPIDSetting(setting);
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

	
	public void log() {
		SmartDashboard.putNumber((name + " Inches"), this.getSelectedSensorPosition(0)/ticksPerInch);

	}
	
	public void debugLog() {
		SmartDashboard.putNumber(name + " P Output", this.getMotorOutputPercent());
//		SmartDashboard.putNumber(name + " S Pos", this.getSelectedSensorPosition(0));
	}
	
	public double getPosition() {
		return super.getSelectedSensorPosition(0) / ticksPerInch;
	}
	

	
}
