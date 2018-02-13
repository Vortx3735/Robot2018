package org.usfirst.frc.team3735.robot.settings;

import org.usfirst.frc.team3735.robot.util.profiling.Location;

public class Waypoints {
	public static final Location center = new Location(Dms.Field.HALFLENGTH, 0);
	public static final Location topRight = new Location(Dms.Field.LENGTH, Dms.Field.HALFWIDTH);
	public static final Location topLeft = new Location(0, Dms.Field.HALFWIDTH);
	public static final Location bottomRight = new Location(Dms.Field.LENGTH, -Dms.Field.HALFWIDTH);
	public static final Location bottomLeft = new Location(0, -Dms.Field.HALFWIDTH);

	
	public static String rightScaleRight = "RightScaleRight";
	public static String rightScaleLeft = "RightScaleRight";
	public static String middleSwitchRight = "RightScaleRight";
	public static String middleSwitchLeft = "RightScaleRight";
	public static String leftScaleRight = "RightScaleRight";
	public static String rScaleRight = "RightScaleRight";

	//public static String rightScaleRight = "RightScaleRight";

}
