package org.usfirst.frc.team3735.robot.assists;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.cmds.ComAssist;
import org.usfirst.frc.team3735.robot.util.settings.Func;

public class NavxAssist extends ComAssist{
	
	Func angle;
	
	public NavxAssist(Func yaw){
		angle = yaw;
		requires(Robot.navigation);
	}
	
	public NavxAssist(){
		requires(Robot.navigation);
	}
	
	@Override
	public void initialize() {
		if(angle == null){
			Robot.navigation.getController().setSetpoint(Robot.navigation.getYaw());
		}else{
			Robot.navigation.getController().setSetpoint(angle.getValue());
		}
	}

	@Override
	public void execute() {
		Robot.drive.setNavxAssist(Robot.navigation.getController().getError());
	}

	@Override
	public void end() {
		Robot.drive.setNavxAssist(0);
	}
	
}
