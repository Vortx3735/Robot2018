package org.usfirst.frc.team3735.robot.subsystems;

import org.usfirst.frc.team3735.robot.util.settings.Setting;
import org.usfirst.frc.team3735.robot.util.vision.VisionHandler;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Vision extends Subsystem {


	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;
	
	public static Setting dpp = new Setting("Vision Degrees per Pixel", 0.13125);
	
	
	private UsbCamera camera1;
	private UsbCamera camera2;
	
	public enum Targets{
		
	}
	public Vision(){
//		camera1 = CameraServer.getInstance().startAutomaticCapture(0);
//		camera2 = CameraServer.getInstance().startAutomaticCapture(1);
//		
//		camera1.setFPS(16);
//		//camera2.setFPS(16);
//		
//	    camera1.setResolution(IMG_WIDTH, IMG_HEIGHT);
//	    camera2.setResolution(IMG_WIDTH, IMG_HEIGHT);
	    

	    
	    
	    
	}


    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void log(){

		
    }

    public Double getRelativeCX(Targets tar){
//    	if(mainHandler.target.equals(VisionHandler.nullTarget)){
//    		return nullValue;
//    	}else{
//        	return mainHandler.getCenterX() - IMG_WIDTH/2;
//    	}
    	return 0.0;
    }
    public Double getWidth(Targets tar){
//    	return mainHandler.getWidth();
    	return 0.0;
    }


	

	


	public void debugLog() {

	}
    
    


    
    
}


