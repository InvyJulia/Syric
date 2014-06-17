/**
 * @author invy
 */
package suites;

import org.junit.Test;

import base.TestBase;
import tests.GoogleHomeTest;

public class GoogleSuite extends TestBase{

	//TestCase Summary or id
	@Test
	public void googleHomeTest(){
		new GoogleHomeTest().searchVerification();
	}
	
}
