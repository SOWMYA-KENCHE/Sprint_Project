package crm.vtiger.Products;

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
import objRepo.Home_Page;
import objRepo.Opportunities_Create_Page;
import objRepo.Opportunities_Page;
import objRepo.Products_Create_Page;
import objRepo.Products_Details_Page;
import objRepo.Products_Page;

public class Functional_TestCase_Products extends BaseClassCrm 
{
	@Test(groups = "functional")
    public void verifyproductpage() throws IOException, InterruptedException 
    {
        // Verify login
        Home_Page home = new Home_Page(driver);
        Assert.assertTrue(home.getsearch().isDisplayed());
        Reporter.log("User is successfully logged in with valid credentials",true);
        // Navigate to products page
        home.getproductlink().click();
        Products_Page product = new Products_Page(driver);
        Assert.assertTrue(product.getCreateProduct().isDisplayed());
        // verify product page
        Reporter.log("product page successfully opened",true);
     }

    @Test(groups = "functional")
    public void createOpportunityWithValidValues() throws IOException, InterruptedException 
    {

    	// Verify login
        Home_Page home = new Home_Page(driver);
        Assert.assertTrue(home.getsearch().isDisplayed());
        Reporter.log("User is successfully logged in with valid credentials",true);
        // Navigate to products page
        home.getproductlink().click();
        Products_Page product = new Products_Page(driver);
        // creat product
        product.getCreateProduct().click();
        // Read data from Excel
        FileInputStream file1 = new FileInputStream("./src/test/resources/testcase.xlsx");
        Workbook workb = WorkbookFactory.create(file1);
        DataFormatter df = new DataFormatter();
        DataFromExcelFile file = new DataFromExcelFile();
        String productname = file.getDataFromExcel("products", 1, 0);
        String productno = file.getDataFromExcel("products", 1, 1);
        String unitprice = df.formatCellValue(workb.getSheet("products").getRow(1).getCell(2));
        Products_Create_Page cproduct = new Products_Create_Page(driver);
        // Fill product details
        cproduct.getProductName(productname);
        cproduct.getProductCode(productno);
        cproduct.getUnitPrice(unitprice);
        cproduct.clickSave();
        Products_Details_Page details = new Products_Details_Page(driver);
        Assert.assertEquals(details.getProductNamevalue(),productname);
        Reporter.log("product created successfully",true);

        }

	@Test(groups = "functional")
	public void duplicateProduct() throws IOException, InterruptedException
	{
	
		// Verify login
        Home_Page home = new Home_Page(driver);
        Assert.assertTrue(home.getsearch().isDisplayed());
        Reporter.log("User is successfully logged in with valid credentials",true);
        // Navigate to products page
        home.getproductlink().click();
        Products_Page product = new Products_Page(driver);
        // Read data from Excel
        FileInputStream file1 = new FileInputStream("./src/test/resources/testcase.xlsx");
        Workbook workb = WorkbookFactory.create(file1);
        DataFormatter df = new DataFormatter();
        DataFromExcelFile file = new DataFromExcelFile();
        String originalName = file.getDataFromExcel("products", 1, 0);
        String originalCode = file.getDataFromExcel("products", 1, 1);
        String unitPrice = df.formatCellValue(workb.getSheet("products").getRow(1).getCell(2));
	    String duplicateName = "Copy" + originalName;
	    String duplicateCode = "Copy" + originalCode;
	    product.getCreateProduct().click();
	    Products_Create_Page create = new Products_Create_Page(driver);
	    create.getProductName(originalName);
	    create.getProductCode(originalCode);
	    create.getUnitPrice(unitPrice);
	    create.clickSave();
	    // Verify original product creation
	    Products_Details_Page details = new Products_Details_Page(driver);
	    Assert.assertEquals(details.getProductNamevalue(),originalName,"Original product was not created successfully");
	    Reporter.log("Original product created successfully", true);
	    // Step 6: Click Duplicate
	    details.clickDuplicate();
	    // Enter a unique name and product code for the copy
	    create.getProductName(duplicateName);
	    create.getProductCode(duplicateCode);
	    create.clickSave();
	    // Step 8: Verify duplicated product
	    Assert.assertEquals(details.getProductNamevalue(),duplicateName,"Product duplication failed");
	    Reporter.log("Product duplicated successfully", true);
	}

}