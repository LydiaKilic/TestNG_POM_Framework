package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ElementUtil;

public class LoginPage extends BasePage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	//locators----Constructors---Actions(methods)
	
	
	By emailId=By.id("username");
	By password1=By.id("password");
	By loginBtn=By.id("loginBtn");
	By signUp=By.linkText("Sign up23");
	//By accountName=By.xpath("accountName");
	By loginErrorText = By.cssSelector("div.private-alert__inner");
	
	
	public LoginPage(WebDriver driver){
	this.driver=driver;
	elementUtil=new ElementUtil(driver);
}
	//Actions==methods
	public String getPageTitle(){
		elementUtil.waitForTitlePresent(AppConstants.LOGIN_PAGE_TITLE);
	return elementUtil.doGetPageTitle();	
}
	
	
	public boolean checkSignUpLink(){
		elementUtil.waitForElementVisible(signUp);
		return elementUtil.doIsDisplayed(signUp);
	}
	
	public HomePage doLogin(Credentials userCred){
		elementUtil.waitForElementPresent(emailId);
		elementUtil.doSendKeys(emailId, userCred.getAppUsername());
		elementUtil.waitForElementPresent(password1);
		elementUtil.doSendKeys(password1, userCred.getAppPassword());
		elementUtil.doClick(loginBtn);
		
		
//		driver.findElement(emailId).sendKeys(username);
//		driver.findElement(password1).sendKeys(password);
//		driver.findElement(loginBtn).click();
//		
		return  new HomePage(driver);
		
		
	}
	
	public boolean checkLoginErrorMessage(){
		return elementUtil.doIsDisplayed(loginErrorText);
	}
	
	
	
	
	
	
}