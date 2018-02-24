package org.usfirst.frc.team3735.robot.util.recording;

import java.text.DecimalFormat;

public class Filer {
	
	public static String make(String name, double d) {
		return name + ":" + d + "#";
	}
	
	public static String make(String name, double d, int decPlaces) {
		return name + ":" + String.format("%." + String.valueOf(decPlaces) + "f", d) + "#";

	}
	
	public static double getDouble(String name, String s) {
		String st = getValue(name, s);
		double d = Double.parseDouble(st);
		//System.out.println("Parsing " + st + " as "  + d);
		return d;
	}
	
	public static String getValue(String name, String s) {
		String cat = s.substring(s.indexOf(name));
		return cat.substring(cat.indexOf(":") + 1, cat.indexOf("#"));
	}
}
