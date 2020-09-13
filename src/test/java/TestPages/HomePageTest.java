package TestPages;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orcrm.qa.BaseSetup.BaseTest;
import com.orcrm.qa.GlobalVariable.ConstantVariables;
import com.orcrm.qa.Pages.HomePage;
import com.orcrm.qa.Pages.LoginPage;

public class HomePageTest extends BaseTest {
	
	public LoginPage lp;
	public HomePage hm;
	
	public HomePageTest() throws IOException {
		super();		
	}
	
	@BeforeMethod
	public void setupbrowser() throws InterruptedException, IOException {
		launchWebDriver();
		lp =  new LoginPage();
		hm = new HomePage();
	}
	
	@Test
	public void addUserTest() throws InterruptedException {
		lp.login(prop.getProperty("username"), prop.getProperty("password"));
		Thread.sleep(ConstantVariables.STEP_WAIT);
		hm.addUser2();
//		hm.addUserDatails();
	}

}
