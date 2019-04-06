package com.crm.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public TestBase(){
		try {
			prop = new Properties();
			//FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/crm"
					//+ "/qa/config/config.properties");
			
			String userDirectory="";

			if (System.getProperty("user.dir").indexOf("target")==-1) {
				userDirectory=System.getProperty("user.dir");
				//FileInputStream ip = new FileInputStream(System.getProperty("user.dir") +"\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			} else {
				userDirectory=System.getProperty("user.dir").substring(0, System.getProperty("user.dir").indexOf("target"));
				//FileInputStream ip = new FileInputStream(System.getProperty("user.dir") +"\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
				System.out.println("User Direcoty with substring: " +userDirectory) ;
			}
			FileInputStream ip = new FileInputStream(userDirectory +"\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			
			System.out.println("User Direcoty: " +System.getProperty("user.dir")) ;
			
			
			//FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/crm""/src/main/java/com/crm/qa/config/config.properties");
			
			//./properties/config.properties
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void initialization(){
		String browserName = prop.getProperty("browser");
		
		
		if(browserName.equalsIgnoreCase("chrome")){
			File chromeDriver = new File(prop.getProperty("Str_ChromeDriver_Path"));
			System.setProperty("webdriver.chrome.bin", prop.getProperty("Str_Chrome_Exe_Path"));
			System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
			
			ChromeOptions options = new ChromeOptions();
			
			
			//options.addArguments("window-size=1400,800");//Mandatory window size for Jenkins 1400, 800
			//options.addArguments("window-size=1600,1060");
			options.addArguments("--start-maximized");
			//options.addArguments("headless");// Comment this line for ADMIN suite, because hover function does not work with the Chrome headless browser.
			//options.addArguments("disable-infobars");
			//options.addArguments("-incognito");
			driver=new ChromeDriver(options);
			driver.manage().deleteAllCookies();
			driver.navigate().refresh();
		}
		else if(browserName.equalsIgnoreCase("firefox")){
			
			System.setProperty("webdriver.gecko.driver",prop.getProperty("Str_FirefoxDriver_Path"));
			driver=new FirefoxDriver();
		}
		
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("Str_freecrm_landingpage_url"));
		
	}
	
	
	
	
	
	
	
	

}
