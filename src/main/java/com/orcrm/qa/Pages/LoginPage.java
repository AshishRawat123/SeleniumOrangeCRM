package com.orcrm.qa.Pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orcrm.qa.BaseSetup.BaseTest;

public class LoginPage extends BaseTest {
	
	
	@FindBy(id="txtUsername")
	WebElement username;
	
	@FindBy(name="txtPassword")
	WebElement password;
	
	@FindBy(id= "btnLogin")
	WebElement submitlogin;
	
	@FindBy(id="welcome")
	List <WebElement> welcomeUser;
	
	@FindBy(xpath = "//*[@id='mainMenuFirstLevelUnorderedList']/li")
	List <WebElement> HomePageModule;
	
	
	public LoginPage () throws IOException {
		super();
		PageFactory.initElements(driver, this);
	}
	
	public int login(String User , String Pass) throws InterruptedException {
		
		System.out.println(driver.findElements(By.id("txtUsername")).size());
		username.sendKeys(User);
		password.sendKeys(Pass);
		submitlogin.click();
		return HomePageModule.size();
		
	}

}
