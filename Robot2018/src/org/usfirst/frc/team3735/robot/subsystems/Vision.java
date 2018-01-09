package org.usfirst.frc.team3735.robot.subsystems;


import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Vision extends Subsystem {


	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;
	
	
	private UsbCamera camera1;
	private UsbCamera camera2;
	
	public enum Target{
		Cubes,
		Tape,
		Exchange,
		Portal
	}
	
	
	public Vision(){
		camera1 = CameraServer.getInstance().startAutomaticCapture(0);
		camera2 = CameraServer.getInstance().startAutomaticCapture(1);
		
		camera1.setFPS(16);
		//camera2.setFPS(16);
		
	    camera1.setResolution(IMG_WIDTH, IMG_HEIGHT);
	    camera2.setResolution(IMG_WIDTH, IMG_HEIGHT);

	    
	    
	}


    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void log(){

		
    }
    
    
    //need to be made with the new vision targets, and with the new camera system
    public double getRelativeCX(Target t){
    	return 0;
    }
    public double getWidth(Target t){
    	return 0;
    }
    public double getRelativeCXAngle(Target t) {
    	return 0; //use degrees per pixel function to calculate
    }



	public void debugLog() {

	}
    
    


    
    
}


