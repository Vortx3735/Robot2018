package org.usfirst.frc.team3735.robot.commands.sequences;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.assists.NavxAssist;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageLower;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageRaiseTele;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageSetRoller;
import org.usfirst.frc.team3735.robot.commands.cubeintake.CubeAnglerSetPID;
import org.usfirst.frc.team3735.robot.commands.cubeintake.CubeSetRoller;
import org.usfirst.frc.team3735.robot.commands.drive.TurnTo;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.DriveExp;
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
public class AutoSwitchLineup extends CommandGroup {

    public AutoSwitchLineup(boolean right) {
    	Location target = (right) ? Waypoints.Pieces.switchRight : Waypoints.Pieces.switchLeft;
    	
//    	addSequential(new TurnTo(target, true),2);
    	addSequential(new CubeAnglerSetPID(0),1);
    	addParallel(VortxCommand.asSequence(
			new ElevatorSetPosDDx(Func.getFunc(Elevator.switchHeight), Func.getFunc(.7), Func.getFunc(.03)),
			new ElevatorSetPosPID(Elevator.switchHeight)
		), 2);
    	addSequential(new CarriageLower());

    	addSequential(new DriveExp(-.8, 0).addT(new Bumped(.8)).addA(new NavxAssist(target, true)));
    	
    	double hugtime = 1;
    	addParallel(new DriveRaw(-.2,0),hugtime);
		addSequential(new CarriageSetRoller(-.7), hugtime);
    	addSequential(new DriveRaw(.4, 0), .4);
    	
    }
    
    

}
