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

public class ContactsPage extends TestBase {
	

	@FindBy(how = How.XPATH, using = "//td[contains(text(), 'Contacts')]") WebElement contactsLabel;
	@FindBy(how = How.XPATH, using = "//span[contains(text(), '360-745-0890')]") WebElement YenPhoneNumber;
	@FindBy(how = How.NAME, using = "by_user") WebElement ownerDropDownList;
	@FindBy(how = How.ID, using = "first_name") WebElement first_name;
	@FindBy(how = How.ID, using = "surname") WebElement surname;
	@FindBy(how = How.NAME, using = "client_lookup") WebElement company;
	
	@FindBy(how = How.XPATH, using = "//input[@type='submit'][@value='Save']") WebElement saveBtn;
	@FindBy(how = How.XPATH, using = "//input[@type='button'][@value='Delete']") WebElement deleteBtn;
	
	
	public ContactsPage () {
		PageFactory.initElements(driver, this);
		
	}
	
	public boolean verifyContactsPageHeader() {
		return contactsLabel.isDisplayed();
	}
	
	public boolean verifyContactPhone() {
		return YenPhoneNumber.isDisplayed();
	}
	
	public boolean verifyOwnerDropDownListDisplay() {
		return ownerDropDownList.isDisplayed();
	}
	

	
	public void createNewContact(String title, String firstName, String lastName, String comp) {
		Select select =new Select(driver.findElement(By.name("title")));
		select.selectByVisibleText(title);
		
		Helper.sendKeys(driver, first_name, 5, firstName);
		Helper.sendKeys(driver, surname, 5, lastName);
		Helper.sendKeys(driver, company, 5, comp);
		Helper.clickOn(driver, saveBtn, 7);
	}
//	public void selectContactsByName(String name){
//		driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td[@class='datalistrow']"
//				+ "//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']")).click();
//	}
	
	public void deleteContactByName(String name) {
		driver.findElement(By.linkText(name)).click();
		WebDriverWait wait=new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.elementToBeClickable(deleteBtn));
		Helper.clickOn(driver, deleteBtn, 7);
		driver.switchTo().alert().accept();
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
