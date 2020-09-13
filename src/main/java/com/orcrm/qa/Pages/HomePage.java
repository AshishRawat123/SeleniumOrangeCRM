package com.orcrm.qa.Pages;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orcrm.qa.BaseSetup.BaseTest;
import com.orcrm.qa.Utility.MouseAction;

public class HomePage extends BaseTest {

	public HomePage() throws IOException {
		super();
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id ="menu_admin_viewAdminModule")
	WebElement adminMenu;

	//a[@id='menu_admin_UserManagement']/ancestor::li[@class='selected']
	@FindBy(xpath ="//a[@id='menu_admin_UserManagement']")
	WebElement userManagement;
	
	@FindBy(xpath = "//li/a[contains(text(),'Users')]")
	WebElement userbutton;
	
	@FindBy(id="btnAdd")
	WebElement  addUser;
	
	@FindBy(id="systemUser_userType")
	WebElement  userRole;
	
	@FindBy(id ="systemUser_employeeName_empName")
	WebElement empName;
	
	@FindBy(id ="systemUser_userName")
	WebElement userName;
	
	
	public Actions act = new Actions(driver);
	
	public void addUser() {
		act.moveToElement(adminMenu).perform();
		act.moveToElement(userManagement).perform();
		act.moveToElement(userbutton).click().build().perform();
		addUser.click();
	}
	
	/*
	 * Try to Work With Action Class and Keyboard Events
	 */
	public void addUserDatails() {
		act.sendKeys(empName, "Ashish").build().perform();
		act.sendKeys(Keys.TAB).perform();
		act.sendKeys("RAWAT").perform();
	}
	
	/* By Using Utils Class for Mouse Actions
	 */
	
	
	public void addUser2() {
		WebElement [] arrWeb = {adminMenu,userManagement,userbutton};  
		MouseAction.moveToAndClick(act, arrWeb);
		addUser.click();
	}
	
	
}
