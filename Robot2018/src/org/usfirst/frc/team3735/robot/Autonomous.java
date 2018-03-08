package org.usfirst.frc.team3735.robot;

import org.usfirst.frc.team3735.robot.commands.auto.LeftScaleLeft;
import org.usfirst.frc.team3735.robot.commands.auto.LeftScaleRight;
import org.usfirst.frc.team3735.robot.commands.auto.LeftSwitchLeft;
import org.usfirst.frc.team3735.robot.commands.auto.MidSwitchLeft;
import org.usfirst.frc.team3735.robot.commands.auto.MidSwitchRight;
import org.usfirst.frc.team3735.robot.commands.auto.RightScaleLeft;
import org.usfirst.frc.team3735.robot.commands.auto.RightScaleRight;
import org.usfirst.frc.team3735.robot.commands.auto.UnknownStraight;
import org.usfirst.frc.team3735.robot.util.choosers.DoNothing;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous {
	
	private SendableChooser<StartingState> posChooser;
	private SendableChooser<Priority> priority;
//	private SendableChooser
	
	private Command autoCommand = new DoNothing();
	
	public Autonomous() {
		posChooser = new SendableChooser<>();
			posChooser.addDefault("Unknown", StartingState.UNKNOWN);
			posChooser.addObject("Left", StartingState.LEFT);
			posChooser.addObject("Middle", StartingState.MID);
			posChooser.addObject("Right", StartingState.RIGHT);
		SmartDashboard.putData("Starting Pos", posChooser);

		
		priority = new SendableChooser<>();
			priority.addDefault("Cross Line", Priority.LINE);
			priority.addObject("Switch", Priority.SWITCH);
			priority.addObject("Scale", Priority.SCALE);
			priority.addObject("Scale if Easy", Priority.SCALEIFEASY);
		SmartDashboard.putData("Priority", priority);
		
		
	}
	
	public void chooseAutonomous() {
		cancel();
		String s = DriverStation.getInstance().getGameSpecificMessage().toLowerCase().trim().substring(0,2);
		
		System.out.println("Specific Message interpreted: " + s);
		
		if(priority.getSelected() == null || priority.getSelected() == Priority.LINE) {
			autoCommand = new UnknownStraight();
			return;
		}
		
		switch(posChooser.getSelected()) {
		
		case LEFT:
			switch(s) {
			case "lr"://Switch
				autoCommand = (priority.getSelected() == Priority.SCALE) ? new LeftScaleRight() : new LeftSwitchLeft(); //priority choose
				break;
			case "rl"://Scale
				autoCommand = new LeftScaleLeft();
				break;
			case "ll"://Scale and Switch
				autoCommand = (priority.getSelected() == Priority.SCALE || priority.getSelected() == Priority.SCALEIFEASY) ? new LeftScaleLeft() : new LeftSwitchLeft(); //priority choose
				break;
			case "rr":
				autoCommand = new LeftScaleRight();
				break;
			}
			break;
		case MID:
			switch(s) {
			case "lr":
				autoCommand = new MidSwitchLeft();
				break;
			case "rl":
				autoCommand = new MidSwitchRight();
				break;
			case "ll":
				autoCommand = new MidSwitchLeft();
				break;
			case "rr":
				autoCommand = new MidSwitchRight();
				break;
			}
			break;
		case RIGHT:
			switch(s) {
			case "lr"://Scale
				autoCommand = new RightScaleRight();
				break;
			case "rl"://Switch
				autoCommand = new RightScaleLeft();//priority choose
				break;
			case "ll":
				autoCommand = new RightScaleLeft();
				break;
			case "rr"://Scale and Switch
				autoCommand = new RightScaleRight();//priority choose
				break;
			}
			break;
		case UNKNOWN:
			
			break;
			
		default:
			
			
		}
	}
	
	public void start() {
		if(autoCommand != null) {
			autoCommand.start();
		}
	}
	
	public void cancel() {
		if(autoCommand != null) {
			autoCommand.start();
		}
	}
	
	public enum Priority{
		SWITCH,
		SCALE,
		SCALEIFEASY,
		LINE
	}
	
	
	public static enum StartingState{
		LEFT,
		MID,
		RIGHT,
		UNKNOWN
	}
	
}
