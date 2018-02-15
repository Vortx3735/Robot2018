package org.usfirst.frc.team3735.robot.util.oi;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/*
 * A standard interface for interacting with a controller that is programmed as shown
 */
public class GraphiteJoystick extends Joystick{
	public Button mid, left, right, trig;
	public Button pov0,pov45,pov90,pov135,pov180,pov225,pov270,pov315;
	
	public GraphiteJoystick(int p){
		super(p);
//		a = new JoystickButton(this, 1);
//		b = new JoystickButton(this, 2);
//		x = new JoystickButton(this, 3);
//		y = new JoystickButton(this, 4);
//		lb = new JoystickButton(this, 5);
//		rb = new JoystickButton(this, 6);
//		back = new JoystickButton(this, 7);
//		start = new JoystickButton(this, 8);
//		ls = new JoystickButton(this, 9);
//		rs = new JoystickButton(this, 10);
//		lt = new JoystickTriggerButton(this, false, .3);
//		rt = new JoystickTriggerButton(this, true, .3);
		trig = new JoystickButton(this, 1);
		left = new JoystickButton(this, 2);
		mid = new JoystickButton(this, 3);
		right = new JoystickButton(this, 4);
		

		pov0 = new JoystickPOVButton(this, 0);
		pov45 = new JoystickPOVButton(this, 45);
		pov90 = new JoystickPOVButton(this, 90);
		pov135 = new JoystickPOVButton(this, 135);
		pov180 = new JoystickPOVButton(this, 180);
		pov225 = new JoystickPOVButton(this, 225);
		pov270 = new JoystickPOVButton(this, 270);
		pov315 = new JoystickPOVButton(this, 315);
	}

	public double getGYaw() {
		return Math.toDegrees(Math.atan2(getGX(), getGY()));
	}
	
	public double getGX() {
		return getRawAxis(0);
	}
	
	public double getGY() {
		return getRawAxis(1) * -1;
	}
	public double getGMagnitude() {
		return Math.hypot(getGX(), getGY());
	}
	
	@Override
	public double getThrottle() {
		return getRawAxis(2);
	}

	

	
	
	
}
