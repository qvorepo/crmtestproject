package com.crm.qa.pages;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.crm.qa.base.TestBase;
import com.crm.qa.common.Helper;
import com.crm.qa.common.Utility;

public class DealsPage extends TestBase {
	

	//@FindBy(how = How.XPATH, using = "//td[contains(text(), 'Contacts')]") WebElement contactsLabel;

	@FindBy(how = How.NAME, using = "title") WebElement title;
	@FindBy(how = How.NAME, using = "client_lookup") WebElement company;
	@FindBy(how = How.NAME, using = "contact_lookup") WebElement primarycontact;
	@FindBy(how = How.NAME, using = "amount") WebElement amount;
	@FindBy(how = How.XPATH, using = "//input[@type='submit'][@value='Save']") WebElement saveBtn;
	@FindBy(how = How.CSS, using = ".datacard tr") List<WebElement> dealsRowList;

	
	
	public DealsPage () {
		PageFactory.initElements(driver, this);
		
	}
	
	
	public boolean verifyNewDealsPageHeader() {
		return Helper.isTextPresent(driver, prop.getProperty("Str_freecrm_newdeals_pageheader"));
	}
	

	
	public void createNewDeals(String strTitle, String comp, String primaryContact, String amt) {
				
		Helper.sendKeys(driver, title, 5, strTitle);
		Helper.sendKeys(driver, company, 5, comp);
		Helper.sendKeys(driver, primarycontact, 5, primaryContact);
		Helper.sendKeys(driver, amount, 5, amt);
		Helper.clickOn(driver, saveBtn, 7);
	}
	
	public void getDealsRows () {
		
		List<WebElement> columnsList = null;
		for (WebElement row : dealsRowList) {
			//System.out.println();
            columnsList = row.findElements(By.tagName("td"));
            
            for (WebElement column : columnsList) {
            	if (column.getText().contentEquals("$10 Hair Cut ")) {
            		 System.out.print(column.getText() + ", ");
            	}
            		
            	 
            }
            	
            }
		}
			
		
         public void extractDealData() {
 			//count rows
 			List<WebElement> Rows = driver.findElements(By.cssSelector("table.datacard:nth-child(2) tr"));
 			int totalRows = Rows.size();
 			System.out.println(" Total rows : "+totalRows);
 			
 			//count columns
 			//List<WebElement> Columns = driver.findElements(By.cssSelector("table.datacard:nth-child(2) tr:nth-child(1) td"));
 			List<WebElement> Columns = driver.findElements(By.xpath("//table[@class='datacard'][2]/tbody/tr[2]/td"));//Second table, second row
 			int totalColumns = Columns.size();
 			System.out.println(" Total Columns : "+totalColumns);
 			
 			//Extract data
			for(int i=1;i<totalRows;i++){
				for(int j=1;j<totalColumns;j++){
					//Skip the first row.
					if(i>1 && i<(totalRows-1) && j<(totalColumns-1)) {
						//WebElement dataCell = driver.findElement(By.xpath("//div[@class='su-table']/table/tbody/tr["+i+"]/td["+j+"]"));
						WebElement dataCell = driver.findElement(By.xpath("//table[@class='datacard'][2]/tbody/tr["+i+"]/td["+j+"]"));
						System.out.println(dataCell.getText());
						
						
//						if(!dataCell.getText().equalsIgnoreCase("10001") && !dataCell.getText().equalsIgnoreCase("10002") && !dataCell.getText().equalsIgnoreCase("10003") && !dataCell.getText().equalsIgnoreCase("10009")) {
//							WebElement deleteIcon = driver.findElement(By.xpath("//table[@class='datacard'][2]/tbody/tr["+i+"]/td["+j+"]/a[3]"));
//							System.out.println(i+"-"+"-"+ j+"deleteIcon found.");
//						}
						
						
					}
					
				}
			}
         }
	
	
	
//	public void clickOnContactByName (String strName) {
//		driver.findElement(By.linkText(strName)).click();
//		
//		WebDriverWait wait=new WebDriverWait(driver,30);
//		wait.until(ExpectedConditions.visibilityOf(AllLabel));
//		verifyContactPhone();
//		
//	}
	
	


}
