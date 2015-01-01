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
import java.util.Date;

public class TestBase {

	private Date dateOfRun; //TODO: implement Date fetching


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
			tearDown();
		}

		@Override
		public void failed(Throwable e, Description description) {
			try {
				File screenshot = WebDriverUtils.takeScreenshot();

				String filePathRoot = ".\\screenshots\\" + WebDriverUtils.getBrowserType() + "\\" + dateOfRun + "\\";
				String fullFilePath = filePathRoot + description.getClassName() + "\\" + description.getMethodName() + ".jpg";

				FileUtils.copyFile(screenshot, new File(fullFilePath));
			} catch(Exception ex) {
				System.out.println(ex.toString());
				System.out.println(ex.getMessage());
			}

			tearDown();
		}
	};
}