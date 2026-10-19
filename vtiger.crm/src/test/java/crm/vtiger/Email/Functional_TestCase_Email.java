
package crm.vtiger.Email;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClassCrm;
import genericUtility.DataFromExcelFile;
import objRepo.Home_Page;
import objRepo.Email_Page;
import objRepo.Email_Compose_Page;

public class Functional_TestCase_Email extends BaseClassCrm
{

	@Test(groups = "functional")
	public void verifyEmailpage() throws IOException, InterruptedException
	{
	    // Verify login
	    Home_Page home = new Home_Page(driver);
	    Assert.assertTrue(home.getsearch().isDisplayed());
	    Reporter.log("User logged in successfully", true);
	    // verify page
	    Email_Page email = new Email_Page(driver);
	    home.getemaillink().click();
	    Assert.assertTrue(email.clickComposeEmail().isDisplayed(),"Not displayed");
	    Reporter.log("Emailpage is displayed successfully", true);
	}

}
