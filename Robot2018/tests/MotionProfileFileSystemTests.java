
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.usfirst.frc.team3735.robot.util.motion.MotionData;
import org.usfirst.frc.team3735.robot.util.motion.MotionProfile;
import org.usfirst.frc.team3735.robot.util.motion.MotionSet;
import org.usfirst.frc.team3735.robot.util.motion.exceptions.MissingColumnException;

public class MotionProfileFileSystemTests {

	@Test
	public void testCanary ()
	{
		Assert.assertTrue(true);
	}

	
	@Test
	public void testLoadBigFromFS () throws IOException, MissingColumnException
	{
		String temp = System.getProperty("user.dir");
		
		MotionSet set = MotionProfile.builder().withProfileName("big").withProfilesFromFilesystem("src/resources").make();
	
		int counter = 0;
		for (MotionData d : set)
			counter++;
		
		Assert.assertEquals(counter, 18627);
		
	}

}
