package org.usfirst.frc.team3735.robot.util.motion;

import org.apache.commons.csv.CSVRecord;
import org.usfirst.frc.team3735.robot.util.motion.MotionProfile.ColumnsFromFile;
import org.usfirst.frc.team3735.robot.util.motion.exceptions.ColumnValueMismatchException;

public class MotionData {

	// TODO: Need to separate returned data into left and right.

	private Double _centerX;
	private Double _centerY;
	private Double _heading;
	private Double _leftV;
	private Double _rightV;

	private long _lineNumber;

	MotionData(CSVRecord left, CSVRecord right, long lineNum)
			throws NumberFormatException, ColumnValueMismatchException {
		_lineNumber = lineNum;

		_centerX = (Double.valueOf(left.get(MotionProfile.ColumnsFromFile.x))
				+ Double.valueOf(right.get(MotionProfile.ColumnsFromFile.x))) / 2.0;

		_centerY = (Double.valueOf(left.get(MotionProfile.ColumnsFromFile.y))
				+ Double.valueOf(right.get(MotionProfile.ColumnsFromFile.y))) / 2.0;

		assertEqualValues(Double.valueOf(left.get(MotionProfile.ColumnsFromFile.heading)),
				Double.valueOf(right.get(MotionProfile.ColumnsFromFile.heading)),
				MotionProfile.ColumnsFromFile.heading);
		_heading = Double.valueOf(left.get(MotionProfile.ColumnsFromFile.heading));

		_leftV = Double.valueOf(left.get(MotionProfile.ColumnsFromFile.velocity));
		_rightV = Double.valueOf(right.get(MotionProfile.ColumnsFromFile.velocity));

	}

	private void assertEqualValues(Double l, Double r, ColumnsFromFile c) throws ColumnValueMismatchException {
		if (l.compareTo(r) != 0)
			throw new ColumnValueMismatchException(c.toString(), _lineNumber, l.toString(), r.toString());
	}

	public Double getCenterX() {
		return _centerX;
	}

	public Double getCenterY() {
		return _centerY;
	}

	public Double getHeading() {
		return _heading;
	}

	public Double getLeftV() {
		return _leftV;
	}

	public Double getRightV() {
		return _rightV;
	}

	@Override
	public String toString() {
		return String.format("%d: CenterX: %f CenterY: %f Heading: %f Left Velocity: %f Right Velocity: %f",
				_lineNumber, _centerX, _centerY, _heading, _leftV, _rightV);
	}

}
