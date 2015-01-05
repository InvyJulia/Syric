/**
 * @author invy
 */
package suites;

import base.TestBase;
import org.junit.Test;
import tests.GoogleHomeTest;

public class GoogleSuite extends TestBase{

	//TestCase Summary or id
	@Test
	public void googleHomeTest(){
		new GoogleHomeTest().searchVerification();
	}

}