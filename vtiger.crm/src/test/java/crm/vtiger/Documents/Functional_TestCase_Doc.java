package crm.vtiger.Documents;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import base.BaseClassCrm;
import genericUtility.DataFromExcelFile;
import objRepo.Documents_Details_Page;
import objRepo.Documents_Page;
import objRepo.Home_Page;

public class Functional_TestCase_Doc extends BaseClassCrm 
{
	@Test(groups = "functional")
	public void createDocumentWithAllFields() throws Exception 
	{
		// Verify login
        Home_Page home = new Home_Page(driver);
        Assert.assertTrue(home.getsearch().isDisplayed());
        Reporter.log("User is successfully logged in with valid credentials",true);
         // Read data from Excel
        DataFromExcelFile file = new DataFromExcelFile();
        String title = file.getDataFromExcel("Documents", 1, 1);
	    String filePath = System.getProperty("user.dir")+ "/src/test/resources/sample.pdf";
	    home.getdocumentslink().click();
	    Documents_Page docs = new Documents_Page(driver);
	    docs.clickCreateDocument();
	    docs.enterTitle(title);
	    docs.chooseFile(filePath);
	    docs.clickSave();
	    Documents_Details_Page details = new Documents_Details_Page(driver);
	    Assert.assertEquals(details.getTitle(), title);
	 //   Assert.assertTrue(details.getFileName().contains("sample.pdf"),"Uploaded file name is incorrect");
	    Reporter.log("Document created successfully with all fields", true);
	}
}