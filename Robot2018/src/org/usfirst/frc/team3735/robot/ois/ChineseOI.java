package org.usfirst.frc.team3735.robot.ois;

import org.usfirst.frc.team3735.robot.commands.InterruptOperations;
import org.usfirst.frc.team3735.robot.commands.drive.simple.DriveAddSensitiveLeft;
import org.usfirst.frc.team3735.robot.commands.drive.simple.DriveAddSensitiveRight;

//import org.usfirst.frc.team3735.robot.util.oi.DriveOI;
import org.usfirst.frc.team3735.robot.util.oi.ChineseBoard;
import org.usfirst.frc.team3735.robot.util.oi.DriveOI;

public class ChineseOI implements DriveOI{
	
	ChineseBoard board;
	
	public ChineseOI() {
		board = new ChineseBoard();


		board.j.whenPressed(new InterruptOperations());
	}

	public double getDriveMove() {
		return board.getMiddleY();
	}

	 
	public double getDriveTurn() {
		return board.getRightX() * .7 + board.getMiddleX() * .3 + board.getRightZ() * .2;
	}
	
	@Override
	public double getFODMag() {
		return board.getLeftMagnitude();
	}

	@Override
	public double getFODAngle() {
		return board.getLeftAngle();
	}
	
	public boolean isOverriddenByDrive(){
		return Math.abs(getDriveTurn()) > .1 || Math.abs(getDriveMove()) > .1;
	}

	@Override
	public void log() {
		// TODO Auto-generated method stub
		
	}

}
