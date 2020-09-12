package com.orcrm.qa.Utility;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.orcrm.qa.BaseSetup.BaseTest;

public class JsUtility extends BaseTest{

	public static JavascriptExecutor js = (JavascriptExecutor) driver;
	
	public JsUtility() throws IOException {
		super();
	}

	public void scrollTillLast() {
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	public void scrollToElement(WebElement Element) {
		js.executeScript("arguments[0].scrollIntoView();", Element);
	}
	
	public void clickEle(WebElement Element) {
		js.executeScript("arguments[0].click();", Element);
	}
}
