package org.usfirst.frc.team3735.robot.assists;


import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.subsystems.Vision;
import org.usfirst.frc.team3735.robot.subsystems.Vision.Targets;
import org.usfirst.frc.team3735.robot.util.cmds.ComAssist;

public class VisionAssist extends ComAssist{
	
	Targets pipe;
	private double prevWorking = 0;

	public VisionAssist(Targets p){
		requires(Robot.vision);
		this.pipe = p;
	}

	@Override
	public void initialize() {
	}

	@Override
	public void execute() {
		Double in = Robot.vision.getRelativeCX(pipe);
    	if(in == null){
    		Robot.drive.setVisionAssist(0);
    	}else{
    		prevWorking = in;
        	Robot.drive.setVisionAssist(in * .0025);
    	}
	}
	
	
}
