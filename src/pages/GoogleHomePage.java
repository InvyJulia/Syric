package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.WebDriverUtils;

public class GoogleHomePage {

	WebElement searchTextField;
	WebElement resultsDiv;
	List<WebElement> allSugestions;
	WebElement searchButton;

	public GoogleHomePage(){
		searchTextField = WebDriverUtils.getElementBy(By.name("q"));
		resultsDiv = WebDriverUtils.getElementBy(By.className("gssb_e"));
		allSugestions = WebDriverUtils.getElementsBy(By.xpath("//div[@id='ires']"));
		searchButton = WebDriverUtils.getElementBy(By.id("gbqfb"));
	}
	
	public GoogleHomePage searchQuery(String query){
		searchTextField.sendKeys(query);
		searchButton.click();
		return this;
	}
	
	public GoogleHomePage waitUntilDivVisible(){
		long end = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < end) {
            new GoogleHomePage();
            if (resultsDiv.isDisplayed()) {
              break;
            }
        }
		return this;
	}
	
	public GoogleHomePage fetchAndPrintSearchResults(){
		new GoogleHomePage();
        for (WebElement suggestion : allSugestions) {
            System.out.println(suggestion.getText());
        }
		return this;
	}
	
	public boolean verifyQueryPresence(String query){
		List<WebElement> divsWithQueryPresent = WebDriverUtils.getElementsBy(By.xpath("//div[@class=\'rc\' and contains(., \'" + query + "\')]"));
		return !divsWithQueryPresent.isEmpty();
	}
}
