package crm.vtiger.Login;

import java.io.IOException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import base.BaseClassCrm;
import base.BaseClassLogin;
import genericUtility.DataFromExcelFile;
import objRepo.Home_Page;
import objRepo.Login_Page;

public class Functional_TestCase_Login extends BaseClassLogin
{
	@Test(groups = "functional")
    public void validLogin() throws IOException, InterruptedException 
    {
        // Verify login
		Login_Page login = new Login_Page(driver);
		// data
		DataFromExcelFile data = new DataFromExcelFile();
        String username = data.getDataFromExcel("Login",1, 0);
        String password = data.getDataFromExcel("Login",1, 1);
		login.loginDetails(username,password);
        Home_Page home = new Home_Page(driver);
        System.out.println(home.getCaltext().getText());
        Assert.assertEquals(home.getCaltext().getText(),"Organizations");
        Reporter.log("User is successfully logged in with valid credentials - F01 Passed",true);
     }
	
	@Test(groups = "functional")
    public void verifyPasswordMasking() throws IOException, InterruptedException 
    {
		Login_Page login = new Login_Page(driver);
		// data
		DataFromExcelFile data = new DataFromExcelFile();
		// String username = data.getDataFromExcel("Sheet2",1, 0);
        String password = data.getDataFromExcel("Login",1, 1);
        //login.getUsername(username);
		login.getPassword(password);
		String field = login.getPasswordFieldType();
		Assert.assertEquals(field,"password","Password characters should be masked");
		Reporter.log("password masking is verified - F02 Passed",true);
     }

    
}