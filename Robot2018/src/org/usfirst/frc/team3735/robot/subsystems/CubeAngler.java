package org.usfirst.frc.team3735.robot.subsystems;

import org.usfirst.frc.team3735.robot.commands.CubeIntakeJoystickMove;
import org.usfirst.frc.team3735.robot.settings.RobotMap;
import org.usfirst.frc.team3735.robot.util.PIDCtrl;
import org.usfirst.frc.team3735.robot.util.hardware.VortxTalon;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CubeAngler extends Subsystem implements PIDSource, PIDOutput{

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	VortxTalon angler;
	PIDCtrl controller;
	double consForce = 1;
	
	AnalogPotentiometer p;
	double offset = -2.3;
		
	public CubeAngler() {
		angler = new VortxTalon(RobotMap.CubeIntake.anglerMotor, "Angler");
		angler.setNeutralMode(NeutralMode.Brake);
		p = new AnalogPotentiometer(3,360,offset);
		controller = new PIDCtrl(1,0,0,0,this,this);
		
	}

	public void setVal(double val) {
		offset = -p.get() + val;
		p = new AnalogPotentiometer(3,360);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CubeIntakeJoystickMove());
    	
    	
    }

	public void setPOutput(double anglerMove) {
		angler.set(anglerMove);
		
	}

	@Override
	public void pidWrite(double val) {
		angler.set(getConsPower() + val);
	}
	
	public double getConsPower() {
		return consForce * Math.cos(getPosition());
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return angler.getPosition();
	}
	
	public double getPosition() {
		return -(p.get() + offset);
	}
	public void log() {
		SmartDashboard.putNumber("Cube Angler Pos", p.get());
		SmartDashboard.putNumber("Cube Angler Angle", getPosition());

	}

	@Override
	public void setPIDSourceType(PIDSourceType arg0) {
		
	}

}

