package org.usfirst.frc.team3735.robot.util.settings;

public class PIDSetting {
	double P;
	double I;
	double D;
	double F;
	double rampRate;

	public PIDSetting(double P, double I, double D){
		this(P, I, D, 0, 0);
	}
	
	public PIDSetting(double P, double I, double D, double F){
		this(P, I, D, F, 0);
	}
	
	public PIDSetting(double P, double I, double D, double F, double rampRate){
		this.P = P;
		this.I = I;
		this.D = D;
		this.F = F;
		this.rampRate = rampRate;
	}

	public double getP() {
		return P;
	}

	public double getI() {
		return I;
	}

	public double getD() {
		return D;
	}

	public double getF() {
		return F;
	}

	public double getRampRate() {
		return rampRate;
	}

	public void setP(double p) {
		P = p;
	}

	public void setI(double i) {
		I = i;
	}

	public void setD(double d) {
		D = d;
	}

	public void setF(double f) {
		F = f;
	}

	public void setRampRate(double rampRate) {
		this.rampRate = rampRate;
	}
	
	
	
}
