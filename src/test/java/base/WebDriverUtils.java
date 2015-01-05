/**
 * @author invy
 * 
 */
package base;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils {
	
	private static WebDriver driver;
	private static String browserType;
	public static final String BUILD_START_DATE_TIME;


	static {
		loadConfigProperties();
		browserType = fetchBrowserType();
		BUILD_START_DATE_TIME = getDateAndTime();
	}
	
	private WebDriverUtils(){
	}
	
	public static void start() {
		getDriver();
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

	private static WebDriver getDriver(){
		if (driver == null) {
			switch (browserType) {
			case "firefox": 
				driver = new FirefoxDriver();
				driver.manage().window().maximize(); break;
			case "chrome": 
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("test-type");
				chromeOptions.addArguments("start-maximized");
				driver = new ChromeDriver(chromeOptions);
				driver.manage().window().maximize(); break;
			case "iexplorer": 
				driver = new InternetExplorerDriver(); break;
			default: throw new RuntimeException();
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
		System.setProperty("webdriver.ie.driver",
				driverProperties.get("iedriverpath").toString());
	}

	private static String fetchBrowserType(){
		String browser = System.getProperty("browser");
		System.out.println("Starting Selenium on " + browser);
		return browser;
	}
	
}