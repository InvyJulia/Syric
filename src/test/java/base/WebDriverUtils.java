/**
 * @author invy
 * 
 */
package base;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class WebDriverUtils {
	
	public static final String BUILD_START_DATE_TIME;
	private static WebDriver driver;
	private static String browserType;
	private static String browserVersion;
	private static String HUB_URL;


	static {
		loadConfigProperties();
//		fetchBrowserType();
		BUILD_START_DATE_TIME = getDateAndTime();
	}
	
	private WebDriverUtils(){
	}
	
	public static void start() {
//		getDriver();
		try {
			driver = new RemoteWebDriver(new URL(HUB_URL), DesiredCapabilities.chrome());
		} catch (MalformedURLException e) {
			System.out.println("Hub url is not correct. Read url from config is: \"" + HUB_URL + "\"");
		}
	}

	public static void stop() {
		driver.quit();
	}

	public static WebElement getElementBy(By by){
		return driver.findElement(by);
	}

	public static List<WebElement> getElementsBy(By by) {
		return driver.findElements(by);
	}
	
	public static void navigate(String url){
		driver.get(url);
	}

	public static void waitForElementVisible(By by){
		new WebDriverWait(getDriver(), 20).until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public static File takeScreenshot(){
		return ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
	}

	public static String getBrowserType(){
		return browserType;
	}

	public static String getDateAndTime(){
		DateFormat dateFormat = new SimpleDateFormat("MMM,dd yyy  HH_mm_ss");
		Date date = new Date();

		return dateFormat.format(date);
	}

	private static final WebDriver getDriver() {
		if (driver == null) {
//			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//
//			desiredCapabilities.setBrowserName(browserType);
//			desiredCapabilities.setPlatform(Platform.VISTA);
//			desiredCapabilities.setVersion(browserVersion);
//			desiredCapabilities.setCapability("ignoreProtectedModeSettings", true);
			try {
				driver = new RemoteWebDriver(new URL(HUB_URL), DesiredCapabilities.chrome());
			} catch (MalformedURLException e) {
				System.out.println("Hub url is not correct. Read url from config is: \"" + HUB_URL + "\"");
			}
		}
		return driver;
	}



	private static void loadConfigProperties() {
		Properties driverProperties = new Properties();
		String driverPropertiesFileName = "config.properties";
		try {
			InputStream stream = new BufferedInputStream(new FileInputStream(
					driverPropertiesFileName));
			driverProperties.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.setProperty("webdriver.chrome.driver",
				driverProperties.get("chromedriverpath").toString());
		System.out.println("webdriver.chrome.driver is set to: " + driverProperties.get("chromedriverpath").toString());
				System.setProperty("webdriver.ie.driver", driverProperties.get("iedriverpath").toString());
		System.out.println("webdriver.ie.driver is set to: " + driverProperties.get("iedriverpath").toString());
		HUB_URL = driverProperties.get("huburl").toString();
	}

	private static void fetchBrowserType() {
		//		browserType = System.getProperty("browser");
		//		browserVersion = System.getProperty("version");

		System.out.println("Starting Selenium on Browser." + browserType
				+ " Platform." + Platform.VISTA.toString() + " Version." + browserVersion + "...");
	}
	
}