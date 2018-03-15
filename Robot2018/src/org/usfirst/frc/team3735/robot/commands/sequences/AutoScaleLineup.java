package org.usfirst.frc.team3735.robot.commands.sequences;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.assists.NavxAssist;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageLower;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageRaise;
import org.usfirst.frc.team3735.robot.commands.carriage.CarriageSetRoller;
import org.usfirst.frc.team3735.robot.commands.drive.TurnTo;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.DriveRaw;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.MoveDDx;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosDDx;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosPID;
import org.usfirst.frc.team3735.robot.settings.Dms;
import org.usfirst.frc.team3735.robot.settings.Waypoints;
import org.usfirst.frc.team3735.robot.subsystems.Elevator;
import org.usfirst.frc.team3735.robot.triggers.HasMoved;
import org.usfirst.frc.team3735.robot.util.choosers.Side;
import org.usfirst.frc.team3735.robot.util.cmds.VortxCommand;
import org.usfirst.frc.team3735.robot.util.profiling.Location;
import org.usfirst.frc.team3735.robot.util.settings.Func;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoScaleLineup extends CommandGroup {

	private static double dist = 0;
    public AutoScaleLineup(boolean right) {
    	Location target = (right) ? Waypoints.Pieces.scaleRight : Waypoints.Pieces.scaleLeft;
    	addSequential(new TurnTo(target));
    	addParallel(VortxCommand.asSequence(
			new ElevatorSetPosDDx(Func.getFunc(Elevator.top), Func.getFunc(1), Func.getFunc(.02)),
			new ElevatorSetPosPID(Elevator.top)
		));
    	addSequential(new DriveRaw(-.4, 0).addT(new HasMoved(new Func() {
    		@Override
    		public double getValue() {
    			return getDist();
    		}
    	})).addA(new NavxAssist(target, true)));
    	
    	
    	addSequential(new CarriageRaise());
    	addSequential(new CarriageSetRoller(-.9), 1);
    	
    	addSequential(new DriveRaw(.4, 0).addT(new HasMoved(new Func() {
    		@Override
    		public double getValue() {
    			return -dist;
    		}
    	})).addA(new NavxAssist(target, false)));
    	
    	addSequential(new CarriageLower());
		addSequential(new ElevatorSetPosDDx(Func.getFunc(0), Func.getFunc(.7), Func.getFunc(.03)));
		
    	
    	
    	
    }
    
    
    public double getDist() {
    	dist = -(Robot.navigation.getPosition().distanceFrom(Waypoints.Pieces.scaleLeft) - 20);
    	return dist;
    }
}
