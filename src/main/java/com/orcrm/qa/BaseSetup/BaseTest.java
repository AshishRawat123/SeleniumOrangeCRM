package com.orcrm.qa.BaseSetup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.orcrm.qa.GlobalVariable.ConstantVariables;



import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	static public WebDriver driver;
	protected static Properties prop;
	public static ExtentReports extents;
	public static ExtentHtmlReporter reporter;
	public static ExtentTest logger;

	public BaseTest() throws IOException {

		prop = new Properties();		
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\orcrm\\qa\\GlobalVariable\\credential.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		if(prop.getProperty("browser").equals("chrome")) {
			WebDriverManager.chromedriver().setup();	
			System.out.println("Chrome setup");
		}
		else
		{
			WebDriverManager.firefoxdriver().architecture(io.github.bonigarcia.wdm.Architecture.X32).setup();
			System.out.println("Firefox setup");		
		}
	}

	public static void launchWebDriver() throws InterruptedException {
		
		// has to make this chromedriver or other  driver .dependents on Credential file
		
//		if(prop.getProperty("browser")=="chrome")
//			WebDriverManager.chromedriver().setup();			
//		else
//			WebDriverManager.firefoxdriver().setup();
			
		if(prop.getProperty("browser").equals("chrome"))
			{
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("disable-infobars");
        if (prop.getProperty("headless") == "true" )
        	chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
			}
		else {
			System.out.println("Why in Firefox path?");
			FirefoxOptions fOptions = new FirefoxOptions();
			fOptions.addArguments("disable-infobars");
			if (prop.getProperty("headless") == "true" )
	        	fOptions.addArguments("--headless");
	        driver = new FirefoxDriver(fOptions);
		}
			
		driver.manage().window().maximize();		
		driver.manage().timeouts().pageLoadTimeout(ConstantVariables.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
		driver.get(prop.getProperty("baseurl"));
		
	}


	// Need to fix this TestNg issue and make reporting config in Before suite
	@BeforeSuite()
	public void reporting() {
		extents = new ExtentReports();
		reporter = new ExtentHtmlReporter(
				System.getProperty("user.dir")+"\\ExtentReport\\ExtentHtmlReport.html");
		System.out.println("running Before suite");
		extents.attachReporter(reporter);
		
	}

	@AfterSuite()
	public void flushreport() {
		extents.flush();
	}
	
		
	public void teardown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.addScreenCaptureFromPath(screenshot(result.getName()));
			logger.log(Status.FAIL, "Failed Due to  " + result.getThrowable());
		   } 
		else if (result.getStatus() == ITestResult.SUCCESS)
			logger.log(Status.PASS, " Passed ");
		else
			logger.log(Status.SKIP, "Skipped");			
	}
	
	public static String screenshot(String testcaseName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "\\FailedTest\\" + testcaseName +".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("test case failed  " + e.getMessage());
		}
		return path;
	}
	
	//Delete all screenshot before executing Test
	@BeforeTest()
	public void deleteSceenshot() {
		File directory = new File(System.getProperty("user.dir") + "\\FailedTest\\");
		File[] files = directory.listFiles();
		for(File fl: files)
		{
			System.out.println("File name to delete :  "+fl.getName());
			//keep status incase a file is unable to delete
			boolean status = fl.delete();
			if(!status) {
				System.out.println("Unable to delete File : "+fl.getName());
			}
		}
	}
	
}
