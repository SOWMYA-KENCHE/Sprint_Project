package crm.vtiger.Email;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseClassCrm;
import genericUtility.DataFromExcelFile;
import objRepo.Home_Page;
import objRepo.Email_Page;
import objRepo.Email_Compose_Page;

public class Integration_TestCase_Email extends BaseClassCrm
{
	 @Test(dataProvider ="emailsaves",groups = "integartion")
	 public void verifyContactIntegration(String contact,String contactname,String toEmail,String ccEmail,
		        String subject,
		        String message,
		        String additionalField)
	            throws IOException, InterruptedException
	    {
		 	// Verify login
		    Home_Page home = new Home_Page(driver);
		    Assert.assertTrue(home.getsearch().isDisplayed());
		    Reporter.log("User logged in successfully", true);
	        // Store original Emails window
	        String emailWindow = driver.getWindowHandle();
	        home.getemaillink().click();
	        Email_Page emails = new Email_Page(driver);
	        // Open Compose window
	        Set<String> oldWindows = driver.getWindowHandles();
	        emails.clickComposeEmail().click();
	        String composeWindow = null;
	        for (String window : driver.getWindowHandles())
	        {
	            if (!oldWindows.contains(window))
	            {
	                composeWindow = window;
	                driver.switchTo().window(window);
	                break;
	            }
	        }
	        Assert.assertNotNull(composeWindow, "Compose window did not open");
	        Email_Compose_Page compose = new Email_Compose_Page(driver);
	        // Open Contact lookup
	        Set<String> composeWindows = driver.getWindowHandles();
	        compose.getcreateto().click();
	        String lookupWindow = null;
	        for (String window : driver.getWindowHandles())
	        {
	            if (!composeWindows.contains(window))
	            {
	                lookupWindow = window;
	                driver.switchTo().window(window);
	                break;
	            }
	        }
	        Assert.assertNotNull(lookupWindow, "Contact lookup window did not open");
	        compose.selectContactSearch(contact);
	        compose.selectContact(contactname);
	        // Return to Compose
	        driver.switchTo().window(composeWindow);
	        compose.enterSubject(subject);
	        compose.enterMessage(message);
	        compose.getsavebtn();
	        // Return to original window
	        Set<String> openWindows = driver.getWindowHandles();
	        Assert.assertTrue(openWindows.contains(emailWindow),"Original Emails window was closed");
	        driver.switchTo().window(emailWindow);
	        // Navigate to To Contacts
	        emails = new Email_Page(driver);
	        emails.openToContacts();
	        // Search 
	        emails.emailfind(subject);
	        Assert.assertTrue(emails.getfoundfindvalue(subject).isDisplayed(),"Saved email was not found in To Contacts");
	        Reporter.log("Contact integration verified",true);
	    }
	 
	    @DataProvider
	    public Object[][] emailsaves() throws EncryptedDocumentException, IOException
	    {
	    	FileInputStream file = new FileInputStream("./src/test/resources/testcase.xlsx");
	    	
	    	Workbook wb = WorkbookFactory.create(file);
	    	Sheet sh = wb.getSheet("Email");
	    	int rowcount =sh.getLastRowNum();
	    	int cellcount = sh.getRow(0).getLastCellNum();
	    	Object[][] obj = new Object[rowcount][cellcount];
	    	DataFormatter df = new DataFormatter();
	    	for(int i=1;i<=rowcount;i++)
	    	{
	    		for(int j=0;j<cellcount;j++)
	    		{
	    			obj[i-1][j] = df.formatCellValue(sh.getRow(i).getCell(j));
	    		}
	    	}
	    	wb.close();
	    	file.close();
	    	return obj;
	    }
   
}
