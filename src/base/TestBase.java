/**
 * @author invy
 * 
 */
package base;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.openqa.selenium.remote.BrowserType;

public class TestBase {
	
	@BeforeClass
	public static void setup(){
		WebDriverUtils.start(BrowserType.FIREFOX);
	}
	
	@AfterClass
	public static void tearDown(){
		WebDriverUtils.stop();
	}
	
}
