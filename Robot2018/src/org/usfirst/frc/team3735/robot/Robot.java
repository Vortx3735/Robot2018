package org.usfirst.frc.team3735.robot;

import org.usfirst.frc.team3735.robot.assists.NavxAssist;
import org.usfirst.frc.team3735.robot.commands.auto.RightScaleRight;
import org.usfirst.frc.team3735.robot.commands.drive.MoveDDx;
import org.usfirst.frc.team3735.robot.commands.drive.positions.ResetPosition;
import org.usfirst.frc.team3735.robot.commands.drive.positions.ZeroYaw;
import org.usfirst.frc.team3735.robot.commands.drive.recorder.RecordProfile;
import org.usfirst.frc.team3735.robot.commands.drive.recorder.SendProfile;
import org.usfirst.frc.team3735.robot.ois.GTAOI;
import org.usfirst.frc.team3735.robot.settings.Dms;
import org.usfirst.frc.team3735.robot.subsystems.Carriage;
import org.usfirst.frc.team3735.robot.subsystems.Climber;
import org.usfirst.frc.team3735.robot.subsystems.CubeIntake;
import org.usfirst.frc.team3735.robot.subsystems.Drive;
import org.usfirst.frc.team3735.robot.subsystems.Elevator;
import org.usfirst.frc.team3735.robot.subsystems.Navigation;
import org.usfirst.frc.team3735.robot.subsystems.Vision;
import org.usfirst.frc.team3735.robot.util.AutoChooser;
import org.usfirst.frc.team3735.robot.util.DoNothing;
import org.usfirst.frc.team3735.robot.util.Side;
import org.usfirst.frc.team3735.robot.util.SideChooser;
import org.usfirst.frc.team3735.robot.util.bases.VortxIterative;
import org.usfirst.frc.team3735.robot.util.oi.DriveOI;
import org.usfirst.frc.team3735.robot.util.profiling.Location;
import org.usfirst.frc.team3735.robot.util.settings.BooleanSetting;
import org.usfirst.frc.team3735.robot.util.settings.Setting;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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
	
	public static DriveOI oi;
	
	
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
		
		oi = new GTAOI(); //MUST be instantiated after the subsystems
			
		
		
		autoChooser = new AutoChooser();
			autoChooser.addObject("Right Scale Right", new RightScaleRight());
			
			
		sideChooser = new SideChooser();

		SmartDashboard.putData("Autonomous", autoChooser);
		SmartDashboard.putData("Side", sideChooser);	
		
		
		SmartDashboard.putData("Reset Position", new ResetPosition());
		SmartDashboard.putData("Reset Yaw", new ZeroYaw());
		SmartDashboard.putData(new MoveDDx(100, .6, .03).addA(new NavxAssist()));
		
		SendProfile s = new SendProfile();
		SmartDashboard.putData("Load File", new InstantCommand() {
			@Override
			protected void initialize() {
				s.loadFile("defaultfile");
			}
			
		});
		SmartDashboard.putData(new RecordProfile());

		
	}
	@Override
	public void robotPeriodic() {		
		Setting.fetchAround();
		BooleanSetting.fetchAround();
		
        vision.debugLog();
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
		navigation.resetPosition();
        autoChooser.startSelected();
	}
	@Override
	public void autonomousPeriodic() {
		 Scheduler.getInstance().run();
	}
	@Override
	public void autonomousContinuous() {
		
	}
	
	

	@Override
    public void teleopInit() {
        autoChooser.cancel();
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
		drive.debugLog();
		navigation.debugLog();
		vision.debugLog();
	}
	


}

