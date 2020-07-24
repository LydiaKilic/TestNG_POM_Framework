package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.ElementUtil;

public class HomePage extends BasePage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	By header=By.xpath("//i18n-string");
	//By accountName=By.xpath("/html[1]/body[1]/div[2]/nav[1]/div[1]/div[2]/div[6]/div[1]/div[2]/a[1]/div[1]");	
		
	By mainContactsLink = By.id("nav-primary-contacts-branch");
	By childContactsLink = By.id("nav-secondary-contacts");
	
	public HomePage(WebDriver driver){
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
	}
	
	public String getHomePageTitle(){
		return elementUtil.doGetPageTitle();
	}
	
	public String getHomePageHeader(){
		return elementUtil.doGettext(header);
	
	}
	
	public void clickOnContacts(){
		elementUtil.waitForElementPresent(mainContactsLink);
		elementUtil.doClick(mainContactsLink);
		
		elementUtil.waitForElementPresent(childContactsLink);
		elementUtil.doClick(childContactsLink);
		
	}
	
	public ContactsPage goToContactsPage() {
		clickOnContacts();
		return new ContactsPage(driver);
	}
//	public String getLoggedInUserAccountNAme(){
//		return elementUtil.doGettext(accountName);
//	}
	
}