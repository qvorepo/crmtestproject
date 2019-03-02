package com.crm.qa.testcases;


import java.util.ArrayList;
import java.util.Iterator;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;

import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;


public class LoginPageDataDrivenTest extends TestBase {
	
	HomePage homePage;
	LoginPage loginPage;
	
	LoginPageDataDrivenTest(){
		super();
	}
	
	  @BeforeMethod
	  public void setUp() throws Exception {
		  initialization();
			
			loginPage=new LoginPage();
			
	  }
	  
		@DataProvider
		public Iterator<Object[]> getTestData() {
			ArrayList<Object[]> testData=TestUtil.getDataFromExcel();
			return testData.iterator();
		}
		
		  @Test(dataProvider="getTestData")
		  public void loginToFreeCRM(String username, String password) throws Exception{

			  homePage=loginPage.login(username,password);
		   
		  }
	  
	  @AfterMethod
		public void tearDown(){
			driver.quit();
		}

}
