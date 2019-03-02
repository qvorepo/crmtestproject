package com.crm.qa.testcases;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.common.DriverFactory;
import com.crm.qa.common.Helper;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;


public class LoginPageTest extends TestBase {
	
	HomePage homePage;
	LoginPage loginPage;
	
	LoginPageTest(){
		super();
	}
	
	  @BeforeMethod
	  public void setUp() throws Exception {
		  initialization();
			
			loginPage=new LoginPage();
			
	  }
	  	  

	  @Test(priority=1)
	  public void verifyLoginPageTitleTest() throws Exception{
		  
		  Assert.assertEquals(loginPage.getPageTitle(), prop.getProperty("Str_freecrm_landingpage_title"), "Login title does not match!");
	 
	  }
	  
	  @Test (priority=2)
	  public void loginTest() {
		  //loginPage.closeChatBox();
		  homePage=loginPage.login(prop.getProperty("Str_freecrm_username"), prop.getProperty("Str_freecrm_password"));
		  
	  }
	  
	  @AfterMethod
		public void tearDown(){
			driver.quit();
		}

}
