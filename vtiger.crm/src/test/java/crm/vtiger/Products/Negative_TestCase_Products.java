package crm.vtiger.Products;

import java.io.IOException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClassCrm;
import genericUtility.DataFromExcelFile;
import objRepo.Home_Page;
import objRepo.Products_Create_Page;
import objRepo.Products_Page;

public class Negative_TestCase_Products extends BaseClassCrm
{
	@Test(groups = { "negative","functional" } )
    public void nonNumericUnitPrice() throws IOException, InterruptedException 
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
        DataFromExcelFile file = new DataFromExcelFile();
        String productname = file.getDataFromExcel("products", 1, 0);
        String productno = file.getDataFromExcel("products", 1, 1);
        String unitprice = file.getDataFromExcel("products", 1, 3);
        Products_Create_Page cproduct = new Products_Create_Page(driver);
        // Fill product details
        cproduct.getProductName(productname);
        cproduct.getProductCode(productno);
        cproduct.getUnitPrice(unitprice);
        cproduct.clickSave();
        // alert 
        driver.switchTo().alert().accept();
        Assert.assertTrue(cproduct.isFormDisplayed(),"Product form should remain displayed when Unit Price is invalid");
        Reporter.log("Product form remains displayed after entering a non-numeric price",true);

        }
	
	@Test(groups = { "negative","smoke" } )
    public void saveWithoutProductName() 
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
        Products_Create_Page create = new Products_Create_Page(driver);
        create.clickSave();
        // alert 
        String alertmsg = driver.switchTo().alert().getText();
        Assert.assertEquals(alertmsg, "Product Name cannot be empty");
        Reporter.log("Product cannot be saved without product name",true);
    }

}

