package org.usfirst.frc.team3735.robot;

import org.usfirst.frc.team3735.robot.commands.drive.positions.ResetPosition;
import org.usfirst.frc.team3735.robot.commands.drive.positions.ZeroYaw;
import org.usfirst.frc.team3735.robot.commands.recorder.RecordProfile;
import org.usfirst.frc.team3735.robot.commands.recorder.SendProfile;
import org.usfirst.frc.team3735.robot.ois.GTAOI;
import org.usfirst.frc.team3735.robot.settings.Dms;
import org.usfirst.frc.team3735.robot.subsystems.Drive;
import org.usfirst.frc.team3735.robot.subsystems.Navigation;
import org.usfirst.frc.team3735.robot.subsystems.Vision;
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

	SendableChooser<Command> autonomousChooser;
	Command autonomousCommand;
	
	public static Drive drive;
	public static Navigation navigation;
	public static Vision vision;
	
	public static DriveOI oi;
	
	private double dt;
	private double prevTime = 0;


	public static SendableChooser<Side> sideChooser;
	public static Side side = Side.Left;
	
	
	
	@Override
	public void robotInit() {
		drive = new Drive();
		navigation = new Navigation();
		vision = new Vision();
		
		oi = new GTAOI(); //MUST be instantiated after the subsystems
			
		autonomousChooser = new SendableChooser<Command>();


		SmartDashboard.putData("AUTONOMOUS SELECTION", autonomousChooser);
		
		sideChooser = new SendableChooser<Side>();
			sideChooser.addDefault("Red", Side.Left);
			sideChooser.addObject("Blue", Side.Right);
		SmartDashboard.putData("Side Selection", sideChooser);	
		
		SmartDashboard.putData("Reset Position", new ResetPosition());

		SmartDashboard.putData("Zero Yaw", new ZeroYaw());
		SendProfile s = new SendProfile("defaultfile");
		SmartDashboard.putData(s);
		SmartDashboard.putData("Load File", new InstantCommand() {
			@Override
			protected void initialize() {
				s.loadFile("defaultfile");
			}
			
		});
		SmartDashboard.putData(new RecordProfile());


		side = Side.Left;
		
		prevTime = Timer.getFPGATimestamp();
	}
	@Override
	public void robotPeriodic() {		
		Setting.fetchAround();
		BooleanSetting.fetchAround();
		
        vision.debugLog();
        //navigation.integrate();
        navigation.displayPosition();
        drive.debugLog();
        log();       
	}
	@Override
	public void robotContinuous() {
//		dt = Timer.getFPGATimestamp() - prevTime;
//		prevTime += dt;
//		SmartDashboard.putNumber("dt", dt);
		navigation.integrate();
	}
	
	

	@Override
	public void autonomousInit() {
		navigation.resetPosition();
		retrieveSide();
        autonomousCommand = autonomousChooser.getSelected();
        if (autonomousCommand != null) autonomousCommand.start();
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
        if (autonomousCommand != null) autonomousCommand.cancel();
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
		if (autonomousCommand != null)
			autonomousCommand.cancel();
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
		//shooter.log();
		//ballIntake.log();
		navigation.log();
		//ultra.log();
		vision.log();
	}
	
	public void debugLog(){
		drive.debugLog();
		//shooter.debugLog();
		//ballIntake.debugLog();
		navigation.debugLog();
		//ultra.debugLog();
		vision.debugLog();
	}
	
	
	

	
	
	public static void retrieveSide(){
		if(sideChooser.getSelected() != null){
			side = sideChooser.getSelected();
		}else{
			System.out.println("Error : sideChooser was found null when retrieving side.");
		};
	}


}

