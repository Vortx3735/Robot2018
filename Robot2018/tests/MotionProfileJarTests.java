import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.usfirst.frc.team3735.robot.util.motion.MotionData;
import org.usfirst.frc.team3735.robot.util.motion.MotionProfile;
import org.usfirst.frc.team3735.robot.util.motion.MotionSet;
import org.usfirst.frc.team3735.robot.util.motion.exceptions.MissingColumnException;

public class MotionProfileJarTests {

	
	@Test
	public void testCanary ()
	{
		Assert.assertTrue(true);
	}
	
	@Test
	public void testLoadBigFromJar () throws IOException, MissingColumnException
	{
		
		MotionSet set = MotionProfile.builder().withProfileName("big").withProfilesFromJar().make();
	
		int counter = 0;
		for (MotionData d : set)
			counter++;
		
		Assert.assertEquals(counter, 18627);
		
	}

	@Test
	public void testLoadRightRightScaleFromJar () throws IOException, MissingColumnException
	{
		
		MotionSet set = MotionProfile.builder().withProfileName("right_RightScale").withProfilesFromJar().make();
	
		int counter = 0;
		for (MotionData d : set)
			counter++;
		
		Assert.assertTrue(counter > 0);
		
	}

}
