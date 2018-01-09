package org.usfirst.frc.team3735.robot.assists;


import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.subsystems.Vision.Target;
import org.usfirst.frc.team3735.robot.util.cmds.ComAssist;

public class VisionAssist extends ComAssist{
	
	Target target;

	public VisionAssist(Target p){
		requires(Robot.vision);
		this.target = p;
	}

	@Override
	public void initialize() {
	}

	@Override
	public void execute() {
        Robot.drive.setVisionAssist(Robot.vision.getRelativeCX(target));
	}

	@Override
	public void end() {
        Robot.drive.setVisionAssist(0);
	}
	
	
}
