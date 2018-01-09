package org.usfirst.frc.team3735.robot;

import org.usfirst.frc.team3735.robot.commands.auto.DoNothing;
import org.usfirst.frc.team3735.robot.commands.drive.positions.ResetPosition;
import org.usfirst.frc.team3735.robot.commands.drive.positions.ZeroYaw;
import org.usfirst.frc.team3735.robot.ois.GTAOI;
import org.usfirst.frc.team3735.robot.subsystems.Drive;
import org.usfirst.frc.team3735.robot.subsystems.Navigation;
import org.usfirst.frc.team3735.robot.subsystems.Vision;
import org.usfirst.frc.team3735.robot.util.bases.VortxIterative;
import org.usfirst.frc.team3735.robot.util.settings.BooleanSetting;
import org.usfirst.frc.team3735.robot.util.settings.Setting;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
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
	
	public static GTAOI oi;
	
	SendableChooser<Command> autonomousChooser;
	Command autonomousCommand;
	
	public static SendableChooser<Alliance> allianceChooser;
	public static Alliance alliance;
	@Override
	public void robotInit() {

		drive = new Drive();
		navigation = new Navigation();
		vision = new Vision();
		
		oi = new GTAOI(); //MUST be instantiated after the subsystems
		
		
		
		autonomousChooser = new SendableChooser<>();
			autonomousChooser.addDefault("Do Nothing", new DoNothing());
		SmartDashboard.putData("Autonomous Select", autonomousChooser);

		alliance = DriverStation.getInstance().getAlliance();
		allianceChooser = new SendableChooser<>();
			switch(alliance) {
				case Red:
				case Invalid:
					allianceChooser.addDefault("Red", Alliance.Red);
					allianceChooser.addObject("Blue", Alliance.Blue);
					break;
				case Blue:
					allianceChooser.addDefault("Blue", Alliance.Blue);
					allianceChooser.addObject("Red", Alliance.Red);
					break;
			}
		SmartDashboard.putData("Side Selection", allianceChooser);	
		
		
		
		SmartDashboard.putData("Reset Position", new ResetPosition());
		SmartDashboard.putData("Zero Yaw", new ZeroYaw());
//		SmartDashboard.putData(new RecordVoltageData());
//		SmartDashboard.putData(new SendSDVoltage());

	}
	@Override
	public void robotPeriodic() {		
		Setting.fetchAround();
		BooleanSetting.fetchAround();
        Scheduler.getInstance().run();
        navigation.displayPosition();
        //drive.debugLog();
        log();       
	}
	@Override
	public void robotContinuous() {
		navigation.integrate();
	}
	
	

	@Override
	public void autonomousInit() {
		navigation.resetPosition();
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
		navigation.log();
		vision.log();
	}
	
	public void debugLog(){
		drive.debugLog();
		navigation.debugLog();
		vision.debugLog();
	}
	


}

