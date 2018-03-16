package org.usfirst.frc.team3735.robot.commands.sequences;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.assists.NavxAssist;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageLower;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageRaise;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageSetRoller;
import org.usfirst.frc.team3735.robot.commands.cubeintake.CubeSetRoller;
import org.usfirst.frc.team3735.robot.commands.drive.TurnTo;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.DriveRaw;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.MoveDDx;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosDDx;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosPID;
import org.usfirst.frc.team3735.robot.settings.Dms;
import org.usfirst.frc.team3735.robot.settings.Waypoints;
import org.usfirst.frc.team3735.robot.subsystems.Elevator;
import org.usfirst.frc.team3735.robot.triggers.Bumped;
import org.usfirst.frc.team3735.robot.triggers.CarriageOverload;
import org.usfirst.frc.team3735.robot.triggers.HasMoved;
import org.usfirst.frc.team3735.robot.util.choosers.Side;
import org.usfirst.frc.team3735.robot.util.cmds.VortxCommand;
import org.usfirst.frc.team3735.robot.util.profiling.Location;
import org.usfirst.frc.team3735.robot.util.settings.Func;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GrabWallCube extends CommandGroup {

    public GrabWallCube(boolean right) {
    	Location target = (right) ? Waypoints.Pieces.switchRight : Waypoints.Pieces.switchLeft;
    	
    	addSequential(new TurnTo(target));
    	addParallel(VortxCommand.asSequence(
			new ElevatorSetPosDDx(Func.getFunc(0), Func.getFunc(.7), Func.getFunc(.03)),
			new ElevatorSetPosPID(Elevator.bottom)
		));
    	addSequential(new CarriageLower());

    	addSequential(new DriveRaw(.4, 0).addT(new Bumped(.7)).addA(new NavxAssist(target, false)));
    	
    	addSequential(VortxCommand.asParallel(
    			new CubeSetRoller(-.9),
    			new CarriageSetRoller(-.4).addT(new CarriageOverload(20))
    			));
    	addParallel(new CarriageSetRoller(-.8), .4);
    	addSequential(new DriveRaw(-.4, 0), .4);
    	
    }
    
    

}
