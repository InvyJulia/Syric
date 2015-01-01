/**
 * @author invy
 * 
 */
package base;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils {
	
	private static WebDriver driver;
	private static String browserType;


	static {
		loadConfigProperties();
		browserType = getBrowserType();
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

	private static String getBrowserType(){
		String browser = System.getProperty("browser");
		System.out.println("Starting Selenium on " + browser);
		return browser;
	}
	
}