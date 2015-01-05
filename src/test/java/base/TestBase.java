/**
 * @author invy
 * 
 */
package base;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestBase {

	@BeforeClass
	public static void setup(){
		WebDriverUtils.start();
	}
	
	@AfterClass
	public static void tearDown(){
		WebDriverUtils.stop();
	}

	@Rule
	public TestRule watcher = new TestWatcher() {
		@Override
		public void finished(Description description) {
			saveScreenshotAsFile(description, "passed");
			tearDown();
		}

		@Override
		public void failed(Throwable e, Description description) {
			saveScreenshotAsFile(description, "failed");
			tearDown();
		}
	};

	private void saveScreenshotAsFile(Description description, String result) {
		try {
            File screenshot = WebDriverUtils.takeScreenshot();
            String filePathRoot = ".\\screenshots\\" + WebDriverUtils.getBrowserType() + "\\"
					+ WebDriverUtils.BUILD_START_DATE_TIME + "\\"
					+ description.getClassName().substring(description.getClassName().indexOf(".") + 1) + "\\";

            String fullFilePath = filePathRoot + result + " " + WebDriverUtils.getDateAndTime()
					+  " - " + description.getMethodName() + ".jpg";

            FileUtils.copyFile(screenshot, new File(fullFilePath));
        } catch(Exception ex) {
            System.out.println(ex.toString());
            System.out.println(ex.getMessage());
        }
	}

}