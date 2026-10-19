package crm.vtiger.Login;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClassLogin;
import genericUtility.DataFromExcelFile;
import objRepo.Home_Page;
import objRepo.Login_Page;
import objRepo.Opportunities_Page;
import objRepo.Products_Page;

public class Integration_TestCase_Login extends BaseClassLogin
{
	@Test(groups = "integration")
    public void differentnmodulesession() throws IOException, InterruptedException 
    {
        // login into the application
		Login_Page login = new Login_Page(driver);
		// data
		DataFromExcelFile data = new DataFromExcelFile();
        String username = data.getDataFromExcel("Login",1, 0);
        String password = data.getDataFromExcel("Login",1, 1);
		login.loginDetails(username,password);
		Home_Page home = new Home_Page(driver);
		Assert.assertEquals(home.getProfile().isDisplayed(),true);
        Reporter.log("User is successfully logged in with valid credentials",true);
        home.getproductlink().click();
        Products_Page product = new Products_Page(driver);
        Assert.assertTrue(product.getCreateProduct().isDisplayed());
        home.getopportunitiesLink().click();
        Opportunities_Page oppo = new Opportunities_Page(driver);
        Assert.assertTrue(oppo.getCreateOpportunityButton().isDisplayed());
        Reporter.log("The same logged-in session remains active  - IT01 Passed",true);
        
     }

}
