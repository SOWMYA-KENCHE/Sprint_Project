package crm.vtiger.Opportunities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import base.BaseClassCrm;
import genericUtility.DataFromExcelFile;
import objRepo.Common_Page;
import objRepo.Documents_Page;
import objRepo.Home_Page;
import objRepo.Opportunities_Create_Page;
import objRepo.Opportunities_Details_Page;
import objRepo.Opportunities_Page;
import objRepo.Organization_Page;
import objRepo.Products_Create_Page;
import objRepo.Products_Details_Page;

public class System_TestCase_Oppo extends BaseClassCrm
{

	@Test(groups = "system")
    public void systemforopportunities() throws Exception 
	{
		// Verify login
        Home_Page home = new Home_Page(driver);
        System.out.println(home.getCaltext().getText());
        Assert.assertEquals(home.getCaltext().getText(),"Organizations");
        Reporter.log("User is successfully logged in with valid credentials",true);
        // Navigate to Opportunities page
        Opportunities_Page oppo = new Opportunities_Page(driver);
        oppo.getOpportunitiesLink().click();
        oppo.getCreateOpportunityButton().click();
        // Create opportunity
        Opportunities_Create_Page create =  new Opportunities_Create_Page(driver);
        FileInputStream file1 = new FileInputStream("./src/test/resources/testcase.xlsx");
        Workbook workb = WorkbookFactory.create(file1);
        DataFormatter df = new DataFormatter();
        String opportunityName = df.formatCellValue(workb.getSheet("opportunities").getRow(2).getCell(0));
        String salesStage = df.formatCellValue(workb.getSheet("opportunities").getRow(2).getCell(1));
        String amount = df.formatCellValue(workb.getSheet("opportunities").getRow(2).getCell(2));
        String closingDate = df.formatCellValue(workb.getSheet("opportunities").getRow(2).getCell(3));
        String organizationName = df.formatCellValue(workb.getSheet("opportunities").getRow(2).getCell(4));
        create.getOpportunityName(opportunityName);
        create.getRelatedto().click();
        create.selectOrganization(organizationName);
        create.getAmount(amount);
        create.getClosingDate(closingDate);
        create.getSalesStage(salesStage);
        create.getSaveButton().click();
        Reporter.log("Opportunity created", true);
        Common_Page common = new Common_Page(driver);
        Opportunities_Details_Page details = new Opportunities_Details_Page(driver);
        // Add product
        DataFromExcelFile file = new DataFromExcelFile();
        String product = file.getDataFromExcel("products", 1, 0);
        home.getproductlink().click();
        Products_Details_Page pdetails = new Products_Details_Page(driver);
        Products_Create_Page pcreate = new Products_Create_Page(driver);
        pcreate.productsearchlink(product);
        details.selectProduct(product);
        Reporter.log("Product selected", true);
        // Add document
        pdetails.getmoreInformation().click();
        pdetails.selectdropdowndoc();
        pdetails.clickAddDocument();
        Documents_Page document = new Documents_Page(driver);
        String filePath = System.getProperty("user.dir")+ "/src/test/resources/sample.pdf";
        document.createDocument("OpportunityDocument", filePath);
        Reporter.log("Document added", true);
        // Edit opportunity rating
        home.getopportunitiesLink().click();
        oppo.searchOpportunityfull(opportunityName);
        common.clickRecord(organizationName);
        create.clickEdit();
        create.getnewsalesStage().click();
        create.getSaveButton().click();
        Reporter.log("Opportunity stage changed to shutdown", true);
        Thread.sleep(2000);
        Organization_Page org = new Organization_Page(driver);
        home.getOrganizationslink().click();
        org.searchorganization1(organizationName);
        common.clickRecord(organizationName);
       // Verify opportunity appears in the organization
        Assert.assertEquals(org.getorgname().getText(),organizationName);
        // Sign out
        home.getProfile().click();
        home.getSignout().click();
        Reporter.log("opportunities system  completed", true);
    }
}
