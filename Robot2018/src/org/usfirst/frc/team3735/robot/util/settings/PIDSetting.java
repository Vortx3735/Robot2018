package org.usfirst.frc.team3735.robot.util.settings;

import org.usfirst.frc.team3735.robot.commands.elevator.BlankPID;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDSetting extends PIDController{
	private Setting iZone;
	private Setting rampRate;
	private String name;

	public PIDSetting(double P, double I, double D){
		this(P, I, D, 0);
	}
	
	public PIDSetting(double P, double I, double D, double F){
		this(P, I, D, F, 0);
	}
	
	public PIDSetting(double P, double I, double D, double F, double rampRate){
		this(P, I, D, F, rampRate, 0);
	}
	
	public PIDSetting(double P, double I, double D, double F, double rampRate, double iZone){
		this(P, I, D, F, rampRate, iZone, "No Name", false);
	}
	
	public PIDSetting(double P, double I, double D, double F, double rampRate, double iZone, String name, boolean onDash){
		super(P, I, D, F, new BlankPID(), new BlankPID());
		this.name = name;
		
		if(onDash){
			this.iZone = new Setting(name + "iZone", iZone);
			this.rampRate = new Setting(name + "Ramp", rampRate);
			SmartDashboard.putData(name, this);
		}else{
			this.iZone = new Setting(name + "iZone", iZone, false);
			this.rampRate = new Setting(name + "Ramp", rampRate, false);
		}
	}

	public void setiZone(double iZone){
		this.iZone.setValue(iZone);
	}
	
	public void setRampRate(double rampRate) {
		this.rampRate.setValue(rampRate);
	}

	public double getiZone() {
		return iZone.getValue();
	}

	public double getRampRate() {
		return rampRate.getValue();
	}
	
	public String getPIDName() {
		return name;
	}

	public void setPIDName(String name) {
		this.name = name;
	}

	public void set(double P, double I, double D, double F, double rampRate, double iZone){
		setPID(P, I, D, F);
		this.iZone.setValue(iZone);
		this.rampRate.setValue(rampRate);
	}
	
	
}
