package org.usfirst.frc.team3735.robot.commands.recorder;

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
	private ArrayList<DriveState> arr;
	boolean needsLoading = false;
	
	private static int lookAmount = 3;
	
	int index;
	
    double forwardLook = 0; //	degrees/180
    double angleLook = 0;	// 	degrees/180
    double angleError = 0;	//	degrees/180
    double distError = 0;	//	inches
    DriveState curState;
    Ray toFollow;
	
	private static Setting lookCo = new Setting("Forward Look Co", 0);
	private static Setting angleLookCo = new Setting("Angle Look Co", 0);
	private static Setting angleErrorCo = new Setting("Angle Error Co", 1);
	private static Setting distErrorCo = new Setting("Dist Error Co", 0);

	
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
    	if(needsLoading) {
    		loadFile(fileName.getValue());
    	}
    	index = 0;
    }
    

    
    public void loadFile(String name) {
    	arr = new ArrayList<>();
		filePath = "/home/lvuser/"  + name + ".txt";
		//filePath = "C:\\Users\\Andrew\\Desktop\\"  + name + ".txt";

		try{
			sc = new Scanner(new File(filePath));
		}catch(Exception e){
			e.printStackTrace();
		}
    	while(sc.hasNextLine()) {
    		arr.add(DriveState.fromString(sc.nextLine()));
    	}
    }


    
    protected void execute() {
    	//update to closest position data point
    	index++;
//		while(Robot.navigation.getPosition().distanceFrom(arr.get(index).pos) > 
//			  Robot.navigation.getPosition().distanceFrom(arr.get(limitIndex(index+1)).pos)){
//			index++;
//		}
		curState = arr.get(limitIndex(index));
//		if(index >= arr.size()) {
//			index--;
//		}
		if(index != 0) {
	    	toFollow = new Ray(curState.pos, arr.get(index - 1).pos);
		}else {
			toFollow = new Ray(arr.get(index + 1).pos, curState.pos);
		}
		
		Robot.navigation.getController().setSetpoint(curState.pos.yaw);
		
		if(limitIndex(index+lookAmount) != index+lookAmount) {
			//compute lookahead error
			forwardLook = Robot.navigation.getRay().angleTo(new Ray(Robot.navigation.getPosition(), arr.get(limitIndex(index + lookAmount)).pos))/ 180.0;
			//compute lookahead by indexing the angle of i+lookahead?
			angleLook = Robot.navigation.getRay().angleTo(arr.get(index + lookAmount).pos) / 180.0;
		}else {
			forwardLook = 0;
			angleLook = 0;
		}
		
		
		//computer angle error
		angleError = VortxMath.navLimit(Robot.navigation.getYaw() - arr.get(index).pos.yaw) / 180.0;
		System.out.println("Angle Error: " + angleError);
		//compute distance from profile error
		distError = toFollow.distanceFrom(Robot.navigation.getPosition());
		
		double turn = curState.getTurn() + angleError * angleErrorCo.getValue() + forwardLook * lookCo.getValue() + distError * distErrorCo.getValue() + angleLook * angleLookCo.getValue();
		Robot.drive.normalDrive(curState.getMove(), turn);
		System.out.println("Move: " + curState.getMove() + " Turn: " + turn);

		
    }
    
    public int limitIndex(int i) {
    	return (i < arr.size()) ? i : arr.size() - 1;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return index >= arr.size() - 4;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
