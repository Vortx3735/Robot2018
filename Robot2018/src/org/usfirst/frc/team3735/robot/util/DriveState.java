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
		return Filer.make("Left", left, 5) + Filer.make("Right", right, 5) + pos.toString();
	}
	
	public static DriveState fromString(String s) {
		return new DriveState(
				Position.fromString(s),
				Filer.getDouble("Left", s),
				Filer.getDouble("Right", s)
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
