package org.usfirst.frc.team3735.robot.settings;

import org.usfirst.frc.team3735.robot.util.profiling.Location;

public class Waypoints {
	
	//the corners of the field, and the center
	//all is in the perspective of alliance station, 
	//with 0,0 at the center of the station
	public static final Location center = new Location(0, Dms.Field.HALFLENGTH);
	public static final Location topRight = new Location(Dms.Field.HALFWIDTH, Dms.Field.LENGTH);
	public static final Location topLeft = new Location(-Dms.Field.HALFWIDTH, Dms.Field.LENGTH);
	public static final Location bottomRight = new Location(Dms.Field.HALFWIDTH, 0);
	public static final Location bottomLeft = new Location(-Dms.Field.HALFWIDTH, 0);

}
