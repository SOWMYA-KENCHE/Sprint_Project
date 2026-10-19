package crm.vtiger.Login;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClassLogin;
import genericUtility.DataFromExcelFile;
import objRepo.Home_Page;
import objRepo.Login_Page;
import objRepo.Products_Page;

public class System_TestCase_Login extends BaseClassLogin
{
	@Test(groups = "system")
    public void verifyLoginAndModuleNavigation() throws IOException, InterruptedException 
    {
		Login_Page login = new Login_Page(driver);
		// data
		DataFromExcelFile data = new DataFromExcelFile();
        String username = data.getDataFromExcel("Login",1, 0);
        String password = data.getDataFromExcel("Login",1, 1);
		login.loginDetails(username,password);
		Assert.assertTrue(driver.getCurrentUrl().contains("index&module"));
		Reporter.log("User is successfully logged in with valid credentials",true);
		Products_Page product = new Products_Page(driver);
		product.getProductlink().click();
		Assert.assertTrue(product.getCreateProduct().isDisplayed());
		Home_Page home = new Home_Page(driver);
		home.getProfile().click();
		home.getSignout().click();
		Assert.assertTrue(login.getusername().isDisplayed());
        Reporter.log("Login and product navigation to logout successful - S01 Passed",true);
     }

}
