package org.usfirst.frc.team3735.robot.commands.auto.cows;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageRaise;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageSetRoller;
import org.usfirst.frc.team3735.robot.commands.drive.recorder.SendProfile;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosRaw;
import org.usfirst.frc.team3735.robot.commands.sequences.AutoScaleLineup;
import org.usfirst.frc.team3735.robot.settings.Waypoints;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MidScaleRight extends CommandGroup {

    public MidScaleRight(boolean complex) {
    	addSequential(new SendProfile(Waypoints.Auto.midScaleRight));	//drive up to scale, FAST
    	addSequential(new AutoScaleLineup(false));
    	
//    	addSequential(new AutoScaleLineup());
    }
}
