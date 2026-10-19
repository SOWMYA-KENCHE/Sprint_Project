package crm.vtiger.Opportunities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClassCrm;
import objRepo.Home_Page;
import objRepo.Opportunities_Create_Page;
import objRepo.Opportunities_Page;
import objRepo.Organization_Page;

public class Integration_TestCase_Opp extends BaseClassCrm
{
	@Test(groups = "integration")
    public void TC_OPP_I01() throws EncryptedDocumentException, IOException, InterruptedException 
	{
		// Verify login
		Home_Page home = new Home_Page(driver);
        Assert.assertTrue(home.getsearch().isDisplayed(),
            "User is not logged in"
        );
        Reporter.log("User is successfully logged in with valid credentials",true);
        // Navigate to Opportunities page
        Opportunities_Page oppo = new Opportunities_Page(driver);
        home.getopportunitiesLink().click();
        oppo.getCreateOpportunityButton().click();
        // Create opportunity
        Opportunities_Create_Page create = new Opportunities_Create_Page(driver);
         // Read data from Excel
        FileInputStream file1 = new FileInputStream("./src/test/resources/testcase.xlsx");
        Workbook workb = WorkbookFactory.create(file1);
        DataFormatter df = new DataFormatter();
        String opportunityName = df.formatCellValue(workb.getSheet("opportunities").getRow(1).getCell(0));
        String salesStage = df.formatCellValue(workb.getSheet("opportunities").getRow(1).getCell(1));
        String amount = df.formatCellValue(workb.getSheet("opportunities").getRow(1).getCell(2));
        String closingDate = df.formatCellValue(workb.getSheet("opportunities").getRow(1).getCell(3));
        String organizationName = df.formatCellValue(workb.getSheet("opportunities").getRow(1).getCell(4));
        create.getOpportunityName(opportunityName);
        create.getRelatedto().click();
        create.selectOrganization(organizationName);
        create.getAmount(amount);
        create.getClosingDate(closingDate);
        create.getSalesStage(salesStage);
        create.getSaveButton().click();
        Reporter.log("Opportunity created successfully", true);
        // Open Organizations
        Organization_Page org = new Organization_Page(driver);
        home.getOrganizationslink().click();
        org.searchorganization(organizationName);
       // Verify opportunity appears in the organization
        Assert.assertEquals(org.getorgname().getText(),organizationName);
        Reporter.log("Opportunity appears in organization",true);

	}
}
