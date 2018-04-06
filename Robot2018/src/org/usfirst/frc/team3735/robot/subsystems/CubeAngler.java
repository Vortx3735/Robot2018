package org.usfirst.frc.team3735.robot.subsystems;

import org.usfirst.frc.team3735.robot.commands.CubeIntakeJoystickMove;
import org.usfirst.frc.team3735.robot.settings.RobotMap;
import org.usfirst.frc.team3735.robot.util.PIDCtrl;
import org.usfirst.frc.team3735.robot.util.calc.VortxMath;
import org.usfirst.frc.team3735.robot.util.hardware.VortxTalon;
import org.usfirst.frc.team3735.robot.util.settings.Setting;

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
	public PIDCtrl controller;
	
	private Setting cons = new Setting("Angler Cons Power", .12);
	
	AnalogPotentiometer p;
	double offset = 0;
		
	public CubeAngler() {
		angler = new VortxTalon(RobotMap.CubeIntake.anglerMotor, "Angler");
		angler.setNeutralMode(NeutralMode.Brake);
		
		p = new AnalogPotentiometer(1,-360 * 10);
		controller = new PIDCtrl(.01,.001,0,0,this,this);
		controller.setAbsoluteTolerance(3);
		SmartDashboard.putData("Cube Angler PID", controller);
		setVal(183);
		
	}

	public void setVal(double val) {
		offset = val - p.get();
		
	}
	
	public double getPosition() {
		return (p.get() + offset);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CubeIntakeJoystickMove());
    	
    	
    }

	public void setPOutput(double anglerMove) {
		if(getPosition() < 90) {
			anglerMove += Math.cos(Math.toRadians(getPosition())) * cons.getValue();
		}
		angler.set(anglerMove);
		
	}

	@Override
	public void pidWrite(double val) {
		setPOutput(VortxMath.limit(val, -.5, .5));
	}
	
	public double getConsPower() {
		return cons.getValue() * Math.cos(Math.toRadians(getPosition()));
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return getPosition();
	}
	

	public void log() {
		SmartDashboard.putNumber("Cube Angler Pos", p.get());
		SmartDashboard.putNumber("Cube Angler Angle", getPosition());
		

	}

	@Override
	public void setPIDSourceType(PIDSourceType arg0) {
		
	}

}

