package org.usfirst.frc.team3735.robot.util.profiling;

public class Position extends Location {
	public double yaw;
	
	public Position(double x, double y, double yaw){
		super(x,y);
		this.yaw = yaw;
	}
	
	public Position(Location loc, double yaw) {
		super(loc.x, loc.y);
		this.yaw = yaw;
	}
	
	public String toString() {
		return "Yaw:" + yaw + "#" + super.toString();
	}
	
	public static Position fromString(String s) {
		 return new Position(
				 Location.fromString(s), 
				 Double.parseDouble(s.substring(s.indexOf("Yaw:") + 4).substring(0, s.indexOf("#")))
		 );
	}
	
	
}
