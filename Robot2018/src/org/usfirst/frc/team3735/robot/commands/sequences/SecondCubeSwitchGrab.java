package org.usfirst.frc.team3735.robot.commands.sequences;

import org.usfirst.frc.team3735.robot.commands.cubeintake.CubeSetRoller;
import org.usfirst.frc.team3735.robot.commands.drive.TurnTo;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.DriveExp;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.Move;
import org.usfirst.frc.team3735.robot.settings.Waypoints;
import org.usfirst.frc.team3735.robot.triggers.HasPassedWaypoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SecondCubeSwitchGrab extends CommandGroup {

    public SecondCubeSwitchGrab() {
    	addSequential(new Move(70));
    	addSequential(new TurnTo(Waypoints.Pieces.headCube));
    	addParallel(new DriveExp(.5, 0).addT(new HasPassedWaypoint(Waypoints.Pieces.headCube)));
    	addSequential(new CubeSetRoller().addT(new HasPassedWaypoint(Waypoints.Pieces.headCube)));
    	addSequential(new Move(-50));
    	
    	
    	
    }
}
