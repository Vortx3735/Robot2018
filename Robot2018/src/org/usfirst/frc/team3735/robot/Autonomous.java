package org.usfirst.frc.team3735.robot;

import org.usfirst.frc.team3735.robot.commands.auto.*;
import org.usfirst.frc.team3735.robot.commands.auto.cows.*;
import org.usfirst.frc.team3735.robot.commands.auto.sec.*;
import org.usfirst.frc.team3735.robot.settings.Dms;
import org.usfirst.frc.team3735.robot.settings.Waypoints;
import org.usfirst.frc.team3735.robot.util.choosers.DoNothing;
import org.usfirst.frc.team3735.robot.util.profiling.Position;
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
	
	private Command firstCommand = new DoNothing();
	private Command secondCommand = new DoNothing();

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
	
	public void startCommand() {
		
	}
	
	public void chooseAutonomous() {
		cancel();
		String s = DriverStation.getInstance().getGameSpecificMessage().toLowerCase().trim().substring(0,2);
		
		System.out.println("Specific Message interpreted: " + s);
		
		if(priority.getSelected() == null || priority.getSelected() == Priority.LINE) {
			firstCommand = new UnknownStraight();
			return;
		}
		
		boolean complex = complexity.getValue();
		switch(posChooser.getSelected()) {
		
		
		case LEFT:
			switch(s) {
			case "lr"://Switch
				firstCommand = (priority.getSelected() == Priority.SCALE) ? new LeftScaleRight(complex) : new LeftSwitchLeft(complex); //priority choose
				break;
			case "rl"://Scale
				firstCommand = new LeftScaleLeft(complex);
				break;
			case "ll"://Scale and Switch
				firstCommand = (priority.getSelected() == Priority.SCALE || priority.getSelected() == Priority.SCALEIFEASY) ? 
						new LeftScaleLeft(complex) : new LeftSwitchLeft(complex); //priority choose
				break;
			case "rr":
				firstCommand = new LeftScaleRight(complex);
				break;
			}
			
			
			
			
			
		case MID:
			switch(s) {
			case "lr":
				switch(priority.getSelected()) {
				case SCALE:
				case SCALEIFEASY:
					firstCommand = new MidScaleRight(complex);
					secondCommand = new ScaleRightScaleRight(complex);			
					break;
				case SWITCH:
					firstCommand = new MidSwitchLeft(complex);
					secondCommand = new SwitchLeft(complex);			
					break;
				}
				break;
			case "rl":
				switch(priority.getSelected()) {
				case SCALE:
				case SCALEIFEASY:
					firstCommand = new MidScaleLeft(complex);
					secondCommand = new ScaleLeftScaleLeft(complex);			
					break;
				case SWITCH:
					firstCommand = new MidSwitchRight(complex);
					secondCommand = new SwitchRight(complex);			
					break;
				}
				break;
			case "ll":
				switch(priority.getSelected()) {
				case SCALE:
				case SCALEIFEASY:
					firstCommand = new MidScaleLeft(complex);
					secondCommand = new ScaleLeftScaleLeft(complex);			
					break;
				case SWITCH:
					firstCommand = new MidSwitchLeft(complex);
					secondCommand = new SwitchLeft(complex);		
					break;
				}
				break;
			case "rr":
				switch(priority.getSelected()) {
				case SCALE:
				case SCALEIFEASY:
					firstCommand = new MidScaleRight(complex);
					secondCommand = new ScaleRightScaleRight(complex);			
					break;
				case SWITCH:
					firstCommand = new MidSwitchRight(complex);
					secondCommand = new SwitchRight(complex);			
					break;
				}
				break;
			}
			break;
			
			
			
			
		case RIGHT:
			switch(s) {
			case "lr"://Scale
				firstCommand = new RightScaleRight(complex);
				break;
			case "rl"://Switch
				firstCommand = (priority.getSelected() == Priority.SCALE) ? 
						new RightScaleLeft(complex) : new RightSwitchRight(complex); //priority choose				break;
			case "ll":
				firstCommand = new RightScaleLeft(complex);
				break;
			case "rr"://Scale and Switch
				firstCommand = (priority.getSelected() == Priority.SCALE || priority.getSelected() == Priority.SCALEIFEASY) ? 
						new RightScaleRight(complex) : new RightSwitchRight(complex); //priority choose
			}
			break;
			
			
			
		case UNKNOWN:
			break;
						
			
		}
	}
	
	public void start() {
		if(firstCommand != null) {
			firstCommand.start();
		}
	}
	
	public void cancel() {
		if(firstCommand != null) {
			firstCommand.start();
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
	
	public Position getStartingPosition() {
		switch(posChooser.getSelected()) {
		case LEFT:
			return new Position(-Dms.Field.HALFWALLWIDTH + Dms.Bot.HALFWIDTH, Dms.Bot.HALFLENGTH, 180);
		case MID:
		case UNKNOWN:
			return new Position(12.5, Dms.Bot.HALFLENGTH, 180);
		case RIGHT:
			return new Position(Dms.Field.HALFWALLWIDTH - Dms.Bot.HALFWIDTH, Dms.Bot.HALFLENGTH, 180);
		default:
			return new Position(0, Dms.Bot.HALFLENGTH, 180);
		}
	}
	
}
