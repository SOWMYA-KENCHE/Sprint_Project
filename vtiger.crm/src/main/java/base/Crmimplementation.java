package base;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.DataFromPropertyFile;
import genericUtility.ExplicitWait;
import objRepo.Home_Page;
import objRepo.Login_Page;

public class Crmimplementation {
	
	protected WebDriver driver = null;
	DataFromPropertyFile cdata = new DataFromPropertyFile();
	
	// cross-browser
	public void launchbrowser(String browser) {

	    if (browser.equalsIgnoreCase("edge")) {

	        driver = new ChromeDriver();

	    } 
	       
	    else if (browser.equalsIgnoreCase("chrome"))
	    {
	    	// Avoid Change Password popup
			ChromeOptions settings = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("profile.password_manager_leak_detection", false);
			settings.setExperimentalOption("prefs", prefs);
	        driver = new ChromeDriver(settings);

	    } 
	    else {

	        throw new RuntimeException(
	            "Unsupported browser: " + browser
	        );
	    }
	}
	// normal browser
	
	public void launchbrowser() throws IOException {

        String browser = cdata.getDataFromPropertyFile("Browser");

        if (browser.equalsIgnoreCase("edge")) {

            driver = new EdgeDriver();

        } else if (browser.equalsIgnoreCase("chrome")) {

            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("firefox")) {

            driver = new FirefoxDriver();

        } 
        
        else {

            throw new RuntimeException(
                    "Unsupported browser: " + browser
            );
        }
    }
	
	
	public void openurl() throws IOException
	{
		String Url = cdata.getDataFromPropertyFile("Url");
		//maximize
		driver.manage().window().maximize();
		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(Url);
	}
	
	public void username() throws IOException, InterruptedException
	{

		String Username = cdata.getDataFromPropertyFile("Username");
		
		// login page
		Login_Page login = new Login_Page(driver);
		login.getUsername(Username);
	}
	
	public void password() throws IOException, InterruptedException
	{
		String Password = cdata.getDataFromPropertyFile("Password");
		
		// login page
		Login_Page login = new Login_Page(driver);
		login.getPassword(Password);
	}
	
	public void loginbutton() throws IOException, InterruptedException
	{	
		Login_Page login = new Login_Page(driver);
		login.getLoginbutton().click();
	}
	
	public void logoutbutton() throws IOException, InterruptedException
	{	
		Home_Page home = new Home_Page(driver);
		home.getProfile();
		ExplicitWait.waitForElementToVisible(driver,home.getSignout());
		home.getSignout().click();
	}
	
	public void closebrowser()
	{
		driver.quit();
	}
	
	public WebDriver getDriver()
	{
	    return driver;
	}

}
