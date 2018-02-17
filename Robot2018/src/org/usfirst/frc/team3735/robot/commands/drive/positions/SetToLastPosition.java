package org.usfirst.frc.team3735.robot.commands.drive.positions;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.DriveState;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SetToLastPosition extends InstantCommand {

	String file;
	String filePath;
	Scanner sc;
    public SetToLastPosition(String file) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.file = file;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		filePath = "/home/lvuser/"  + file + ".txt";
		//filePath = "C:\\Users\\Andrew\\Desktop\\"  + name + ".txt";

		try{
			sc = new Scanner(new File(filePath));
		}catch(Exception e){
			e.printStackTrace();
		}
		String line = "";
    	while(sc.hasNextLine()) {
    		line = sc.nextLine();
    	}
    	Robot.navigation.setPosition(DriveState.fromString(line).pos);
    }


}
