/**
 * @author invy
 */
package tests;

import org.junit.Test;
import org.testng.Assert;

import data.GoogleHomeData;
import pages.GoogleHomePage;
import base.TestBase;
import base.WebDriverUtils;

public class GoogleHomeTest extends TestBase{
	
	@Test
	public void searchVerification(){
		WebDriverUtils.navigate(GoogleHomeData.HOME_PAGE_URL);
		GoogleHomePage homePage = new GoogleHomePage();
		
		homePage.searchQuery(GoogleHomeData.QUERY).waitUntilDivVisible()
			.fetchAndPrintSearchResults();
		Assert.assertTrue(homePage.verifyQueryPresence(GoogleHomeData.QUERY), 
				"Search results do not contain '" + GoogleHomeData.QUERY + "' phrase");
	}

}