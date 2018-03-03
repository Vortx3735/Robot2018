package org.usfirst.frc.team3735.robot.util;


import org.usfirst.frc.team3735.robot.util.profiling.Position;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PositionChooser extends SendableChooser<Position> {
	
	public PositionChooser() {
		
	}
	

	@Override
	public Position getSelected() {
		Position s = super.getSelected();
		if(s == null) {
			System.out.println("Null Starting Position detected... returning Middle");
			return new Position(0,0,0);
		}else {
			return s;
		}
	}
	
	

	

}


