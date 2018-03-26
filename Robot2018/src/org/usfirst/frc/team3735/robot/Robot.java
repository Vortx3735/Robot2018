package org.usfirst.frc.team3735.robot;

import org.usfirst.frc.team3735.robot.assists.NavxAssist;
import org.usfirst.frc.team3735.robot.commands.auto.*;
import org.usfirst.frc.team3735.robot.commands.drive.movedistance.MoveDDx;
import org.usfirst.frc.team3735.robot.commands.drive.positions.ResetPosition;
import org.usfirst.frc.team3735.robot.commands.drive.positions.SetToLastPosition;
import org.usfirst.frc.team3735.robot.commands.drive.positions.ZeroYaw;
import org.usfirst.frc.team3735.robot.commands.drive.recorder.RecordProfile;
import org.usfirst.frc.team3735.robot.commands.drive.recorder.SendProfile;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorResetPos;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosDDx;
import org.usfirst.frc.team3735.robot.commands.elevator.ElevatorSetPosPID;
import org.usfirst.frc.team3735.robot.commands.sequences.AutoScaleLineup;
import org.usfirst.frc.team3735.robot.commands.sequences.AutoSwitchLineup;
import org.usfirst.frc.team3735.robot.ois.GTAOI;
import org.usfirst.frc.team3735.robot.settings.Dms;
import org.usfirst.frc.team3735.robot.subsystems.Carriage;
import org.usfirst.frc.team3735.robot.subsystems.Climber;
import org.usfirst.frc.team3735.robot.subsystems.CubeAngler;
import org.usfirst.frc.team3735.robot.subsystems.CubeIntake;
import org.usfirst.frc.team3735.robot.subsystems.Drive;
import org.usfirst.frc.team3735.robot.subsystems.Elevator;
import org.usfirst.frc.team3735.robot.subsystems.Navigation;
import org.usfirst.frc.team3735.robot.subsystems.Vision;
import org.usfirst.frc.team3735.robot.util.bases.VortxIterative;
import org.usfirst.frc.team3735.robot.util.choosers.AutoChooser;
import org.usfirst.frc.team3735.robot.util.choosers.DoNothing;
import org.usfirst.frc.team3735.robot.util.choosers.SideChooser;
import org.usfirst.frc.team3735.robot.util.oi.DriveOI;
import org.usfirst.frc.team3735.robot.util.profiling.Position;
import org.usfirst.frc.team3735.robot.util.settings.BooleanSetting;
import org.usfirst.frc.team3735.robot.util.settings.Setting;
import org.usfirst.frc.team3735.robot.util.settings.StringSetting;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends VortxIterative {

	
	public static Drive drive;
	public static Navigation navigation;
	public static Vision vision;
	public static CubeIntake cubeIntake;
	public static Elevator elevator;
	public static Climber climber;
	public static Carriage carriage;
	public static CubeAngler angler;
	
	public static Autonomous autoLogic;
	
	public static GTAOI oi;
	
	
	public static SideChooser sideChooser;
	public static AutoChooser autoChooser;
	
	


	
	
	@Override
	public void robotInit() {
		drive = new Drive();
		navigation = new Navigation();
		vision = new Vision();
		cubeIntake = new CubeIntake();
		elevator = new Elevator();
		climber = new Climber();
		carriage = new Carriage();
		angler = new CubeAngler();
		
		oi = new GTAOI(); //MUST be instantiated after the subsystems
			
		autoLogic = new Autonomous();
		/*
		 * Selections:
		 * Side: Red, Blue
		 * Starting Position: Left, Mid, Right
		 * Priority: Scale, Switch, Line
		 * Complexity: Strict, Curved
		 * 
		 */
		 
		boolean complex = true;
		autoChooser = new AutoChooser();
			autoChooser.addObject("Left Scale Left", new LeftScaleLeft(complex));
			autoChooser.addObject("Left Scale Right", new LeftScaleRight(complex));
			autoChooser.addObject("Left Switch Left", new LeftSwitchLeft(complex));
			autoChooser.addObject("Mid Switch Left", new MidSwitchLeft(complex));
			autoChooser.addObject("Mid Switch Right", new MidSwitchRight(complex));
			autoChooser.addObject("Right Scale Left", new RightScaleLeft(complex));
			autoChooser.addObject("Right Scale Right", new RightScaleRight(complex));
			autoChooser.addObject("Right Switch Right", new RightSwitchRight(complex));
			autoChooser.addObject("Drive Forward", new UnknownStraight());
			
			
		sideChooser = new SideChooser();
//			posChooser.addDefault("Left", new Position(-Dms.Field.HALFWALLWIDTH + Dms.Bot.HALFWIDTH, Dms.Bot.HALFLENGTH, 0));
//			posChooser.addDefault("Middle", new Position(0, Dms.Bot.HALFLENGTH, 0));
//			posChooser.addDefault("Right", new Position(Dms.Field.HALFWALLWIDTH - Dms.Bot.HALFWIDTH, Dms.Bot.HALFLENGTH, 0));

		SmartDashboard.putData("Autonomous Testing", autoChooser);
		SmartDashboard.putData("Side", sideChooser);

		
		

//		SmartDashboard.putData(new TurnTo(new Setting("Turning Setpoint")));

		
	}
	@Override
	public void robotPeriodic() {		
		Setting.fetchAround();
		BooleanSetting.fetchAround();
		
//        vision.debugLog();
//        navigation.integrate();
        navigation.displayPosition();
        drive.debugLog();
        
        
        log();       
	}
	@Override
	public void robotContinuous() {
		navigation.integrate();
	}
	
	

	@Override
	public void autonomousInit() {
		
		
		
		navigation.resetPosition(autoLogic.getStartingPosition());
		autoLogic.chooseAutonomous();
		autoLogic.printAuto();
//		autoLogic.startCommand();
		autoChooser.startSelected();
		
	}
	@Override
	public void autonomousPeriodic() {
		 Scheduler.getInstance().run();
		 vision.refresh();
	}
	@Override
	public void autonomousContinuous() {
		
	}
	
	

	@Override
    public void teleopInit() {
        autoChooser.cancel();
        autoLogic.cancel();
    }
	@Override
	public void teleopPeriodic() {
        Scheduler.getInstance().run();
	}
	@Override
	public void teleopContinuous() {

	}
	
	

	@Override
	public void testPeriodic() {
	}
	@Override
	public void disabledInit() {
		autoChooser.cancel();
	}
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();

	}
	@Override
	public void disabledContinuous() {
		
	}
	
	
	
	public void log(){
		drive.log();
		navigation.log();
		vision.log();
		elevator.log();
		carriage.log();
		
	}
	
	public void debugLog(){
//		drive.debugLog();
//		navigation.debugLog();
		elevator.debugLog();
//		vision.debugLog();
		
	}
	


}

