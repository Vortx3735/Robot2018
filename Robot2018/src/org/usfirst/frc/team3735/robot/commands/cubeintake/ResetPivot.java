package org.usfirst.frc.team3735.robot.commands.cubeintake;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.cmds.VortxCommand;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ResetPivot extends VortxCommand {

    public ResetPivot() {
        requires(Robot.angler);
    }


	@Override
	protected void initialize() {
		super.initialize();
		Robot.angler.controller.disable();
	}


	@Override
	protected void execute() {
		Robot.angler.setPOutput(-.4);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		Robot.angler.setVal(0);
	}



	@Override
	protected void interrupted() {
		super.interrupted();
		end();
	}




}
