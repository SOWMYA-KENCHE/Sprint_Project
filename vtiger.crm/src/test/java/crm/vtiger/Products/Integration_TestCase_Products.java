package crm.vtiger.Products;

import java.io.IOException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import base.BaseClassCrm;
import genericUtility.DataFromExcelFile;
import objRepo.Home_Page;
import objRepo.Products_Create_Page;
import objRepo.Products_Details_Page;
import objRepo.Products_Page;

public class Integration_TestCase_Products extends BaseClassCrm 
{

    @Test(groups = "integration")
    public void linkProductToVendor() throws IOException 
    {
        Home_Page home = new Home_Page(driver);
        Assert.assertTrue(home.getsearch().isDisplayed(),
            "User is not logged in"
        );
        Reporter.log("User logged in successfully", true);
        DataFromExcelFile file = new DataFromExcelFile();
        String productName = file.getDataFromExcel("products", 4, 0);
        String vendorName = file.getDataFromExcel("products", 4, 1);
        home.getproductlink().click();
        Products_Page products = new Products_Page(driver);
        products.getCreateProduct().click();
        Products_Create_Page create = new Products_Create_Page(driver);
        create.getProductName(productName);
        create.selectVendor(vendorName);
        Assert.assertEquals( create.getVendorName(), vendorName, "Vendor was not selected correctly"
        );
        create.clickSave();
        Products_Details_Page details = new Products_Details_Page(driver);
//        System.out.println(details.getProductName1(vendorName));
        Assert.assertEquals(details.getProductNamevalue(),"schoolbags","Vendor is not displayed on product details"
        );
        Reporter.log("Product is linked to vendor: " + vendorName,true);
    }
}