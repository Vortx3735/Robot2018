package org.usfirst.frc.team3735.robot.subsystems;

import org.usfirst.frc.team3735.robot.commands.CubeIntakeJoystickMove;
import org.usfirst.frc.team3735.robot.settings.RobotMap;
import org.usfirst.frc.team3735.robot.util.hardware.VortxTalon;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeAngler extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	VortxTalon angler;
	
	
	public CubeAngler() {
		angler = new VortxTalon(RobotMap.CubeIntake.anglerMotor, "Angler");
		angler.setNeutralMode(NeutralMode.Brake);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CubeIntakeJoystickMove());
    	
    	
    }

	public void setPOutput(double anglerMove) {
		angler.set(anglerMove);
		
	}
}

