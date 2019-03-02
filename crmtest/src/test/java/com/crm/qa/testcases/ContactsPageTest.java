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
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;


public class ContactsPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	TestUtil testUtil;
	
	public ContactsPageTest() {
		super();
	}

	
	  @BeforeMethod
	  public void setUp() throws Exception {
		  initialization();
			
		  	testUtil = new TestUtil();
		  	contactsPage=new ContactsPage();
			
		  	loginPage=new LoginPage();
			homePage=loginPage.login(prop.getProperty("Str_freecrm_username"), prop.getProperty("Str_freecrm_password"));
			testUtil.switchToFrame();
			contactsPage=homePage.clickOnContactsLink();
			
	  }
	  
	  
	  @Test(priority=1)
	  public void verifyContactsPageHeader() throws Exception{
		  contactsPage.verifyContactsPageHeader();
	  }
	  

	 
	  
	  @DataProvider ()
		public Iterator<Object[]> getTestData() {
			ArrayList<Object[]> testData=TestUtil.getNewContactTestDataFromExcel();
			return testData.iterator();
		}
		
		@Test(dataProvider="getTestData", priority=2)
		public void verifyCreateNewContacts(String title, String firstname, String lastname, String company) throws Exception{
			homePage.clickOnNewContactLink();
		}
		
		
//		 @Test(priority=3)
//		  public void verifyDeleteContactByNameTest1() throws Exception{
//			  contactsPage.deleteContactByName(prop.getProperty("Str_freecrm_contact_1"));
//		  }
//		 @Test(priority=4)
//		  public void verifyDeleteContactByNameTest2() throws Exception{
//			  contactsPage.deleteContactByName(prop.getProperty("Str_freecrm_contact_2"));
//		  }
//		 @Test(priority=5)
//		  public void verifyDeleteContactByNameTest3() throws Exception{
//			  contactsPage.deleteContactByName(prop.getProperty("Str_freecrm_contact_3"));
//		  }		
		

			  
	  
	  @AfterMethod
		public void tearDown(){
			driver.quit();
		}

}
