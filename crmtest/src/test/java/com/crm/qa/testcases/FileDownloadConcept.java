package com.crm.qa.testcases;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;



public class FileDownloadConcept {
	WebDriver driver;
	File folder;
	
	@BeforeMethod
	public void setup() {
		folder=new File(UUID.randomUUID().toString());
		folder.mkdir();
		
		//Chrome
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mamga\\Dropbox\\Tools\\Selenium\\Driver\\chromedriver.exe");
		ChromeOptions options=new ChromeOptions();
		Map<String, Object> prefs=new HashMap<String,Object>();
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("safebrowsing.enabled", false);
		
		prefs.put("download.default_directory", folder.getAbsolutePath());
		
		options.setExperimentalOption("prefs", prefs);
		DesiredCapabilities cap=DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		
		driver=new ChromeDriver(cap);
		
	}
	
	@Test
	public void downloadFileTest() throws InterruptedException {
		driver.get("https://www.openoffice.org/download/");//Download link
		Thread.sleep(2000);
		driver.findElement(By.linkText("Download full installation")).click();
		
		//Thread.sleep(2000);
		
		
		//driver.findElement(By.linkText("Notepad++ Installer 64-bit x64")).click();
		//Wait for 2 seconds to download the file
		Thread.sleep(10000);
		File listOfFiles[] =folder.listFiles();
		//Make sure the directory is not empty.
		Assert.assertEquals(listOfFiles.length, is(not(0)));
		
		for(File file:listOfFiles) {
			//Make sure the downloaded file is not empty.
			Assert.assertEquals(file.length(), is(not((long)0)));
			
		}
	}
	
//	@AfterMethod
//	public void tearDown() {
//		driver.quit();
//		for(File file : folder.listFiles()) {
//			file.delete();
//		}
//		folder.delete();
//		
//	}

}
