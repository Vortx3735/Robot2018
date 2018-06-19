package org.usfirst.frc.team3735.robot.subsystems;

import org.usfirst.frc.team3735.robot.commands.cubeintake.CubeIntakeJoystickMove;
import org.usfirst.frc.team3735.robot.settings.Constants;
import org.usfirst.frc.team3735.robot.settings.RobotMap;
import org.usfirst.frc.team3735.robot.util.PIDCtrl;
import org.usfirst.frc.team3735.robot.util.calc.VortxMath;
import org.usfirst.frc.team3735.robot.util.hardware.VortxTalon;
import org.usfirst.frc.team3735.robot.util.settings.Setting;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
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
public class Pivot extends Subsystem implements PIDSource, PIDOutput{

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	VortxTalon pivot;
	public PIDCtrl controller;
	private static double startingVal = 183;
	
	private Setting cons = new Setting("Angler Cons Power", .12);
	public Setting ticksPerDegree = new Setting("Pivot Ticks Per Degree", 2.8444444);
	
	//AnalogPotentiometer p;
	double offset = 0;
		
	public Pivot() {
		pivot = new VortxTalon(RobotMap.CubeIntake.anglerMotor, "Angler");
		pivot.setNeutralMode(NeutralMode.Brake);
		
		//p = new AnalogPotentiometer(1,-360 * 10);
		controller = new PIDCtrl(.015,.001,0.01,0,this,this, 5);
		controller.setAbsoluteTolerance(2);
		SmartDashboard.putData("Cube Angler PID", controller);
		//resetInside();
		 
		pivot.setTicksPerInch(ticksPerDegree.getValue());//Will be ticksPerDegree
		pivot.initSensor(FeedbackDevice.QuadEncoder, false);
		resetEncoderPositions();
	}
	
//	public void resetInside() {
//		setVal(startingVal);
//	}
//
//	public void setVal(double val) {
//		offset = val - p.get();
//		
//	}
	
	public double getPosition() {
		return pivot.getPosition();
	}
	
	public void resetEncoderPositions() {
		pivot.resetPosition();
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CubeIntakeJoystickMove());
    	
    	
    }

	public void setPOutput(double anglerMove) {
//		if(getPosition() < 90) {
//			anglerMove += Math.cos(Math.toRadians(getPosition())) * cons.getValue();
//		}
		pivot.set(anglerMove);	
	}

	@Override
	public void pidWrite(double val) {
		setPOutput(VortxMath.limit(val, -.5, .5));
	}
	
//	public double getConsPower() {
//		return cons.getValue() * Math.cos(Math.toRadians(getPosition()));
//	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		return getPosition();
	}
	

	public void log() {
//		SmartDashboard.putNumber("Cube Angler Angle", getPosition());
		SmartDashboard.putNumber("Cube Angler Angle", getPosition());
//		SmartDashboard.putNumber("Pivot Percent ", value)
		pivot.debugLog();
		SmartDashboard.putNumber("Cube Angler Current", pivot.getOutputCurrent());
	}

	@Override
	public void setPIDSourceType(PIDSourceType arg0) {
		
	}

}

