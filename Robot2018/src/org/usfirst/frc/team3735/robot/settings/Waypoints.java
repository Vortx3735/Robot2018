package org.usfirst.frc.team3735.robot.settings;

import org.usfirst.frc.team3735.robot.util.profiling.Location;
import org.usfirst.frc.team3735.robot.util.profiling.Position;

public class Waypoints {

	public static class Verticies{
		public static final Location center = new Location(Dms.Field.HALFLENGTH, 0);
		public static final Location topRight = new Location(Dms.Field.LENGTH, Dms.Field.HALFWIDTH);
		public static final Location topLeft = new Location(0, Dms.Field.HALFWIDTH);
		public static final Location bottomRight = new Location(Dms.Field.LENGTH, -Dms.Field.HALFWIDTH);
		public static final Location bottomLeft = new Location(0, -Dms.Field.HALFWIDTH);
	}

	public static class Auto{
		public static String leftScaleLeft = "leftScaleLeft";
		public static String leftScaleRight = "leftScaleRight";
		public static String leftSwitchLeft = "leftSwitchLeft";

		
		public static String middleSwitchRight = "middleSwitchRight";
		public static String middleSwitchLeft = "middleSwitchLeft";
		public static String middleScaleLeft = "middleScaleLeft";
		public static String middleScaleRight = "middleScaleRight";

		
		public static String rightScaleLeft = "rightScaleLeft";
		public static String rightScaleRight = "rightScaleRight";
		public static String rightSwitchRight = "rightSwitchRight";
		public static String rightScaleRight2 = "rightScaleRight2";
	}

	//public static String rightScaleRight = "RightScaleRight";

	public static class Starting{
		public static final Position left = new Position(-Dms.Field.HALFWALLWIDTH, Dms.Bot.HALFLENGTH, 0);
		public static final Position mid = new Position(0, Dms.Bot.HALFLENGTH, 0);
		public static final Position right = new Position(Dms.Field.HALFWALLWIDTH, Dms.Bot.HALFLENGTH, 0);
	}
}
