package base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClassLogin 
{
    protected WebDriver driver = null;

    protected CrmExecutors C = new CrmExecutors();
    @BeforeMethod(groups =  {"smoke","functional","integration","system","negative"} )
    @Parameters("browser")
    public void beforemethod(@Optional("chrome")String browser) throws IOException, InterruptedException
    {
        // launch browser
        C.executors("LAUNCH_BROWSER");
        // get driver
        driver = C.getDriver();
        // application
        C.executors("OPEN_URL");
    }

    @AfterMethod(groups =  {"smoke","functional","integration","system","negative"} )
    public void afterMethod() throws IOException, InterruptedException {
        // close browser
        if (driver != null) {
            C.executors("CLOSE_BROWSER");
        }
    }
}