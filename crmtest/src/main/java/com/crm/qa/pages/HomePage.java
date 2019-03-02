package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.crm.qa.base.TestBase;
import com.crm.qa.common.Helper;

public class HomePage extends TestBase {
	

	
	@FindBy(how = How.XPATH, using = "//a[contains(text(), 'Contacts')]") WebElement contactsLink;
	@FindBy(how = How.XPATH, using = "//a[contains(text(), 'Deals')]") WebElement dealsLink;
	
	
	@FindBy(how = How.XPATH, using = "//td[contains(text(), 'User: Quang Vo')]") WebElement usernameLabel;
	@FindBy(how = How.LINK_TEXT, using = "New Contact") WebElement NewContactLink;
	@FindBy(how = How.LINK_TEXT, using = "New Deal") WebElement NewDealLink;
	
	
	
	
	// Initializing the Page Objects:
	public HomePage () {
		PageFactory.initElements(driver, this);
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}

	public ContactsPage clickOnContactsLink() {
		Helper.clickOn(driver, contactsLink, 5);
		return new ContactsPage();
		
	}
	
	public boolean verifyUsernameLabel() {
		return usernameLabel.isDisplayed();
	}
	
	public void clickOnNewContactLink() {
		Actions action=new Actions(driver);
		action.moveToElement(contactsLink).build().perform();
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(NewContactLink));
		Helper.clickOn(driver,NewContactLink, 5);
		
	}
	
	public void clickOnNewDealLink() {
		Actions action=new Actions(driver);
		action.moveToElement(dealsLink).build().perform();
		//WebDriverWait wait=new WebDriverWait(driver,10);
		//wait.until(ExpectedConditions.visibilityOf(NewDealLink));
		Helper.clickOn(driver,NewDealLink, 5);
		
	}
	
	public DealsPage clickOnDealsLink() {
		Helper.clickOn(driver, dealsLink, 5);
		return new DealsPage();
		
	}
	


}
