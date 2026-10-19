package crm.vtiger.Opportunities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import java.time.Duration;
import base.BaseClassCrm;
import genericUtility.DataFromExcelFile;
import objRepo.Common_Page;
import objRepo.Home_Page;
import objRepo.Opportunities_Create_Page;
import objRepo.Opportunities_Page;

public class Functional_TestCase_Opp extends BaseClassCrm 
{
	@Test(groups = "functional")
    public void opportunityPageOpen() throws IOException, InterruptedException 
    {
        // Verify login
        Home_Page home = new Home_Page(driver);
        System.out.println(home.getCaltext().getText());
        Assert.assertEquals(home.getCaltext().getText(),"Organizations");
        Reporter.log("User is successfully logged in with valid credentials",true);
        // Navigate to Opportunities page
        Opportunities_Page oppo = new Opportunities_Page(driver);
        oppo.getOpportunitiesLink().click();
        // verify Opportunity page
        Assert.assertTrue(oppo.getCreateOpportunityButton().isDisplayed());
        Reporter.log("Opportunity page successfully opened",true);
     }

    @Test(groups = "functional")
    public void createOpportunityWithValidValues() throws IOException, InterruptedException 
    {

        // Verify login
        Home_Page home = new Home_Page(driver);
        System.out.println(home.getCaltext().getText());
        Assert.assertEquals(home.getCaltext().getText(),"Organizations");
        Reporter.log("User is successfully logged in with valid credentials",true);
        // Navigate to Opportunities page
        Opportunities_Page oppo = new Opportunities_Page(driver);
        oppo.getOpportunitiesLink().click();
        // Open Create Opportunity form
        oppo.getCreateOpportunityButton().click();
        Opportunities_Create_Page opcreate = new Opportunities_Create_Page(driver);
        // Read data from Excel
        FileInputStream file1 = new FileInputStream("./src/test/resources/testcase.xlsx");
        Workbook workb = WorkbookFactory.create(file1);
        DataFormatter df = new DataFormatter();
        String opportunityName = df.formatCellValue(workb.getSheet("opportunities").getRow(1).getCell(0));
        String salesStage = df.formatCellValue(workb.getSheet("opportunities").getRow(1).getCell(1));
        String amount = df.formatCellValue(workb.getSheet("opportunities").getRow(1).getCell(2));
        String closingDate = df.formatCellValue(workb.getSheet("opportunities").getRow(1).getCell(3));
        String organizationName = df.formatCellValue(workb.getSheet("opportunities").getRow(1).getCell(4));
         // Fill opportunity details
        opcreate.getOpportunityName(opportunityName);
         // Select related organization
        opcreate.getRelatedto().click();
        opcreate.selectOrganization(organizationName);
        opcreate.getAmount(amount);
        opcreate.getClosingDate(closingDate);
        opcreate.getSalesStage(salesStage);
        // Save opportunity
        opcreate.getSaveButton().click();
        // Wait for the details page confirmation
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(opcreate.getverifyCreate()));
        Assert.assertTrue(opcreate.getverifyCreate().getText().contains("Opportunity Information"),"Opportunity creation was not confirmed");
        Reporter.log("Opportunity created successfully",true);
        }
}