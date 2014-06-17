package base;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import suites.GoogleSuite;

public class TestRunner {
	
	public static void main(String[] args) {
		WebDriverUtils.driverType = args[0];
		
		Result result = JUnitCore.runClasses(GoogleSuite.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
	}
	
}