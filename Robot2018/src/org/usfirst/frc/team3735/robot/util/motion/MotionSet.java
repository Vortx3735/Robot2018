package org.usfirst.frc.team3735.robot.util.motion;

import java.util.Iterator;
import java.util.function.Consumer;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.usfirst.frc.team3735.robot.util.motion.exceptions.ColumnValueMismatchException;

public class MotionSet implements Iterable<MotionData> {
	
	private CSVParser _leftParser;
	private CSVParser _rightParser;
	private long _lineNum = 1;
	
	class IteratorImpl implements Iterator<MotionData> 
	{
		private Iterator<CSVRecord> _left;
		private Iterator<CSVRecord> _right;
		
		IteratorImpl (MotionSet theSet)
		{
			_left = theSet._leftParser.iterator();
			_right = theSet._rightParser.iterator();
		}

		@Override
		public boolean hasNext() {
			return _left.hasNext() && _right.hasNext();
		}

		@Override
		public MotionData next() {
			try {
				return new MotionData (_left.next(), _right.next (), ++_lineNum);
			} catch (NumberFormatException | ColumnValueMismatchException e) {
				throw new RuntimeException (e);
			}
		}
		
	}

	MotionSet (CSVParser left, CSVParser right)
	{
		_leftParser = left;
		_rightParser = right;
	}

	@Override
	public Iterator<MotionData> iterator() {
		return new IteratorImpl(this);
	}


}
