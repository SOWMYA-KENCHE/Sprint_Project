package crm.vtiger.Login;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClassLogin;
import genericUtility.DataFromExcelFile;
import objRepo.Home_Page;
import objRepo.Login_Page;

public class Smoke_TestCase_Login extends BaseClassLogin
{
	@Test(groups = "smoke")
    public void loginpagebuttons() throws IOException, InterruptedException 
    {
        // Verify buttons are visible and accessible 
		Login_Page login = new Login_Page(driver);
		// data
		Assert.assertTrue(login.getusername().isDisplayed());
		Assert.assertTrue(login.getpassword().isDisplayed());
		Assert.assertTrue(login.getLoginbutton().isDisplayed());
		// accessible
		DataFromExcelFile data = new DataFromExcelFile();
        String username = data.getDataFromExcel("Login",1, 0);
        String password = data.getDataFromExcel("Login",1, 1);
		login.getUsername(username);
		login.getPassword(password);
		//Assert.assertEquals(login.getusername().getText(), "admin");
		//Assert.assertEquals(login.getpassword().getText(), "admin");
		Assert.assertTrue(login.getLoginbutton().isEnabled());
        Reporter.log("Login page buttons are visible and accessible - SM01 - Passed",true);
     }
	
	@Test(groups = "smoke")
    public void signOut() throws IOException, InterruptedException 
    {
		Login_Page login = new Login_Page(driver);
		// data
		DataFromExcelFile data = new DataFromExcelFile();
        String username = data.getDataFromExcel("Login",1, 0);
        String password = data.getDataFromExcel("Login",1, 1);
		login.loginDetails(username,password);
		Home_Page home = new Home_Page(driver);
		home.getProfile().click();
		home.getSignout().click();
		Assert.assertTrue(login.getusername().isDisplayed());
        Reporter.log("sign out returns to login page - SM02 Passed",true);
     }

}
