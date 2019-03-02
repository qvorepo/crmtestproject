package com.crm.qa.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.crm.qa.base.TestBase;
import com.crm.qa.common.Helper;
import com.crm.qa.common.Utility;

public class DealsPage extends TestBase {
	

	//@FindBy(how = How.XPATH, using = "//td[contains(text(), 'Contacts')]") WebElement contactsLabel;

	@FindBy(how = How.NAME, using = "title") WebElement title;
	@FindBy(how = How.NAME, using = "client_lookup") WebElement company;
	@FindBy(how = How.NAME, using = "contact_lookup") WebElement primarycontact;
	@FindBy(how = How.NAME, using = "amount") WebElement amount;
	@FindBy(how = How.XPATH, using = "//input[@type='submit'][@value='Save']") WebElement saveBtn;

	
	
	public DealsPage () {
		PageFactory.initElements(driver, this);
		
	}
	
	
	public boolean verifyNewDealsPageHeader() {
		return Helper.isTextPresent(driver, prop.getProperty("Str_freecrm_newdeals_pageheader"));
	}
	

	
	public void createNewDeals(String strTitle, String comp, String primaryContact, String amt) {
				
		Helper.sendKeys(driver, title, 5, strTitle);
		Helper.sendKeys(driver, company, 5, comp);
		Helper.sendKeys(driver, primarycontact, 5, primaryContact);
		Helper.sendKeys(driver, amount, 5, amt);
		Helper.clickOn(driver, saveBtn, 7);
	}
	
	
	
//	public void clickOnContactByName (String strName) {
//		driver.findElement(By.linkText(strName)).click();
//		
//		WebDriverWait wait=new WebDriverWait(driver,30);
//		wait.until(ExpectedConditions.visibilityOf(AllLabel));
//		verifyContactPhone();
//		
//	}
	
	


}
