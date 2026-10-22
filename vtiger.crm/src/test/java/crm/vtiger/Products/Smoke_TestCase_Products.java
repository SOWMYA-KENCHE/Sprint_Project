package crm.vtiger.Products;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClassCrm;
import genericUtility.DataFromExcelFile;
import objRepo.Common_Page;
import objRepo.Home_Page;
import objRepo.Products_Create_Page;
import objRepo.Products_Details_Page;
import objRepo.Products_Page;

public class Smoke_TestCase_Products extends BaseClassCrm
{
	
	@Test(groups = "smoke")
	public void productDetailOpensFromList() throws IOException 
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
	    String name = "TestProduct" + System.currentTimeMillis();
	    DataFromExcelFile file = new DataFromExcelFile();
        String productname = file.getDataFromExcel("products", 1, 0);
	    Products_Create_Page create = new Products_Create_Page(driver);
	    create.getProductName(productname);
	    create.clickSave();
	    home.getproductlink().click();
	    product.getsearchfull(productname);
	    Common_Page common = new Common_Page(driver);
	    common.clickRecord(productname);
	    Products_Details_Page details = new Products_Details_Page(driver);
	    Assert.assertEquals(details.getProductNamevalue(), productname);
	    Assert.assertTrue(details.isEditDisplayed(), "Edit button is missing");
	    Assert.assertTrue(details.isDuplicateDisplayed(), "Duplicate button is missing");
	    Assert.assertTrue(details.isDeleteDisplayed(), "Delete button is missing");
	    Reporter.log("Product is displayed",true);
	    
	}

}
