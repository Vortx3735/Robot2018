package org.usfirst.frc.team3735.robot.assists;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.subsystems.Vision.Target;
import org.usfirst.frc.team3735.robot.util.calc.VortxMath;
import org.usfirst.frc.team3735.robot.util.cmds.ComAssist;

public class NavxVisionAssist extends ComAssist{

    private Target target;


	public NavxVisionAssist(Target p){
		this.target = p;
    	requires(Robot.vision);
    	requires(Robot.navigation);
	}
	
	@Override
	public void initialize() {
		Robot.navigation.getController().setSetpoint(
				VortxMath.navLimit(
						Robot.navigation.getYaw() + Robot.vision.getRelativeCXAngle(target)
				)
		);
	}

	@Override
	public void execute() {
    	Robot.drive.setNavxAssist((Robot.navigation.getController().getError()));
	}
	
	@Override
	public void end(){
		Robot.drive.setNavxAssist(0);
	}
	
//doy mun fuh = ???
//sing chow em = hello
//em depp
	
}
