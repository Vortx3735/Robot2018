package org.usfirst.frc.team3735.robot.commands.cubeintake;

import org.usfirst.frc.team3735.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class CubeAnglerReset extends InstantCommand {

    public CubeAnglerReset() {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.setRunWhenDisabled(true);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.angler.setVal(0);
    }

}
