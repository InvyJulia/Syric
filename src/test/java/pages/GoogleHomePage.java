package pages;

import base.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GoogleHomePage {

	WebElement searchTextField;


	public GoogleHomePage(){
		searchTextField = WebDriverUtils.getElementBy(By.name("q"));
	}
	
	public GoogleHomePage searchQuery(String query){
		searchTextField.sendKeys(query);
		WebDriverUtils.getElementBy(By.id("gbqfb")).click();
		return this;
	}
	
	public GoogleHomePage waitUntilDivVisible(){
		WebDriverUtils.waitForElementVisible(By.className("s"));
		return this;
	}
	
	public GoogleHomePage fetchAndPrintSearchResults(){
        for (WebElement suggestion : WebDriverUtils.getElementsBy(By.xpath("//div[@id='ires']//li[@class='g']"))) {
            System.out.println(suggestion.getText());
        }
		return this;
	}
	
	public boolean verifyQueryPresence(String query){
		boolean result = false;
		for (WebElement suggestionElem : WebDriverUtils.getElementsBy(By.xpath("//div[@id='ires']//li[@class='g']"))) {
			if (suggestionElem.getText().contains(query)) {
				result = true;
				break;
			}
		}
		return result;
	}
}
