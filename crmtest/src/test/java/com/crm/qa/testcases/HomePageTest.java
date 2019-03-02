package com.crm.qa.testcases;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.common.Helper;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;


public class HomePageTest extends TestBase {
	
	HomePage homePage;
	LoginPage loginPage;
	TestUtil testUtil;
	
	public HomePageTest() {
		super();
	}

	
	  @BeforeMethod
	  public void setUp() throws Exception {
		  initialization();
			
		  	testUtil = new TestUtil();
			loginPage=new LoginPage();
			
			homePage=loginPage.login(prop.getProperty("Str_freecrm_username"), prop.getProperty("Str_freecrm_password"));
			
	  }
	  
	  
	  @Test(priority=1)
	  public void verifyHomePageTitleTest() throws Exception{
		  Assert.assertEquals(homePage.getPageTitle(), prop.get("Str_freecrm_homepage_title"), "Homepage title does not match!");
	  }
	  
	  @Test (priority=2)
	  public void verifyUsernameLabelTest() {
		  testUtil.switchToFrame();
		 homePage.verifyUsernameLabel();
		  
	  }
	  @Test (priority=3)
	  public void verifyContactsLinkTest() {
		  testUtil.switchToFrame();
		  homePage.clickOnContactsLink();
	  }
	  
	  @Test (priority=4)
	  public void verifyDealsLinkTest() {
		  testUtil.switchToFrame();
		  homePage.clickOnDealsLink();
	  }
	  
	  
	  @AfterMethod
		public void tearDown(){
			driver.quit();
		}

}
