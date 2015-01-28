/**
 * @author invy
 */
package suites;

import base.TestBase;
import data.GoogleHomeData;
import org.junit.Test;
import tests.GoogleHomeTest;

public class GoogleSuite extends TestBase{

	//TestCase Summary or id
	@Test
	public void googleHomeTest(){
		new GoogleHomeTest().searchVerification(GoogleHomeData.QUERY);
	}

	@Test
	public void googleHomeTest2(){
		new GoogleHomeTest().searchVerification("Tesla");
	}

	@Test
	public void googleHomeTest3(){
		new GoogleHomeTest().searchVerification("babies");
	}

	@Test
	public void googleHomeTest4(){
		new GoogleHomeTest().searchVerification("Border Collie");
	}

	@Test
	public void googleHomeTest5(){
		new GoogleHomeTest().searchVerification("luck");
	}

}