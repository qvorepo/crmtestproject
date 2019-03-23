package fileDownload;

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
		prefs.put("safebrowsing.enabled", "false");
		prefs.put("download.default_directory", folder.getAbsolutePath());
		
		options.setExperimentalOption("prefs", prefs);
		DesiredCapabilities cap=DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(cap);
		driver=new ChromeDriver(options);
		
	}
	
	@Test
	public void downloadFileTest() throws InterruptedException {
		//https://notepad-plus-plus.org/download/v7.6.4.html
		driver.get("https://notepad-plus-plus.org/download/v7.6.4.html");//Download link
		Thread.sleep(2000);
		
		//Link text Notepad++ Installer 64-bit x64
		driver.findElement(By.linkText("Notepad++ Installer 64-bit x64")).click();
		
		Thread.sleep(2000);
		File listOfFiles[] =folder.listFiles();
		//Make sure the directory is not empty.
		
		//Assert.assertEquals(listOfFiles.length, is(not(0)));// NOT CERTAIN WHY THIS LINE YELDS A FALSE RESULT AT RUNTIME.
		Assert.assertEquals(listOfFiles.length, 1);
		
		
		for(File file:listOfFiles) {
			//Make sure the down loaded file is not empty.
			//Assert.assertEquals(file.length(), is(not((long)0)));//THIS LINE SHOULD NOT BE COMMENTED OUT. 3/23/2019
			
			
			
		}
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
		for(File file : folder.listFiles()) {
			file.delete();
		}
		folder.delete();
		
	}

}
