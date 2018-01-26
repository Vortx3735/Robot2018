package org.usfirst.frc.team3735.robot.commands.recorder;

import java.io.File;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.DriveState;
import org.usfirst.frc.team3735.robot.util.profiling.Line;
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
	
    // Called repeatedly when this Command is scheduled to run
    double forwardLook = 0; //	degrees/180
    double angleLook = 0;	// 	degrees/180
    double angleError = 0;	//	degrees/180
    double distError = 0;	//	inches
    DriveState curState;
    Line toFollow;
	
	private static Setting lookCo = new Setting("Forward Look Co", 1);
//	private static Setting indexLookCo = new Setting("Index Look Co", 1);
	private static Setting angleErrorCo = new Setting("Angle Error Co", 1);
	private static Setting distErrorCo = new Setting("Dist Error Co", 1);

	
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

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(needsLoading) {
    		loadFile(fileName.getValue());
    	}
    }
    
    public void loadFile(String name) {
    	arr = new ArrayList<>();
		filePath = "/home/lvuser/"  + name + ".txt";
		try{
			sc = new Scanner(new File(filePath));
		}catch(Exception e){
			e.printStackTrace();
		}
    	while(sc.hasNextLine()) {
    		arr.add(DriveState.fromString(sc.nextLine().trim()));
    	}
    }


    
    protected void execute() {
    	//update to closest position data point
		while(Robot.navigation.getPosition().distanceFrom(arr.get(index).pos) > 
			  Robot.navigation.getPosition().distanceFrom(arr.get(limitIndex(index+1)).pos)){
			index++;
		}
    	toFollow = new Line(arr.get(index).pos, arr.get(index).pos);
		curState = arr.get(index);
		
		Robot.navigation.getController().setSetpoint(curState.pos.yaw);
		
		//compute lookahead error
		if(limitIndex(index+lookAmount) != index+lookAmount) {
			forwardLook = Robot.navigation.getLine().smallAngleWith(new Line(Robot.navigation.getPosition(), arr.get(limitIndex(index+lookAmount)).pos)) / 180.0;
		}else {
			forwardLook = 0;
		}
		
		//compute lookahead by indexing the angle of i+lookahead?
		
		//computer angle error
		angleError = Robot.navigation.getLine().smallAngleWith(curState.pos.yaw) * Math.signum(Robot.navigation.getController().getError());
		
		//compute distance from profile error
		distError = toFollow.distanceFrom(Robot.navigation.getPosition()) * Math.signum(Robot.navigation.getController().getError());
		
		double turn = curState.getTurn() + angleError * angleErrorCo.getValue() + forwardLook * lookCo.getValue() + distError * distErrorCo.getValue();
		Robot.drive.normalDrive(curState.getMove(), turn);
    }
    
    public int limitIndex(int i) {
    	return (i < arr.size()) ? i : arr.size() - 1;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return index >= arr.size() - 1;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
