package org.usfirst.frc.team3735.robot.commands.drive.positions;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.settings.Waypoints;
import org.usfirst.frc.team3735.robot.util.profiling.Location;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class BeginAtPathX extends InstantCommand {
	Location[] locs;
	private double offset;
	
    public BeginAtPathX(Location[] locs) {
    	this(locs,0);
    }
    
    public BeginAtPathX(Location[] locs, double offset) {
    	this.locs = locs;
    	this.offset = offset;
    }

    // Called once when the command executes
    protected void initialize() {
		Robot.navigation.getPosition().x = locs[0].x + offset;
    }

}
