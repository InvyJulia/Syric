/**
 * @author invy
 * 
 */
package base;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverUtils {
	
	private static WebDriver driver;
	
	
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
	
	
	private static WebDriver getDriver(){
		if (driver == null) {
			String browser = getBrowserType(); 
			
			switch (browser) {
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

	//TODO fetch args accurately
	private static String getBrowserType(){
		String browser = "";
		String[] properties = System.getProperties()
				.values().toString().split(" ");
		for (String string : properties) {
			if (string.contains("browser")){
				browser = string.substring(string.indexOf("=")+1, string.length());
			}
		}
		return browser;
	}
	
}