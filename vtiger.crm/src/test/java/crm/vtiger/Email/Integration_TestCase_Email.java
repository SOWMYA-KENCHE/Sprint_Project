package crm.vtiger.Email;

import java.io.IOException;
import java.util.Set;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClassCrm;
import genericUtility.DataFromExcelFile;
import objRepo.Home_Page;
import objRepo.Email_Page;
import objRepo.Email_Compose_Page;

public class Integration_TestCase_Email extends BaseClassCrm
{
	 @Test(groups = "integration")
	    public void verifyContactIntegration()
	            throws IOException
	    {
		 	// Verify login
		    Home_Page home = new Home_Page(driver);
		    Assert.assertTrue(home.getsearch().isDisplayed());
		    Reporter.log("User logged in successfully", true);
	        DataFromExcelFile file = new DataFromExcelFile();
	        String contact = file.getDataFromExcel("Email", 1, 1);
	        String subject = file.getDataFromExcel("Email", 1, 4);
	        String message = file.getDataFromExcel("Email", 1, 5);
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
	        compose.selectto(contact);
	        compose.selectContact(contact);
	        // Return to Compose
	        driver.switchTo().window(composeWindow);
	        compose.enterSubject(subject);
	        compose.enterMessage(message);
	        // Save email without sending
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
   
}
