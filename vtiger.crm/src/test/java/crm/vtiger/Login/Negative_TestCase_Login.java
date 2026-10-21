package crm.vtiger.Login;

import java.io.IOException;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import base.BaseClassLogin;
import genericUtility.DataFromExcelFile;
import genericUtility.ScreenShotUtility1;
import objRepo.Login_Page;
import objRepo.Opportunities_Page;

public class Negative_TestCase_Login extends BaseClassLogin
{
	@Test(groups = { "Negative", "functional" })
    public void validUsernameWrongPassword() throws IOException, InterruptedException 
    {
        // Verify login
		Login_Page login = new Login_Page(driver);
		// data
		DataFromExcelFile data = new DataFromExcelFile();
        String username = data.getDataFromExcel("Login",1, 0);
        String password = data.getDataFromExcel("Login",1, 2);
		login.loginDetails(username,password);
		System.out.println(login.geterrorMessage());
		Assert.assertEquals(login.geterrorMessage(),"You must specify a valid username and password.");
        Reporter.log("User is not successfully logged in with valid username and incorrect password - NT01 Passed",true);
     }
	@Test(groups = { "Negative", "functional" })
    public void usernameWrongValidPassword() throws IOException, InterruptedException 
    {
        // Verify login
		Login_Page login = new Login_Page(driver);
		// data
		DataFromExcelFile data = new DataFromExcelFile();
        String username = data.getDataFromExcel("Login",1, 0)+"_"+System.currentTimeMillis();
        String password = data.getDataFromExcel("Login",1, 2);
		login.loginDetails(username,password);
		System.out.println(login.geterrorMessage());
		Assert.assertEquals(login.geterrorMessage(),"You must specify a valid username and password.");
        Reporter.log("User is not successfully logged in with valid password and incorrect username - NT02 Passed",true);
     }
	
	@Test(groups = { "Negative", "functional" })
    public void blankspaceUsernameAndPassword() throws IOException, InterruptedException 
    {
        // login
		Login_Page login = new Login_Page(driver);
		// leave blank
        Actions actions = new Actions(driver);
        actions.moveToElement(login.getusername()).click().sendKeys("    ").perform();
        actions.moveToElement(login.getpassword()).click().sendKeys("    ").perform();
        login.getLoginbutton().click();
        // Take screenshot
    	ScreenShotUtility1 ss = new ScreenShotUtility1();
    	ss.takeScreenshot(driver, "blankuandp");
        // Verify error
        Assert.assertEquals(login.geterrorMessage(),"You must specify a valid username and password.");
        Assert.assertFalse(driver.getCurrentUrl().contains("index&module"));
        Reporter.log("user not logged in because of blank username and password - NT03 Passed",true);
     }
	
	@Test(groups = { "negative", "integration"} )
    public void directModuleAccessWithoutLogin() throws IOException, InterruptedException 
    {
        // login into the application
		Login_Page login = new Login_Page(driver);
		// url
		DataFromExcelFile data = new DataFromExcelFile();
        String url = data.getDataFromExcel("Login",1, 3);
		driver.get(url);
		// login page is displayed
		Assert.assertTrue(login.getLoginbutton().isDisplayed());
		Reporter.log("Direct access requires valid credentials",true);
		// data
	    String username = data.getDataFromExcel("Login",1, 0);
		String password = data.getDataFromExcel("Login",1, 1);
	    login.loginDetails(username,password);
	    driver.get(url);
	    Opportunities_Page oppo = new  Opportunities_Page(driver);
	    oppo.getOpportunitiesLink().click();
	    Assert.assertTrue(oppo.getCreateOpportunityButton().isDisplayed());
	    Reporter.log("Access is denied before login and allowed after login - NT04 Passed",true);    
     }

}
