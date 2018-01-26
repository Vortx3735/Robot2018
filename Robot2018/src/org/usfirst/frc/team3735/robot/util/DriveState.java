package org.usfirst.frc.team3735.robot.util;

import org.usfirst.frc.team3735.robot.util.profiling.Position;

public class DriveState {

	public Position pos;
	double left;
	double right;
	
	public DriveState(Position p, double l, double r) {
		this.pos = p;
		this.left = l;
		this.right = r;
	}
	
	public String toString() {
		return "Left:" + left + "# Right:" + right + "#" + pos.toString();
	}
	
	public static DriveState fromString(String s) {
		return new DriveState(
				Position.fromString(s),
				Double.parseDouble(s.substring(s.indexOf("Left:") + 5).substring(0, s.indexOf("#"))),
				Double.parseDouble(s.substring(s.indexOf("Right:") + 6).substring(0, s.indexOf("#")))
		);
	}
	
	public void rotate(double d) {
		left += d;
		right -=d;
	}
	
	public double getMove() {
		return .5 * (left + right);
	}
	
	public double getTurn() {
		return (left - right)/2.0;
	}
	
}
