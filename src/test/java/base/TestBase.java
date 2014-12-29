/**
 * @author invy
 * 
 */
package test.java.base;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public class TestBase {
	
	@BeforeClass
	public static void setup(){
		WebDriverUtils.start();
	}
	
	@AfterClass
	public static void tearDown(){
		WebDriverUtils.stop();
	}
	
}