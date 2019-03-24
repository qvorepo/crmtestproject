package fileDownload;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.not;

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
		
		//Firefox, has not been able to get the test to pass in Firefox. 3/23/2019
//		FirefoxProfile profile = new FirefoxProfile();
//		
//		profile.setPreference("browser.download.dir", folder.getAbsolutePath());
//		profile.setPreference("browser.download.folderList", folder.getAbsolutePath());
//		
//		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "image/JPEG, application/pdf, application/octet-stream");
//		profile.setPreference("pdfjs.disabled", "true");
//		
//		System.setProperty("webdriver.gecko.driver","C:\\Users\\Mamga\\Dropbox\\Tools\\Selenium\\Driver\\geckodriver.exe");
//		
//		FirefoxOptions option=new FirefoxOptions();
//		option.setProfile(profile);
//		driver= new FirefoxDriver(option);
		
		
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
		assertThat(listOfFiles.length , is(not(0)));
		
		
		for(File file:listOfFiles) {
			//Make sure the down loaded file is not empty.
			assertThat(file.length() ,  is(not((long)0)));
			
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
