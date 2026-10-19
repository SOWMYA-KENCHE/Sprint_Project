
package crm.vtiger.Documents;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClassCrm;
import genericUtility.DataFromExcelFile;
import objRepo.Home_Page;
import objRepo.Organization_Page;
import objRepo.Common_Page;
import objRepo.Documents_Page;

public class Integration_TestCase_Doc extends BaseClassCrm
{
    @Test(groups = "integration")
    public void verifyDocumentAddedFromOrganization() throws Exception
    {
        Home_Page home = new Home_Page(driver);
        Assert.assertTrue(home.getsearch().isDisplayed(),"User is not logged in successfully");
        Reporter.log("User is successfully logged in with valid credentials",true);
        // Open the Organization
        Organization_Page organization = new Organization_Page(driver);
        home.getOrganizationslink().click();
     // Read test data from Excel
        DataFromExcelFile file = new DataFromExcelFile();
        String documentName = file.getDataFromExcel("Documents", 7, 1);
        String organizationName = file.getDataFromExcel("Documents", 7, 2);
        String filePath = System.getProperty("user.dir")+ "/src/test/resources/sample.pdf";
        Common_Page common = new Common_Page(driver);
        organization.searchorganization1(organizationName);
        common.clickRecord(organizationName);
        common.clickMoreInformation();
        common.selectdropdowndoc();
        // Open More Information
        Reporter.log("Organization opened: " + organizationName,true);
        common.clickAddDocument();
        // Enter title, upload file and save 
        Documents_Page documents = new Documents_Page(driver);
        documents.enterTitle(documentName);
        documents.chooseFile(filePath);
        documents.clickSave();
        Reporter.log("Document created from the Organization related list",true);
        // Reopen the Organization and verify association
        home.getOrganizationslink().click();
        organization.searchorganization1(organizationName);
        common.clickRecord(organizationName);
        common.clickMoreInformation();
        common.selectdropdowndoc();
        Assert.assertTrue(common.isRecordPresent(documentName),"Document is not linked to the Organization");
        Reporter.log("Document is linked to " + organizationName,true);
        // Open the Documents module
        home.getdocumentslink().click();
        documents = new Documents_Page(driver);
        documents.searchDocument(documentName);
        Assert.assertTrue(documents.isRecordPresent(documentName),"Document is not displayed in the Documents module");
        Reporter.log("Document is displayed in the Documents module",true);
    }
}
