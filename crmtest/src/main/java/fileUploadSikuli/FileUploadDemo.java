package fileUploadSikuli;



import java.io.FileInputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FileUploadDemo {
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		
		//Chrome
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mamga\\Dropbox\\Tools\\Selenium\\Driver\\chromedriver.exe");
		ChromeOptions options=new ChromeOptions();
		driver=new ChromeDriver(options);
	
	}
	
	@Test

	public void functionName() throws FindFailed {
		
		//System.out.println("User Direcoty: " +System.getProperty("user.dir")) ;
		
		String userDirectory="";

		if (System.getProperty("user.dir").indexOf("target")==-1) {
			userDirectory=System.getProperty("user.dir");
			//FileInputStream ip = new FileInputStream(System.getProperty("user.dir") +"\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
		} else {
			userDirectory=System.getProperty("user.dir").substring(0, System.getProperty("user.dir").indexOf("target"));
			//FileInputStream ip = new FileInputStream(System.getProperty("user.dir") +"\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			System.out.println("User Direcoty with substring: " +userDirectory) ;
		}
		//FileInputStream ip = new FileInputStream(userDirectory +"\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
		
		System.out.println("User Direcoty: " +System.getProperty("user.dir")) ;

		String filepath = userDirectory +"src\\main\\java\\com\\crm\\qa\\testdata\\";
        String inputFilePath = userDirectory +"src\\main\\java\\com\\crm\\qa\\testdata\\";
        Screen screen = new Screen();
        Pattern fileInputTextBox = new Pattern(filepath + "Sikuli_FileInputTextBox.png");
        Pattern openBtn = new Pattern(filepath + "Sikuli_OpenBtn.png");

        driver.get("http://demo.guru99.com/test/image_upload/index.php");
        
        //https://uploadfiles.io/


    // Click on Browse button and handle windows pop up using Sikuli
    driver.findElement(By.xpath(".//*[@id='photoimg']")).click();
    screen.wait(fileInputTextBox, 20);
    screen.type(fileInputTextBox, inputFilePath + "Test.txt");
    screen.click(openBtn);

    // Close the browser
    driver.close();

	  }

}
