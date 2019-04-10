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
import java.util.Iterator;
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
	 
	  /**
	   * Fill out form fields.
	   **/
	  Helper.sendKeys(driver, driver.findElement(By.name("firstname")), 5, "Quang");
	  Helper.sendKeys(driver, driver.findElement(By.name("lastname")), 5, "Vo");
	  driver.findElement(By.id("sex-1")).click();
	  driver.findElement(By.id("exp-6")).click();
	  
	  Helper.sendKeys(driver, driver.findElement(By.id("datepicker")), 5, "12/31/1991");
	  
	  driver.findElement(By.id("profession-0")).click();
	  driver.findElement(By.id("profession-1")).click();
	  
	  //Tools
	  driver.findElement(By.id("tool-1")).click();
	  driver.findElement(By.id("tool-2")).click();
	  
	  //Continent
	  Select continents=new Select(driver.findElement(By.id("continents")));
	  continents.selectByVisibleText("North America");
	  
	  //Selenium Commands
	  Select seleniumCommands=new Select(driver.findElement(By.id("selenium_commands")));
	  seleniumCommands.selectByVisibleText("Browser Commands");
	  seleniumCommands.selectByIndex(1);
	  
	  /**
	   *  Prepare to print out the form data.
	   **/
	  
	  Thread.sleep(1000);
	 System.out.println(driver.findElement(By.name("firstname")).getText());
	 System.out.println(driver.findElement(By.name("lastname")).getText());
	 if (driver.findElement(By.id("sex-0")).isSelected()) {
		 System.out.println("Gender selected is Male");
	 } else if (driver.findElement(By.id("sex-1")).isSelected()) {
		 System.out.println("Gender selected is Female");
	 }
	 System.out.println("Date " +driver.findElement(By.id("datepicker")).getAttribute("value"));
	 
	 List<WebElement> experienceList=driver.findElements(By.name("exp"));
	 Iterator<WebElement> it1 = experienceList.iterator();
	 while(it1.hasNext()) {
		 WebElement radioBtn=it1.next();
		 if(radioBtn.isSelected()) {
			 System.out.println("Years of experience " + radioBtn.getAttribute("value"));
		 }
	     
	 }
	 
	 //Profession
	 String professionStr="Profession:  ";
	 List<WebElement> professionList=driver.findElements(By.name("profession"));
	 Iterator<WebElement> it2 = professionList.iterator();
	 while(it2.hasNext()) {
		 WebElement checkbox=it2.next();
		 if(checkbox.isSelected()) {
			 professionStr=professionStr + " " + checkbox.getAttribute("value");
		 }
	     
	 }
	 System.out.println(professionStr);
	 
	 // Tools
	 String toolStr="Tool:  ";
	 List<WebElement> toolList=driver.findElements(By.name("tool"));
	 Iterator<WebElement> it3 = toolList.iterator();
	 while(it3.hasNext()) {
		 WebElement checkbox=it3.next();
		 if(checkbox.isSelected()) {
			 toolStr=toolStr + " " + checkbox.getAttribute("value");
		 }
	     
	 }
	 
	 System.out.println(toolStr);
	 
	 //Continents
	 String selectedContinent = continents.getFirstSelectedOption().getText();
	 
	  System.out.println("Continent " +selectedContinent);
	 
	 //Selenium commands
	  String commandsStr="Selenium commands: ";
	  List<WebElement> allSelectedOptions=seleniumCommands.getAllSelectedOptions();
	  for (WebElement selectedOption : allSelectedOptions) {
		  commandsStr+=" "+selectedOption.getText();
          
	  }
	  System.out.println(commandsStr);
	  
	  

}

@AfterMethod
public void tearDown() throws Exception {
  driver.quit();
}

}
