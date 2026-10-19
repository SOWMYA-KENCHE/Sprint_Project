package base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

public class CrmExecutors {
	
	private Crmimplementation s = new Crmimplementation();
	
	public void executors(String keyword, String browser)
	        throws IOException, InterruptedException {

	    if(keyword.equals("LAUNCH_BROWSER")) {
	        s.launchbrowser(browser);
	    }
	}
	public void executors(String keyword) throws IOException, InterruptedException
	{
		if(keyword.equals("LAUNCH_BROWSER"))
		{
			s.launchbrowser();
		}
		else if(keyword.equals("OPEN_URL"))
		{
			s.openurl();
		}
		else if(keyword.equals("USERNAME"))
		{
			s.username();
		}
		else if(keyword.equals("PASSWORD"))
		{
			s.password();
		}
		else if(keyword.equals("LOGIN_BUTTON"))
		{
			s.loginbutton();
		}
		else if(keyword.equals("LOGOUT_BUTTON"))
		{
			s.logoutbutton();
		}
		else if(keyword.equals("CLOSE_BROWSER"))
		{
			s.closebrowser();
		}
		
		
		
	}
	public WebDriver getDriver()
	{
	    return s.getDriver();
	}

}
