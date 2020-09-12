package TestPages;

import java.io.IOException;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.orcrm.qa.BaseSetup.BaseTest;
import com.orcrm.qa.Pages.LoginPage;

public class LoginPageTest extends BaseTest {

	public LoginPage lp;
	
	public LoginPageTest() throws IOException {
		super();
	}
	
	@BeforeTest
	public void setupbrowser() throws InterruptedException, IOException {
		launchWebDriver();
		lp =  new LoginPage();
		SessionId sessionid = ((RemoteWebDriver) driver).getSessionId();
		System.out.println(sessionid);
	}
	
	@Test()
	public void avalidateLogin() throws InterruptedException {
		logger = extents.createTest("verify Page title");
//		System.out.println(driver.getTitle());
		Thread.sleep(3000);
		int sz = lp.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(sz, 11);		
	}
	
	@Test()
	public void test2() {
		logger = extents.createTest("verify Test2");
		Assert.assertTrue(false);
	}
	
	@Test()
	public void test3() {
		logger = extents.createTest("verify Test3");
		Assert.assertTrue(true);
	}
	@AfterMethod()
	public void afterMethodOfTestClass(ITestResult result) throws IOException {
		teardown(result);
		// In Selenium 4 we don't need to close or quit window,its automatic.
		//driver.quit();
	}
	
	
	
	

	
}
