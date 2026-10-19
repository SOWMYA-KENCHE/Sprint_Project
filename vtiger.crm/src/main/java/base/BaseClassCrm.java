package base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClassCrm
{
	    protected WebDriver driver;
	    protected CrmExecutors C;

	    @BeforeMethod(groups =  {"smoke","functional","integration","system","negative"} )
	    @Parameters("browser")
	    public void beforemethod(@Optional("chrome")String browser) throws IOException, InterruptedException
	    {
	        C = new CrmExecutors();
	        C.executors("LAUNCH_BROWSER", browser);
	        driver = C.getDriver();
	        C.executors("OPEN_URL");
	        C.executors("USERNAME");
	        C.executors("PASSWORD");
	        C.executors("LOGIN_BUTTON");
	        Reporter.log("Login successful on " + browser, true);
	    }

	    @AfterMethod(groups =  {"smoke","functional","integration","system","negative"} )
	    public void aftermethod() throws IOException, InterruptedException 
	    {
	        try {
	            if (driver != null &&
	                driver.getCurrentUrl().contains("Home&action")) {

	                C.executors("LOGOUT_BUTTON");
	                Reporter.log("logout successful", true);
	            }
	        }
	        catch (Exception e) {
	            Reporter.log("Logout skipped: " + e.getMessage(), true);
	        }
	        finally {
	            if (driver != null) {
	                C.executors("CLOSE_BROWSER");
	                Reporter.log("closing browser", true);
	            }
	        }
	    }
}
