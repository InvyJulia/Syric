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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverUtils {

	private static WebDriver driver;
	
//	private WebDriverUtils(){
//	}
	
	public static void start(String driverType) {
		getDriver(driverType);
	}

	public static void stop() {
		driver.quit();
	}

	//TODO: revert to PRIVATE!
	public static WebDriver getDriver(String driverType){
		if (driver == null) {
			switch (driverType) {
				case "firefox":	driver = new FirefoxDriver(); break;
				case "chrome": driver = new ChromeDriver(); break;
				case "internet explorer": driver = new InternetExplorerDriver(); break;
					
				default: throw new RuntimeException();
			}
		}
		return driver;
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
	
}