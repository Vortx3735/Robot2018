package org.usfirst.frc.team3735.robot.util.motion;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.usfirst.frc.team3735.robot.util.motion.exceptions.MissingColumnException;

public class MotionProfile {

	private static CSVFormat _theFormat = CSVFormat.newFormat(',').withFirstRecordAsHeader();

	enum ColumnsFromFile
	{
		x,
		y,
		velocity,
		heading
	};
	
	private Path _left;
	private Path _right;

	private MotionProfile(Path left, Path right) {
		_left = left;
		_right = right;
	}

	/**
	 * Loads the motion data set from a profile.
	 * 
	 * @param pathRoot
	 *            The root path where the profiles are kept.
	 * 
	 * @param profileName
	 *            The name of the profile, which is the part of the filename before
	 *            _left_detailed.csv and _right_detailed.csv
	 * 
	 * @return An instance of the profile reader.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public final static MotionProfile withStoredProfile(String pathRoot, String profileName)
			throws FileNotFoundException {

		Path leftPath = FileSystems.getDefault().getPath(pathRoot, String.format("%s_left_detailed.csv", profileName));
		Path rightPath = FileSystems.getDefault().getPath(pathRoot,
				String.format("%s_right_detailed.csv", profileName));

		if (!leftPath.toFile().exists())
			throw new FileNotFoundException(leftPath.toString());

		if (!rightPath.toFile().exists())
			throw new FileNotFoundException(rightPath.toString());

		return new MotionProfile(leftPath, rightPath);
	}

	/**
	 * Loads the motion data set from a profile.  Uses the current working directory as the path root.
	 * 
	 * @param profileName
	 *            The name of the profile, which is the part of the filename before
	 *            _left_detailed.csv and _right_detailed.csv
	 * @return
	 * @throws FileNotFoundException
	 */
	public final static MotionProfile withStoredProfile(String profileName)
			throws FileNotFoundException {
		
		return withStoredProfile (System.getProperty("user.dir"), profileName);
	}
	
	
	private void assertHeaderIsValid (Path forFile, Map<String, Integer> columnNames) throws MissingColumnException
	{
		
		for (ColumnsFromFile column : ColumnsFromFile.values() )
		{
			if (!columnNames.containsKey(column.toString()))
				throw new MissingColumnException (forFile, column.toString());
		}
		
	}

	public final MotionSet build() throws IOException, MissingColumnException {
		CSVParser leftParser = CSVParser.parse(_left, Charset.forName("ASCII"), _theFormat);
		CSVParser rightParser = CSVParser.parse(_right, Charset.forName("ASCII"), _theFormat);
		
		assertHeaderIsValid (_right, rightParser.getHeaderMap());
		assertHeaderIsValid(_left, leftParser.getHeaderMap());

		return new MotionSet (leftParser, rightParser);
	}

}
