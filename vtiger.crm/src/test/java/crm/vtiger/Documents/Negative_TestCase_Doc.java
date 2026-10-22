package crm.vtiger.Documents;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClassCrm;
import genericUtility.DataFromExcelFile;
import genericUtility.ScreenShotUtility1;
import objRepo.Documents_Page;
import objRepo.Home_Page;

public class Negative_TestCase_Doc extends BaseClassCrm
{
	@Test(groups = {"negative","functional"} )
	public void uploadOversizedFile() throws IOException 
	{
		
		// Verify login
        Home_Page home = new Home_Page(driver);
        Assert.assertTrue(home.getsearch().isDisplayed());
        Reporter.log("User is successfully logged in with valid credentials",true);
	    String filePath = System.getProperty("user.dir")+ "/src/test/resources/big_file.pdf";
	    home.getdocumentslink().click();
	    Documents_Page docs = new Documents_Page(driver);
	    docs.clickCreateDocument();
	    // Read data from Excel
        DataFromExcelFile file = new DataFromExcelFile();
        String title = file.getDataFromExcel("Documents", 2, 1);
	    docs.enterTitle(title);
	    docs.chooseFile(filePath);
	    String alert = driver.switchTo().alert().getText();
	    System.out.println(alert);
//	    // Take screenshot
//    	ScreenShotUtility1 ss = new ScreenShotUtility1();
//    	ss.takeScreenshot(driver, "oversize");
//	    driver.switchTo().alert().accept();
	    Assert.assertEquals(alert,"File size should not be greater than 3MB");
	    Reporter.log("large file size was rejected", true);
	}
	
	@Test(groups = {"negative","smoke"} )
	public void withoutTitle() throws IOException 
	{
		
		// Verify login
        Home_Page home = new Home_Page(driver);
        Assert.assertTrue(home.getsearch().isDisplayed());
        Reporter.log("User is successfully logged in with valid credentials",true);
        String filePath = System.getProperty("user.dir")+ "/src/test/resources/sample.pdf";
	    home.getdocumentslink().click();
	    Documents_Page docs = new Documents_Page(driver);
	    docs.clickCreateDocument();
	    docs.chooseFile(filePath);
	    docs.clickSave();
	    String alert = driver.switchTo().alert().getText();
	    System.out.println(alert);
	    Assert.assertEquals(alert,"Title cannot be empty");
	    Reporter.log("Documnet without title was rejected", true);
	}

}
