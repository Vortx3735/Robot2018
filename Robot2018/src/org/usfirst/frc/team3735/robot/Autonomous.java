package org.usfirst.frc.team3735.robot;

import org.usfirst.frc.team3735.robot.commands.auto.*;

import org.usfirst.frc.team3735.robot.util.choosers.DoNothing;
import org.usfirst.frc.team3735.robot.util.settings.BooleanSetting;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous {
	
	private SendableChooser<StartingState> posChooser;
	private SendableChooser<Priority> priority;
	private BooleanSetting complexity;
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
		
		complexity = new BooleanSetting("Complex Auto", true);
		
		
	}
	
	public void chooseAutonomous() {
		cancel();
		String s = DriverStation.getInstance().getGameSpecificMessage().toLowerCase().trim().substring(0,2);
		
		System.out.println("Specific Message interpreted: " + s);
		
		if(priority.getSelected() == null || priority.getSelected() == Priority.LINE) {
			autoCommand = new UnknownStraight();
			return;
		}
		
		boolean complex = complexity.getValue();
		switch(posChooser.getSelected()) {
		
		case LEFT:
			switch(s) {
			case "lr"://Switch
				autoCommand = (priority.getSelected() == Priority.SCALE) ? new LeftScaleRight(complex) : new LeftSwitchLeft(complex); //priority choose
				break;
			case "rl"://Scale
				autoCommand = new LeftScaleLeft(complex);
				break;
			case "ll"://Scale and Switch
				autoCommand = (priority.getSelected() == Priority.SCALE || priority.getSelected() == Priority.SCALEIFEASY) ? 
						new LeftScaleLeft(complex) : new LeftSwitchLeft(complex); //priority choose
				break;
			case "rr":
				autoCommand = new LeftScaleRight(complex);
				break;
			}
			break;
		case MID:
			switch(s) {
			case "lr":
				autoCommand = new MidSwitchLeft(complex);
				break;
			case "rl":
				autoCommand = new MidSwitchRight(complex);
				break;
			case "ll":
				autoCommand = new MidSwitchLeft(complex);
				break;
			case "rr":
				autoCommand = new MidSwitchRight(complex);
				break;
			}
			break;
		case RIGHT:
			switch(s) {
			case "lr"://Scale
				autoCommand = new RightScaleRight(complex);
				break;
			case "rl"://Switch
				autoCommand = (priority.getSelected() == Priority.SCALE) ? 
						new RightScaleLeft(complex) : new RightSwitchRight(complex); //priority choose				break;
			case "ll":
				autoCommand = new RightScaleLeft(complex);
				break;
			case "rr"://Scale and Switch
				autoCommand = (priority.getSelected() == Priority.SCALE || priority.getSelected() == Priority.SCALEIFEASY) ? 
						new RightScaleRight(complex) : new RightSwitchRight(complex); //priority choose
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
