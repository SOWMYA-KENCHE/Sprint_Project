package crm.vtiger.Email;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClassCrm;
import objRepo.Home_Page;
import objRepo.Email_Page;
import objRepo.Email_Compose_Page;

public class Smoke_TestCase_Email extends BaseClassCrm
{
    @Test(groups = "smoke")
    public void verifyComposePageControls() throws InterruptedException
    {
        // Verify login
    	Thread.sleep(1000);
        Home_Page home = new Home_Page(driver);
        Assert.assertTrue(home.getsearch().isDisplayed());
        Reporter.log("User logged in successfully", true);
        // Open Email module
        home.getemaillink().click();
        Email_Page emails = new Email_Page(driver);
        emails.clickComposeEmail().click();
      // Open Email and Compose
        String parent = driver.getWindowHandle();
        emails.clickComposeEmail();
        for (String window : driver.getWindowHandles())
        {
            if (!window.equals(parent))
            {
                driver.switchTo().window(window);
                break;
            }
        }
        // Verify Compose form
        Email_Compose_Page compose = new Email_Compose_Page(driver);
        Assert.assertTrue(compose.isToDisplayed(), "To field not displayed");
        Assert.assertTrue(compose.isCCDisplayed(), "CC field not displayed");
        Assert.assertTrue(compose.isBCCDisplayed(), "BCC field not displayed");
        Assert.assertTrue(compose.isSubjectDisplayed(), "Subject field not displayed");
        Assert.assertTrue(compose.isAttachmentDisplayed(), "Attachment option not displayed");
        Assert.assertTrue(compose.isSendDisplayed(), "Send button not displayed");
        Reporter.log("Compose controls are displayed", true);
        Thread.sleep(1000);
    }
}