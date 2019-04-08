package com.crm.qa.poc;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.common.Helper;

import org.testng.Assert;
import org.testng.Reporter;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.*;
import static org.testng.Assert.fail;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebForm_DataEntry_Demo extends TestBase {
	

	
	  @BeforeMethod
	  
	   
	  public void setUp() throws Exception {
			
			//Chrome
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mamga\\Dropbox\\Tools\\Selenium\\Driver\\chromedriver.exe");
			ChromeOptions options=new ChromeOptions();
			driver=new ChromeDriver(options);
			
			
		
		
	   //baseUrl ="https://www.hy-vee.com/grocery/login/?retUrl=%2fgrocery%2f";
	    
	    
	    driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	  }

@Test
public void login() throws Exception {
	  driver.get("https://www.toolsqa.com/automation-practice-form/");//https://www.toolsqa.com/automation-practice-form/
	  Thread.sleep(1000);
	 
	  Helper.sendKeys(driver, driver.findElement(By.name("firstname")), 5, "Quang");
	  Helper.sendKeys(driver, driver.findElement(By.name("lastname")), 5, "Vo");
	  driver.findElement(By.id("sex-1")).click();
	  driver.findElement(By.id("exp-6")).click();
	  
	  Helper.sendKeys(driver, driver.findElement(By.id("datepicker")), 5, "12/31/1991");
	  
	  
	  Thread.sleep(1000);
	 System.out.println(driver.findElement(By.name("firstname")).getText());
	 System.out.println(driver.findElement(By.name("lastname")).getText());
	 if (driver.findElement(By.id("sex-0")).isSelected()) {
		 System.out.println("Gender selected is Male");
	 } else if (driver.findElement(By.id("sex-1")).isSelected()) {
		 System.out.println("Gender selected is Female");
	 }
	 System.out.println("Date " +driver.findElement(By.id("datepicker")).getAttribute("value"));
	 
	 String exp="Years of experience ";
	 if (driver.findElement(By.id("exp-0")).isSelected()) {
		 System.out.println(exp + "1");
	 } else if (driver.findElement(By.id("exp-1")).isSelected()) {
		 System.out.println(exp + "2");
	 } else if (driver.findElement(By.id("exp-2")).isSelected()) {
		 System.out.println(exp + "3");
	 } else if (driver.findElement(By.id("exp-3")).isSelected()) {
		 System.out.println(exp + "4");
	 } else if (driver.findElement(By.id("exp-4")).isSelected()) {
		 System.out.println(exp + "5");
	 } else if (driver.findElement(By.id("exp-5")).isSelected()) {
		 System.out.println(exp + "6");
	 } else if (driver.findElement(By.id("exp-6")).isSelected()) {
		 System.out.println(exp + "7");
	 }
	
	 
	 
	  
	  

}

@AfterMethod
public void tearDown() throws Exception {
  driver.quit();
}

}
