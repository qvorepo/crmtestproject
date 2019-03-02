package com.crm.qa.testcases;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.common.Helper;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;


public class DealsPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	DealsPage dealsPage;
	TestUtil testUtil;
	
	public DealsPageTest() {
		super();
	}

	
	  @BeforeMethod
	  public void setUp() throws Exception {
		  initialization();
			
		  	testUtil = new TestUtil();
		  	dealsPage=new DealsPage();
			
		  	loginPage=new LoginPage();
			homePage=loginPage.login(prop.getProperty("Str_freecrm_username"), prop.getProperty("Str_freecrm_password"));
			testUtil.switchToFrame();
			dealsPage=homePage.clickOnDealsLink();
			
	  }
	  
	  
	  @Test(priority=1)
	  public void verifyDealsPageHeader() throws Exception{
		  dealsPage.verifyNewDealsPageHeader();
	  }
	  
	  @DataProvider
		public Iterator<Object[]> getTestData() {
			ArrayList<Object[]> testData=TestUtil.getNewDealTestDataFromExcel();
			return testData.iterator();
		}
		
		@Test(dataProvider="getTestData")
		public void verifyCreateNewDeals(String title, String company, String primarycontact, String amount) throws Exception{
			//homePage.clickOnDealsLink();
			homePage.clickOnNewDealLink();
			dealsPage.createNewDeals(title, company, primarycontact, amount);
			
		}	  
	  
	  @AfterMethod
		public void tearDown(){
			driver.quit();
		}

}
