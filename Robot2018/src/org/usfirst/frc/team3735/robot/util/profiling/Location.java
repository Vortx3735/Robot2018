package org.usfirst.frc.team3735.robot.util.profiling;

import java.io.Serializable;
import java.util.ArrayList;

import org.usfirst.frc.team3735.robot.util.Filer;
import org.usfirst.frc.team3735.robot.util.bases.VortxIterative.Side;

public class Location implements Serializable{
	public double x,y;
	private static ArrayList<Location> staticLocations = new ArrayList<Location>();
	private static boolean onLeftSide = true;
	
	public Location(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Location(double x, double y, boolean flag) {
		this(x,y);
		if(flag)staticLocations.add(this);
	}
	
	public Location appendYawDistance(double yaw, double distance) {
		double ang = Math.toRadians(-yaw);
		return new Location(x + Math.cos(ang), y + Math.sin(ang));
	}
	
	public Location appendXY(double addX, double addY) {
		return new Location(x + addX, y + addY);
	}
	
	public double distanceFrom(Location other) {
		return Math.hypot(this.x - other.x, this.y - other.y);
	}
	
	public double yawTo(Location other) {
		return Math.toDegrees(Math.atan2(other.x - x, other.y - y));
	}
	
	public static void changeSide(Side side, double fieldLength) {
		if((side == Side.Left) != onLeftSide) {
			for(Location loc : staticLocations) {
				loc.x = fieldLength - loc.x;
			}
			onLeftSide = !onLeftSide;
		}
	}
	
	public String toString() {
		return Filer.make("Xloc", x, 2) + Filer.make("Yloc", y, 2);
	}
	
	public static Location fromString(String s) {
		return new Location(
			Filer.getDouble("Xloc", s),
			Filer.getDouble("Yloc", s)
		);
		
	}
	
}
