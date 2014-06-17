/**
 * @author invy
 */
package tests;

import org.junit.Assert;

import pages.GoogleHomePage;
import base.WebDriverUtils;
import data.GoogleHomeData;

public class GoogleHomeTest{
	
	public void searchVerification(){
		WebDriverUtils.navigate(GoogleHomeData.HOME_PAGE_URL);
		GoogleHomePage homePage = new GoogleHomePage();
		
		homePage.searchQuery(GoogleHomeData.QUERY).waitUntilDivVisible()
			.fetchAndPrintSearchResults();
		Assert.assertTrue("Search results do not contain '" + GoogleHomeData.QUERY + "' phrase",
				homePage.verifyQueryPresence(GoogleHomeData.QUERY));
	}

}