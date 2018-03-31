package org.usfirst.frc.team3735.robot.util.motion.exceptions;

import java.nio.file.Path;

public class MissingColumnException extends Exception {

	private static final long serialVersionUID = -8819325514514271424L;


	public MissingColumnException(Path theFile, String columnName)
	{
		super(String.format("Column %s does not exist in file %s.", columnName, theFile));
	}
	
	
}
