package crm.vtiger.Documents;

import java.io.File;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClassCrm;
import genericUtility.DataFromExcelFile;
import objRepo.Documents_Details_Page;
import objRepo.Documents_Page;
import objRepo.Home_Page;

public class System_TestCase_Doc extends BaseClassCrm
{
	 @Test(groups = "system")
	 public void verifyDocumentEndToEnd() throws Exception
	 {
	      // Verify login
	      Home_Page home = new Home_Page(driver);
	      Assert.assertTrue(home.getsearch().isDisplayed());
	      Reporter.log("User logged in successfully", true);
	      DataFromExcelFile file = new DataFromExcelFile();
	      String title = file.getDataFromExcel("Documents", 4, 1);
	      String opportunityName = file.getDataFromExcel("Documents", 4, 2);
	      String edittitle = file.getDataFromExcel("Documents", 4, 4);
	      String filePath = System.getProperty("user.dir") + "/src/test/resources/sample.pdf";
	      Assert.assertTrue(new File(filePath).exists(),"sample.pdf was not found");
	      home.getdocumentslink().click();
	      Documents_Page docs = new Documents_Page(driver);
	      docs.clickCreateDocument();
	      docs.enterTitle(title);
	      docs.chooseFile(filePath);
	      docs.clickSave();
	      Reporter.log("Document uploaded successfully", true);
	      home.getdocumentslink().click();
	      docs.searchDocument(title);
	      docs.clickRecord(title);
	      Documents_Details_Page details = new Documents_Details_Page(driver);
	      details.clickEdit();
	      docs.editTitle(edittitle);
	      docs.clickSave();
	      home.getdocumentslink().click();
	      docs.searchDocument(edittitle);
	      Assert.assertTrue(docs.isRecordPresent(edittitle),"Edited document title was not found");
	      Reporter.log("Document title updated successfully", true);
	      docs.clickRecord(edittitle);
	      details = new Documents_Details_Page(driver);
	      details.clickDelete();
	      try
	      {
	          driver.switchTo().alert().accept();
	       }
	      catch (org.openqa.selenium.NoAlertPresentException e)
	       {
	           // No browser alert was displayed
           }
	       Reporter.log("Delete action performed", true);
//	       home.getProfile();
//	       home.getSignout();
	        Reporter.log("system completed", true);
	    }

}
