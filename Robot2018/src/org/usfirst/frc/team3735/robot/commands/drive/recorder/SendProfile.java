package org.usfirst.frc.team3735.robot.commands.drive.recorder;

import java.io.File;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.DriveState;
import org.usfirst.frc.team3735.robot.util.calc.VortxMath;
import org.usfirst.frc.team3735.robot.util.profiling.Line;
import org.usfirst.frc.team3735.robot.util.profiling.Ray;
import org.usfirst.frc.team3735.robot.util.settings.Setting;
import org.usfirst.frc.team3735.robot.util.settings.StringSetting;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SendProfile extends Command {
	
	public static StringSetting fileName = new StringSetting("Sending Profile File", "defaultfile");
	private String filePath;
	private Scanner sc;
	private ArrayList<DriveState> array;
	boolean needsLoading = false;
	
	private static int lookAmount = 25; //.5 seconds
	
	int index;
	
    double forwardLook = 0; //	degrees/180
    double angleLook = 0;	// 	degrees/180
    double angleError = 0;	//	degrees/180
    double distError = 0;	//	inches
    DriveState curState;
    Ray toFollow;
	
	private static Setting forwardLookCo = new Setting("Forward Look Co", .8);
//	private static Setting angleLookCo = new Setting("Angle Look Co", 0);
	private static Setting angleErrorCo = new Setting("Angle Error Co", 3);
//	private static Setting distErrorCo = new Setting("Dist Error Co", 0);
	private static Setting halfWay = new Setting("Half Way Co", 20);

	
    public SendProfile(String file) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	if(file != null) {
    		loadFile(file);
    	}else {
    		needsLoading = true;
    	}
    	requires(Robot.drive);
    	requires(Robot.navigation);
    }
    
    public SendProfile() {
    	needsLoading = true;
    	requires(Robot.drive);
    	requires(Robot.navigation);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	if(needsLoading) {
//    		loadFile(fileName.getValue());
//    	}
    	index = 0;
    	
    	//bring to closest point

    	
//		while(Robot.navigation.getPosition().distanceFrom(arr.get(index).pos) > 
//			Robot.navigation.getPosition().distanceFrom(arr.get(limitIndex(index+1)).pos)){
//			index++;
//		}
    }
    

    
    public void loadFile(String name) {
    	
    	Thread n = new Thread() {
    		@Override
    		public void run() {
    	    	array = new ArrayList<>();
    			filePath = "/home/lvuser/"  + name + ".txt";
    			//filePath = "C:\\Users\\Andrew\\Desktop\\"  + name + ".txt";

    			try{
    				sc = new Scanner(new File(filePath));
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    	    	while(sc.hasNextLine()) {
    	    		array.add(DriveState.fromString(sc.nextLine()));
    	    	}
    		}
    	};
    	n.start();

    	

    }


    
    protected void execute() {
		ArrayList<DriveState> arr = getArray();
		curState = getArray().get(limitIndex(arr, index));
		//draw line between current point and previous point
		if(index != 0) {
	    	toFollow = new Ray(curState.pos, arr.get(index - 1).pos);
		}else {
			toFollow = new Ray(arr.get(index + 1).pos, curState.pos);
		}
		//set controller to the correct angle
		Robot.navigation.getController().setSetpoint(curState.pos.yaw);
		
		
		
		//if forward and angle look is available, calculate it
		if(limitIndex(arr, index + lookAmount) == (index + lookAmount)){
			//compute lookahead error
			Ray r = new Ray(Robot.navigation.getPosition(), arr.get(limitIndex(arr, index + lookAmount)).pos);
			forwardLook = Robot.navigation.getRay().angleTo(r)/ 180.0;
			//System.out.println("Robot Angle: " + Robot.navigation.getRay().yaw + "Target Ray Angle: " + r.yaw);
			//compute lookahead by indexing the angle of i+lookahead?
			//angleLook = Robot.navigation.getRay().angleTo(arr.get(index + lookAmount).pos) / 180.0;
			if(Robot.navigation.getPosition().distanceFrom(arr.get(limitIndex(arr, index + lookAmount)).pos) < 10) {
				forwardLook = 0;
			}
				
		}else {
			forwardLook = 0;
			angleLook = 0;
		}
		System.out.println("Forward Look: " + forwardLook);

		//computer angle error
		angleError = VortxMath.navLimit(Robot.navigation.getController().getError()) / 180.0;
		//System.out.println("Angle Error: " + angleError);
		//compute distance from profile error
		distError = toFollow.distanceFrom(Robot.navigation.getPosition());
		
		
		
//		double turn = curState.getTurn() + angleError * angleErrorCo.getValue() + forwardLook * lookCo.getValue() + distError * distErrorCo.getValue() + angleLook * angleLookCo.getValue();
		
		double p = Math.abs(VortxMath.squish(distError, halfWay.getValue()));
		//System.out.println("P: " + p);

		double turn =  p * forwardLook * forwardLookCo.getValue() +  (1-p) * angleError * angleErrorCo.getValue();
//		if(Double.isNaN(turn)) {
//			System.out.print("forward look" + forwardLook + "\t\t\t");
//			System.out.println("lookco" + forwardLookCo.getValue() + "\t\t");
//			
//		}
		
		Robot.drive.normalDrive(curState.getMove(), turn);
//		System.out.println("Move: " + curState.getMove() + "\t\t\t Turn: " + turn);
//		System.out.print("forward look" + forwardLook + "\t\t\t");
//		System.out.println("lookco" + forwardLookCo.getValue() + "\t\t");
    	index++;
    }
    
    public int limitIndex(ArrayList<DriveState> a, int i) {
    	return (i < a.size()) ? i : a.size() - 1;
    }
    
    public synchronized ArrayList<DriveState> getArray(){
    	return array;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return index >= getArray().size();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
