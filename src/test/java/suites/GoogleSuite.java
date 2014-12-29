/**
 * @author invy
 */
package test.java.suites;

import org.junit.Test;

import test.java.base.TestBase;
import test.java.tests.GoogleHomeTest;

public class GoogleSuite extends TestBase{

	//TestCase Summary or id
	@Test
	public void googleHomeTest(){
		new GoogleHomeTest().searchVerification();
	}
	
}
