package com.crm.qa.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.io.File;
import java.util.Properties;


public class DriverFactory {

	public enum BrowserType{

			FIREFOX,
			CHROME,
			IE,
			PHANTOMJS

		}

public static WebDriver getDriver(BrowserType type, Properties prop){
	
	WebDriver driver=null;
	
	switch(type){
	
	//phantomjs.exe
	case PHANTOMJS:

		 File file = new File("C:\\Users\\Mamga\\Dropbox\\Tools\\Selenium\\phantomjs.exe");
	        System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
	       // driver = new PhantomJSDriver();
		
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		
		break;
	
	case FIREFOX:
	
		//System.setProperty("webdriver.firefox.bin", prop.getProperty("Str_Firefox_Exe_Path"));
		System.setProperty("webdriver.gecko.driver",prop.getProperty("Str_FirefoxDriver_Path"));
		
		driver=new FirefoxDriver();
	
		
		break;
	
	case CHROME:
	
		File chromeDriver = new File(prop.getProperty("Str_ChromeDriver_Path"));
		System.setProperty("webdriver.chrome.bin", prop.getProperty("Str_Chrome_Exe_Path"));
		System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
		
		ChromeOptions options = new ChromeOptions();
		
		
		//options.addArguments("window-size=1400,800");//Mandatory window size for Jenkins 1400, 800
		options.addArguments("window-size=1600,1060");
		options.addArguments("--start-maximized");
		//options.addArguments("headless");// Comment this line for ADMIN suite, because hover function does not work with the Chrome headless browser.
		//options.addArguments("disable-infobars");
		//options.addArguments("-incognito");
		driver=new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
	
		break;
		
		
	case IE:
		
		File ieDriver= new File(prop.getProperty("Str_IEDriver_Path"));
		//System.setProperty("webdriver.ie.driver", "C:\\Program Files (x86)\\Internet Explorer\\iexplore.exe");
		//System.setProperty("webdriver.ie.bin", "C:\\Program Files (x86)\\Internet Explorer\\iexplore.exe");
		System.setProperty("webdriver.ie.driver", ieDriver.getAbsolutePath());
		driver = new InternetExplorerDriver();	
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		break;

	default:
	
		driver=new ChromeDriver();
	
		break;
	}
	return driver;


}

}