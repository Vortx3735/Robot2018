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
		public static String leftScaleLeft2 = "leftScaleLeft2";
		public static String leftScaleLeft3 = "leftScaleLeft3";
		public static String leftScaleLeft4 = "leftScaleLeft4";
		public static String leftScaleLeft5 = "leftScaleLeft5";


		public static String leftScaleRight = "leftScaleRight";
		public static String leftScaleRight2 = "leftScaleRight2";
		public static String leftScaleRight3 = "leftScaleRight3";
		public static String leftScaleRight4 = "leftScaleRight4";
		public static String leftScaleRight5 = "leftScaleRight5";

		public static String leftSwitchLeft = "leftSwitchLeft";
		public static String leftSwitchLeft2 = "leftSwitchLeft2";
		public static String leftSwitchLeft3 = "leftSwitchLeft3";
		public static String leftSwitchLeft4 = "leftSwitchLeft4";
		public static String leftSwitchLeft5 = "leftSwitchLeft5";


		
		public static String middleSwitchRight = "middleSwitchRight";
		public static String middleSwitchRight2 = "middleSwitchRight2";
		public static String middleSwitchRight3 = "middleSwitchRight3";
		public static String middleSwitchRight4 = "middleSwitchRight4";
		public static String middleSwitchRight5 = "middleSwitchRight5";

		public static String middleSwitchLeft = "middleSwitchLeft";
		public static String middleSwitchLeft2 = "middleSwitchLeft2";
		public static String middleSwitchLeft3 = "middleSwitchLeft3";
		public static String middleSwitchLeft4 = "middleSwitchLeft4";
		public static String middleSwitchLeft5 = "middleSwitchLeft5";



		
		public static String rightScaleLeft = "rightScaleLeft";
		public static String rightScaleLeft2 = "rightScaleLeft2";
		public static String rightScaleLeft3 = "rightScaleLeft3";
		public static String rightScaleLeft4 = "rightScaleLeft4";
		public static String rightScaleLeft5 = "rightScaleLeft5";

		public static String rightScaleRight = "rightScaleRight";
		public static String rightScaleRight2 = "rightScaleRight2";
		public static String rightScaleRight3 = "rightScaleRight3";
		public static String rightScaleRight4 = "rightScaleRight4";
		public static String rightScaleRight5 = "rightScaleRight5";


		public static String rightSwitchRight = "rightSwitchRight";
		public static String rightSwitchRight2 = "rightSwitchRight2";
		public static String rightSwitchRight3 = "rightSwitchRight3";
		public static String rightSwitchRight4 = "rightSwitchRight4";
		public static String rightSwitchRight5 = "rightSwitchRight5";

	}

	//public static String rightScaleRight = "RightScaleRight";

	public static class Starting{
		public static final Position left = new Position(-Dms.Field.HALFWALLWIDTH, Dms.Bot.HALFLENGTH, 0);
		public static final Position mid = new Position(0, Dms.Bot.HALFLENGTH, 0);
		public static final Position right = new Position(Dms.Field.HALFWALLWIDTH, Dms.Bot.HALFLENGTH, 0);
		public static final Position unknown = mid;
	}

}
