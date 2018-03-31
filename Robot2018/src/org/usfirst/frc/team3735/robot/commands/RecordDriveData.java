package org.usfirst.frc.team3735.robot.commands;

import java.util.Formatter;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.recording.DriveState;
import org.usfirst.frc.team3735.robot.util.settings.StringSetting;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RecordDriveData extends Command {

	public static String  fileName = "DriveData";
	private static String filePath;
	private static Formatter formatter;
	private boolean error;
	
    public RecordDriveData() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.setRunWhenDisabled(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		filePath = "/home/lvuser/"  + fileName + ".csv";
		try{
			formatter = new Formatter(filePath);
			formatter.format("%s,%s,%s,%s", "LeftPOutput", "RightPOutput", "LeftV", "RightV");
			formatter.format("%s", System.getProperty("line.separator"));

		}catch(Exception e){
			System.out.println("Could not make file " + fileName);
//			e.printStackTrace();
			error = true;
		}
		
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//unity, unity, dps
		formatter.format("%.4f,%.4f,%.4f,%.4f", Robot.drive.getLeftPercent(), Robot.drive.getRightPercent(), Robot.drive.getLeftSpeed(), Robot.drive.getRightSpeed());
		formatter.format("%s", System.getProperty("line.separator"));
	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return error;
    }

    // Called once after isFinished returns true
    protected void end() {
		formatter.flush();
		formatter.close();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
