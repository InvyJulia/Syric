/**
 * @author invy
 */
package tests;

import org.junit.Assert;

import pages.GoogleHomePage;
import base.WebDriverUtils;
import data.GoogleHomeData;

public class GoogleHomeTest{
	
	public void searchVerification(String searchFor){
		WebDriverUtils.navigate(GoogleHomeData.HOME_PAGE_URL);
		GoogleHomePage homePage = new GoogleHomePage();
		
		homePage.searchQuery(searchFor).waitUntilDivVisible();
//			.fetchAndPrintSearchResults();
		Assert.assertTrue("Search results do not contain '" + searchFor+ "' phrase",
				homePage.verifyQueryPresence(searchFor));
	}

}