package crm.vtiger.Documents;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClassCrm;
import genericUtility.DataFromExcelFile;
import objRepo.Home_Page;
import objRepo.Documents_Page;
import objRepo.Documents_Details_Page;

public class Smoke_TestCase_Doc extends BaseClassCrm
{
	@Test(groups = "smoke")
    public void verifydocumenttextfield() throws IOException, InterruptedException 
    {
        // Verify login
        Home_Page home = new Home_Page(driver);
        Assert.assertTrue(home.getsearch().isDisplayed());
        Reporter.log("User is successfully logged in with valid credentials",true);
        // Navigate to products page
        home.getdocumentslink().click();
        Documents_Page docs = new Documents_Page(driver);
        docs.clickCreateDocument();
        Assert.assertTrue(docs.isCreateDocumentDisplayed());
        Assert.assertTrue(docs.gettitle().isDisplayed());
        Reporter.log("title textfield is diaplayed",true);
     }
	
    @Test(groups = "smoke")
    public void verifyDocumentCanBesearched() throws Exception
    {
        // Verify login 
        Home_Page home = new Home_Page(driver);
        Assert.assertTrue(home.getdocumentslink().isDisplayed(),"Documents link is not displayed");
        Reporter.log("User is successfully logged in", true);
        home.getdocumentslink().click();
        // Search and open the existing document
        DataFromExcelFile file = new DataFromExcelFile();
	    String title = file.getDataFromExcel("Documents", 5, 1);
        Documents_Page docs = new Documents_Page(driver);
        docs.searchDocument(title);
        Assert.assertTrue(docs.isRecordPresent(title),"Document QA_Doc_001 is not present");
        docs.clickRecord(title);
        // Verify document details page
        Documents_Details_Page details = new Documents_Details_Page(driver);
        Assert.assertTrue(details.getHeaderText().contains(title), "Document details page is not displayed");
        Reporter.log("Document opened successfully", true);

    }
}