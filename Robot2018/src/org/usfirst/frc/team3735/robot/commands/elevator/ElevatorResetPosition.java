package org.usfirst.frc.team3735.robot.commands.elevator;

import org.usfirst.frc.team3735.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ElevatorResetPosition extends InstantCommand {

	public ElevatorResetPosition() {
		
	}
	
	public void initialize() {
		Robot.elevator.resetEncoderPositions();
	}
}
