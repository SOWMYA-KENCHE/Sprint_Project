
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

public class System_TestCase_Email extends BaseClassCrm
{
	@Test(groups = "system")
	public void verifyEmailWithCCAndBCC() throws IOException, InterruptedException
	{
	    // Verify login
	    Home_Page home = new Home_Page(driver);
	    Assert.assertTrue(home.getsearch().isDisplayed());
	    Reporter.log("User logged in successfully", true);
	    // Read data from Excel
	    DataFromExcelFile file = new DataFromExcelFile();
	    String to = file.getDataFromExcel("Email", 1, 0);
	    String contactname = file.getDataFromExcel("Email", 1, 1);
	    String cc = file.getDataFromExcel("Email", 1, 2);
	    String bcc = file.getDataFromExcel("Email", 1, 3);
	    String subject = file.getDataFromExcel("Email", 1, 4);
	    String message = file.getDataFromExcel("Email", 1, 5);
	    // Open Email and Compose
	    home.getemaillink().click();
	    Email_Page emails = new Email_Page(driver);
	    String emailWindow = driver.getWindowHandle();
	    Set<String> oldWindows = driver.getWindowHandles();
	    emails.clickComposeEmail().click();
	    // Switch to Compose window
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
	    Email_Compose_Page compose = new Email_Compose_Page(driver);
	    // Open Contact lookup once
	    Set<String> composeWindows = driver.getWindowHandles();
	    compose.getcreateto().click();
	    // Switch to Contact lookup window
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
	    compose.selectContactSearch(to);
	    compose.selectContact(contactname);
	    // Return to Compose window
	    driver.switchTo().window(composeWindow);
	    compose.enterCC(cc);
	    compose.enterBCC(bcc);
	    compose.enterSubject(subject);
	    compose.enterMessage(message);
	    // Save email
	    compose.getsavebtn();
	    Set<String> openWindows = driver.getWindowHandles();
	    Assert.assertTrue(
	        openWindows.contains(emailWindow),"Original Emails window was closed unexpectedly"
	    );

	    driver.switchTo().window(emailWindow);
	    Email_Page emailsPage = new Email_Page(driver);
	    emailsPage.emailfind(subject);
	    boolean value = emails.getfoundfind(subject);
	    Assert.assertTrue(value);
	    home.getProfile().click();
	    home.getSignout().click();
	    Reporter.log("Email saved and verified successfully", true);
	}
}
