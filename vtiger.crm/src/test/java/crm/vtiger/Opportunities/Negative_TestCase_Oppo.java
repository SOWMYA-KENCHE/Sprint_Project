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
import base.BaseClassLogin;
import objRepo.Home_Page;
import objRepo.Opportunities_Create_Page;
import objRepo.Opportunities_Page;

public class Negative_TestCase_Oppo extends BaseClassCrm
{
	    @Test(groups = {"negative","functional"})
	    public void createOpportunityWithoutrelatingto() throws IOException, InterruptedException 
	    {

	        // Verify login
	        Home_Page home = new Home_Page(driver);
	        System.out.println(home.getCaltext().getText());
	        Assert.assertEquals(home.getCaltext().getText(),"Organizations");
	        Reporter.log("User is successfully logged in with valid credentials",true);
	        // Navigate to Opportunities page
	        Opportunities_Page oppo = new Opportunities_Page(driver);
	        home.getopportunitiesLink().click();
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
	         // Fill opportunity details
	        opcreate.getOpportunityName(opportunityName);
	        opcreate.getAmount(amount);
	        opcreate.getClosingDate(closingDate);
	        opcreate.getSalesStage(salesStage);
	        // Save opportunity
	        opcreate.getSaveButton().click();
	        String alertmsg = driver.switchTo().alert().getText();
	        Assert.assertEquals(alertmsg, "Related To cannot be none");
	        Reporter.log("Opportunity cannot be done without relatedto",true);
	  }
	 
	 @Test(groups = {"negative","functional"})
	 public void createOpportunityWithNegativeVlaues() throws EncryptedDocumentException, IOException
	 {

		// Verify login
	        Home_Page home = new Home_Page(driver);
	        System.out.println(home.getCaltext().getText());
	        Assert.assertEquals(home.getCaltext().getText(),"Organizations");
	        Reporter.log("User is successfully logged in with valid credentials",true);
	        // Navigate to Opportunities page
	        Opportunities_Page oppo = new Opportunities_Page(driver);
	        home.getopportunitiesLink().click();
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
	        String orgname = df.formatCellValue(workb.getSheet("opportunities").getRow(1).getCell(4));
	        String probability = df.formatCellValue(workb.getSheet("opportunities").getRow(2).getCell(6));
	         // Fill opportunity details
	        opcreate.getOpportunityName(opportunityName);
	        opcreate.getRelatedto().click();
	        opcreate.selectOrganization(orgname);
	        opcreate.getAmount(amount);
	        opcreate.getClosingDate(closingDate);
	        opcreate.getSalesStage(salesStage);
	        opcreate.getprobability(probability);
	        // Save opportunity
	        opcreate.getSaveButton().click();
	        String alertmsg = driver.switchTo().alert().getText();
	        Assert.assertEquals(alertmsg, "Invalid Probability");
	        Reporter.log("Opportunity cannot be done with negative values",true);
	 }

}
