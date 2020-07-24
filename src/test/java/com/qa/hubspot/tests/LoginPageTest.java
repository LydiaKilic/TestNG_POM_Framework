package com.qa.hubspot.tests;

import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Epic - 101 : create login features")
@Feature("US - 501 : Create test for login on HubSpot")
public class LoginPageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	Credentials userCred;
	
	Logger log = Logger.getLogger(LoginPageTest.class);
	
	@BeforeTest //@BeforeMethod olsaydi browser test sayisi kadar acilip kapanacakti..
                //@BeforeTest 'de butun hepsini  bir kerede tamamlayip,kapatiyor..
	@Parameters(value= {"browser"})
	public void setUp(String browser) throws InterruptedException {
		String browserName = null;
		basePage = new BasePage();
		prop = basePage.init_properties();
		
		if(browser.equals(null)) {
			browserName = prop.getProperty("browser");
		}else {
			browserName = browser;
		}
		
		browserName = prop.getProperty("browser");
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		log.info("url is launched" + prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		
		Thread.sleep(5000);
	}
	
	@Test(priority=1 , groups="sanity", description= "get page Title ", enabled=true )
	@Description("Verify Login Page Title")
	@Severity(SeverityLevel.NORMAL)
	public void verifyPageTitleTest() {
		log.info("starting------------------>>>>> verifyLoginPageTest");
	
		String title=loginPage.getPageTitle();
		System.out.println("login page title is "+ title);
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
	
		log.info("starting------------------>>>>> verifyLoginPageTest");
		log.warn("Some watning");
		log.error("some error");
		log.fatal("fatal error");
		
}
	
	@Test(priority=2  , description= "Thanks for choosing HubSpot", enabled=true)
	@Description("Verify Login Page Title")
	@Severity(SeverityLevel.NORMAL)
	public void verifySignUpLink(){
	Assert.assertTrue(loginPage.checkSignUpLink());
	}
	
	
	@Test( priority =3 ,description="invalid username and password for the login page", enabled=true)
	@Description("Verify Login Page Title")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
	HomePage homePage=loginPage.doLogin(userCred);
	//String accountName=homePage.getLoggedInUserAccountNAme();
//	System.out.println("logged in account name " + accountName);
//	Assert.assertEquals(accountName, prop.getProperty("accountname"));	
		
	
	}
	
	@DataProvider
	public Object[][] getLoginInvalidData(){
		
		Object data [][] = {{"bill@gmail.com", "test12345"},
							{"jimy@gmail.com", " "},
							{" ", "test12345"},
							{"yummy", "yummy"},
							{" ", " "}};
		return data;
	}
	@Test(priority=4, dataProvider="getLoginInvalidData", enabled=false)
	public void login_invalidTestCases(String username, String pwd){
		
		userCred.setAppUsername(username);
		userCred.setAppPassword(pwd);
		loginPage.doLogin(userCred);
		Assert.assertTrue(loginPage.checkLoginErrorMessage());
		
		
	}
	
   @AfterTest //@AfterMethod olsaydi browser test sayisi kadar acilip kapanacakti..
	       //@AfterTest 'de butun hepsini  bir kerede tamamlayip,kapatiyor..
	public void tearDown(){
		driver.quit();
	}
	
	

}