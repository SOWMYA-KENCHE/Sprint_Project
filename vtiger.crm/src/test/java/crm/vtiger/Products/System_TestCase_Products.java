package crm.vtiger.Products;

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
import genericUtility.DataFromExcelFile;
import objRepo.Common_Page;
import objRepo.Home_Page;
import objRepo.Opportunities_Create_Page;
import objRepo.Opportunities_Details_Page;
import objRepo.Opportunities_Page;
import objRepo.Products_Create_Page;
import objRepo.Products_Details_Page;
import objRepo.Products_Page;

public class System_TestCase_Products extends BaseClassCrm
{
	@Test(groups = "system")
    public void systemEndToEnd() throws EncryptedDocumentException, IOException, InterruptedException 
	{
		// Verify login
        Home_Page home = new Home_Page(driver);
        Assert.assertTrue(home.getsearch().isDisplayed());
        Reporter.log("User is successfully logged in with valid credentials",true);
        // Navigate to products page
        home.getproductlink().click();
        Products_Page product = new Products_Page(driver);
        // create product
        product.getCreateProduct().click();
        Products_Create_Page create = new Products_Create_Page(driver);
        FileInputStream file1 = new FileInputStream("./src/test/resources/testcase.xlsx");
        Workbook workb = WorkbookFactory.create(file1);
        DataFormatter df = new DataFormatter();
        DataFromExcelFile file = new DataFromExcelFile();
        String productname =
        	    file.getDataFromExcel("products", 1, 0)
        	    + "_" + System.currentTimeMillis();
        String originalCode = file.getDataFromExcel("products", 1, 1);
        String unitPrice = df.formatCellValue(workb.getSheet("products").getRow(1).getCell(2));
        create.getProductName(productname);
        create.getUnitPrice(unitPrice);
        create.clickSave();
        // Verify product is created
        home.getproductlink().click();
        product.getsearchfull(productname);
        Common_Page common = new Common_Page(driver);
        common.clickRecord(productname);
        Assert.assertTrue(product.isRecordPresent(productname));
        Products_Details_Page details = new Products_Details_Page(driver);
        Assert.assertEquals(details.getProductNamevalue(), productname);
        // Edit product price
        details.clickEdit();
        String unitPrice1 = df.formatCellValue(workb.getSheet("products").getRow(2).getCell(2));
        create.getUnitPrice(unitPrice1);
        create.clickSave();
        // Verify updated price
        Thread.sleep(2000);
        home.getproductlink().click();
        product.getsearchfull(productname);
        common.clickRecord(productname);
        System.out.println(details.getUnitPrice());
        Assert.assertTrue(details.getUnitPrice().contains("50,000.00"),"Product price was not updated");
        // Open Opportunities
        home.getopportunitiesLink().click();
        Opportunities_Page oppo = new Opportunities_Page(driver);
        oppo.getCreateOpportunityButton().click();
        Opportunities_Create_Page oppo1 = new Opportunities_Create_Page(driver);
        Opportunities_Details_Page oppo2 =new Opportunities_Details_Page(driver);
        // data
        String opponame = file.getDataFromExcel("products", 3, 5);
        String organizationName = file.getDataFromExcel("products", 3, 6);
        String closedate = file.getDataFromExcel("products", 3, 7);
        String salesStage = file.getDataFromExcel("products", 3, 8);
        oppo1.getOpportunityName(opponame);
        // select related organization
        oppo1.getRelatedto().click();
        oppo1.selectOrganization(organizationName);
        oppo1.getClosingDate(closedate);
        oppo1.getSalesStage(salesStage);
        oppo1.getSaveButton().click();
        oppo.clickMoreInformation();
        oppo2.productsdropdown();
        oppo2.selectProductBtn();
        Thread.sleep(2000);
        oppo1.selectproduct(productname);
        // Delete product
        home.getproductlink().click();
        product.getsearchfull(productname);
        common.clickRecord(productname);
        details.clickDelete();
        driver.switchTo().alert().accept();
        product.getsearchfull(productname);
        Assert.assertFalse(product.isRecordPresent(productname),"Product was not deleted");
        Reporter.log("system end to end",true);
    }
}