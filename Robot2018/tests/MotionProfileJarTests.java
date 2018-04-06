//import static org.junit.jupiter.api.Assertions.*;
//
//import java.io.IOException;
//
//import org.junit.Assert;
//import org.junit.jupiter.api.Test;
//import org.usfirst.frc.team3735.robot.util.motion.MotionData;
//import org.usfirst.frc.team3735.robot.util.motion.MotionProfile;
//import org.usfirst.frc.team3735.robot.util.motion.MotionSet;
//import org.usfirst.frc.team3735.robot.util.motion.exceptions.MissingColumnException;
//
//class MotionProfileJarTests {
//
//	
//	@Test
//	void testCanary ()
//	{
//		Assert.assertTrue(true);
//	}
//	
//	@Test
//	void testLoadBigFromJar () throws IOException, MissingColumnException
//	{
//		
//		MotionSet set = MotionProfile.builder().withProfileName("big").withProfilesFromJar().make();
//	
//		int counter = 0;
//		for (MotionData d : set)
//			counter++;
//		
//		Assert.assertEquals(counter, 18627);
//		
//	}
//
//}
