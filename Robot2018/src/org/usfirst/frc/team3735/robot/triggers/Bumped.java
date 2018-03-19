package org.usfirst.frc.team3735.robot.triggers;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.cmds.ComTrigger;
import org.usfirst.frc.team3735.robot.util.settings.Func;

public class Bumped extends ComTrigger{
	
	private Func acc;

	public Bumped(Func acc){
		this.acc = acc;
	}
	
	public Bumped(double acc){
		this(Func.getFunc(acc));
	}
	
	public Bumped(){
		this(1);
	}

	@Override
	public boolean get() {
		return Math.abs(Robot.navigation.getXYAcceleration()) > acc.getValue();
	}

	@Override
	public String getHaltMessage() {
		return "bumped " + acc + " acc";
	}
	
	
	
	
}
