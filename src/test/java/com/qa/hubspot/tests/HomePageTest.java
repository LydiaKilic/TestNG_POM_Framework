package com.qa.hubspot.tests;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;

public class HomePageTest {

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	Credentials userCred;
	
	
	@BeforeTest
	public void setUp() throws InterruptedException{
	
	basePage=new BasePage();
	prop=basePage.init_properties();
	String browserName=prop.getProperty("browser");
	driver=basePage.init_driver(browserName);
	driver.get(prop.getProperty("url"));
	loginPage=new LoginPage(driver)	;
    userCred= new Credentials(prop.getProperty("username"), prop.getProperty("password"));
    homePage= loginPage.doLogin(userCred);
	Thread.sleep(5000);
		
		
	}
	
	@Test(priority=1 ,description="Home title-Account Setup | HubSpot")
	public void verifyHomePageTitleTest(){
	String title= homePage.getHomePageTitle();
	System.out.println("homePage title is:: "+ title);
	Assert.assertEquals(title,AppConstants.HOME_PAGE_TITLE);
	}
	
	@Test(priority=2 ,description="Home header-Setup Guide")
	public void verifyHomePageHeaderTest(){
	String header=homePage.getHomePageHeader()	;
	System.out.println(" Home Page Header is:: "+header);
	Assert.assertEquals(header,AppConstants.HOME_PAGE_HEADER);

	
	}
//	@Test(priority=3,description= "account name is =")
//	public void verifyLoggedInUserTest(){
//		String accountName=homePage.getLoggedInUserAccountNAme();
//		System.out.println(accountName);
//		Assert.assertEquals(accountName, "Silicone Labs");
//	}
	
	
	
	@AfterTest
	public void tearDown(){
	driver.quit();	
	}
	
	
	
	

}