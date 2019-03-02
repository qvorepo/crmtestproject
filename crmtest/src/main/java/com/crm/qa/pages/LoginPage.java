package com.crm.qa.pages;

import static org.testng.Assert.assertTrue;

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
import com.crm.qa.common.Utility;

public class LoginPage extends TestBase {
	
	
	

	@FindBy(how = How.NAME, using = "username") WebElement username;
	//@FindBy(how = How.CSS, using = "#loginForm input") WebElement username;
	@FindBy(how = How.NAME, using = "password") WebElement password;
	
	@FindBy(how = How.CSS, using = ".input-group") WebElement inputGroupDiv;
	
	
	//@FindBy(how = How.XPATH, using = "//div[contains(text(), 'Cogmento CRM')]") WebElement chatBox;
	@FindBy(how = How.CSS, using = ".intercom-borderless-conversation-body") WebElement chatBox;
	//intercom-borderless-conversation-body
	@FindBy(how = How.CSS, using = ".intercom-borderless-dismiss-button span") WebElement closeChatBoxBtn;
	
	
	@FindBy(how = How.XPATH, using = "//input[@value='Login']") WebElement loginButton;
	
	
	public  LoginPage () {
		PageFactory.initElements(driver, this);
	}
	
	public HomePage login(String un, String pw) {
		
		closeChatBox();
		setUsername(un);
		setPassword(pw);
		clickOnLoginButton();
		
		return new HomePage();
		
	}
	
	public void setUsername(String strUsername) {
				
		Helper.sendKeys(driver, username, 5, strUsername);
	}
	//Sign In link text
	
	public void setPassword (String strPassword) {
		Helper.sendKeys(driver, password, 5, strPassword);
	}
	
	public void clickOnLoginButton () {
		Helper.clickOn(driver, loginButton, 5);
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	
	public void closeChatBox() {
		driver.switchTo().frame("intercom-borderless-frame");
		WebDriverWait wait=new WebDriverWait(driver,180);
		
		wait.until(ExpectedConditions.visibilityOf(chatBox));
		Actions action=new Actions(driver);
		action.moveToElement(chatBox).click().perform();
		closeChatBoxBtn.click();
	}
	


}
