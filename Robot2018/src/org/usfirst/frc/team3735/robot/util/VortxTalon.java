package org.usfirst.frc.team3735.robot.util;
import org.usfirst.frc.team3735.robot.commands.elevator.BlankPID;
import org.usfirst.frc.team3735.robot.util.settings.Setting;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard; 

public class VortxTalon extends WPI_TalonSRX{
	int id;
	String name;
	private PIDController PID;
	private Setting iZone;
	private Setting rampRate;
	
	
	double P = 0;
	double I = 0;
	double D = 0;
	double F = 0;
	double iZ = 0;
	double ramp = 1;
	
	
	double ticksPerInch = 1;
	
	public VortxTalon(int id){
		this(id, "Motor " + id + " PID", false);
	}
	
	public VortxTalon(int id, String name, boolean onDash){
		super(id);
		this.id = id;
		this.name= name;
		PID = new PIDController(P, I, D, new BlankPID(), new BlankPID());

		if(onDash){
			iZone = new Setting("iZoneLeft", iZ);
			SmartDashboard.putData(PID);
			rampRate = new Setting(name + "Ramp", ramp);
		}else{
			iZone = new Setting(name + "iZone", iZ, false);
			rampRate = new Setting(name + "Ramp", ramp, false);

		}
	}

	public void setPID(double kp, double ki, double kd){
		this.config_kP(0, kp, 0);
		this.config_kI(0, ki, 0);
		this.config_kD(0, kd, 0);
		
		this.P = kp;
		this.I = ki;
		this.D = kd;
	}
	
	public void setPIDF(double kp, double ki, double kd, double kf) {
		setPID(kp,ki,kd);
		this.config_kF(0, kf, 0);
		this.F = kf;
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
	
	public void updatePID() {
    	this.setPIDF(PID.getP()/ticksPerInch, PID.getI()/ticksPerInch, PID.getD()/ticksPerInch, PID.getF());
    	this.config_IntegralZone(0,  (int)(iZone.getValue() * ticksPerInch), 0);
		this.configClosedloopRamp(rampRate.getValue(), 0);
    }
	
	@Override
	public void set(ControlMode mode, double value){
		if(mode == ControlMode.Position){
			updatePID();
			value *= ticksPerInch;
		}
		super.set(mode, value);
	}
	
	public PIDController getPIDController(){
		return PID;
	}

	public void printToDashboard(){
		SmartDashboard.putNumber((name + " Pos") , this.getSelectedSensorPosition(0));
		SmartDashboard.putNumber((name + " Inches"),this.getSelectedSensorPosition(0)/ticksPerInch);
	}
	

	
}
