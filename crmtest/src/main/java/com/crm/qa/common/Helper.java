package com.crm.qa.common;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Properties;
import java.lang.Thread;


public class Helper {
	
	public static Properties prop;
	public static WebDriver driver;
	public static int HOVER_RETRY_COUNT=10;
	
	public void login (WebDriver driver, String sUsername, String sPassword) {
		
		driver.findElement(By.linkText(prop.getProperty("Str_LogIn"))).click();//Login btn click
		  
		driver.findElement(By.id(prop.getProperty("Txt_Login_Username"))).clear();//Txt_Login_Username
		driver.findElement(By.id(prop.getProperty("Txt_Login_Username"))).sendKeys(sUsername);
		  
			
		driver.findElement(By.id(prop.getProperty("Txt_Login_Password"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_Login_Password"))).sendKeys(sPassword);
	    
		//driver.findElement(By.xpath("//button[contains(.,'Log In')]")).click();
		driver.findElement(By.cssSelector(prop.getProperty("Str_LoginModal_LogIn_Selector"))).click();//Log In Button on the modal
	    	
	}
	
	/**
	 * Source:  https://www.youtube.com/watch?v=hRjVVnmmcCc&t=1163s
	 * */

	public static void sendKeys (WebDriver driver, By by, int timeout, String value) {
		new WebDriverWait (driver, timeout).until(ExpectedConditions.visibilityOf(driver.findElement(by)));
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(value);
	}
	
	public static void switchFrame(String framename) {
		driver.switchTo().frame(framename);
	}
	
	public static void sendKeys (WebDriver driver, WebElement element, int timeout, String value) {
		new WebDriverWait (driver, timeout).until(ExpectedConditions.visibilityOf(element));
		element.clear();
		element.sendKeys(value);
	}

	/**
	 * Source:  https://www.youtube.com/watch?v=hRjVVnmmcCc&t=1163s
	 * */
	public static void clickOn(WebDriver driver, By by, int timeout) {
		new WebDriverWait(driver,timeout).until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));
		driver.findElement(by).click();
	}	
	
	public static void clickOn(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver,timeout).until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	public static void waitForElementPresent(WebDriver driver, By by, int iTimeOut) throws InterruptedException{
		int iTotal=0;
		int iSleepTime=5000;
		while (iTotal<iTimeOut){
			
			List<WebElement> oWebElements=driver.findElements(by);
			if(oWebElements.size()>0) {
				return;
			} else {
				Thread.sleep(iSleepTime);
				iTotal+=iSleepTime;
				System.out.println(String.format("Waited for %d milliseconds.[%s]", iTotal, by));
			}
		}
		
	}
	
	public static boolean isTextPresent(WebDriver driver, String text) {
		try{
	        boolean b = driver.getPageSource().contains(text);
	        return b;
	    }
	    catch(Exception e){
	        return false;
	    }
		
	}
	
	public boolean isConnectionSecured(WebDriver driver) {
		return !isTextPresent(driver, prop.getProperty("Str_YourConnectionIsNotPrivate"));
		
	}
	
	
	public void sleep(int seconds) 
	{
	    try {
	        Thread.sleep(seconds * 1000);
	    } catch (InterruptedException e) {

	    }
	}
	
	public String getCreditCardType (String cardNbr) {
		String creditCardType="Credit card type: ";
		if (cardNbr !=null && cardNbr.length()>0) {
			cardNbr=cardNbr.trim().replaceAll("\\s","");// Remove all white spaces
			if (cardNbr.equals("4111111111111111")) {
				creditCardType+="Visa";
			} else if (cardNbr.equals("5555555555554444")) {
				creditCardType+="MasterCard";
			} else if (cardNbr.equals("370000000000002") || cardNbr.equals("378282246310005")) {
				creditCardType+="American Express";
			} else if (cardNbr.equals("6011000990139424")) {
				creditCardType+="Discover";
			} else {
				creditCardType+="Unknown";
			}
		}
		return creditCardType;
	}
	


	

}
