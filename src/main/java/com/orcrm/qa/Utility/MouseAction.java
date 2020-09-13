package com.orcrm.qa.Utility;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orcrm.qa.BaseSetup.BaseTest;

public class MouseAction extends BaseTest{

	public MouseAction() throws IOException {
		super();
	}

	public static void moveToAndClick(Actions act , WebElement[] el) {
		
		for(int i=0;i<el.length;i++) {
			
			if(i==el.length-1) {
				System.out.println(el[i].getText());
				act.moveToElement(el[i]).click().build().perform();
			}	
			System.out.println(el[i].getText());
			act.moveToElement(el[i]).build().perform();
		}
	}
}
