package org.usfirst.frc.team3735.robot.commands.cubeintake;

import org.usfirst.frc.team3735.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ResetPivot extends CommandGroup {

    public ResetPivot() {
        addSequential(new PivotSet(-.5),.2);
        addSequential(new InstantCommand() {
        	@Override
        	public void initialize() {
        		Robot.angler.setVal(0);
        	}
        });
    }
}
