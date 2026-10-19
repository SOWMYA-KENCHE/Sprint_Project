package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtility.ExplicitWait;

public class Login_Page 
{
	// initialization

	WebDriver driver;
	public Login_Page(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	// declare 
	
	@FindBy(name = "user_name")
	private WebElement username;
	
	@FindBy(name = "user_password")
	private WebElement password;
	
	@FindBy(id="submitButton")
	private WebElement login_button;
	
	@FindBy(className = "errorMessage")
	private WebElement errorMessage;
	
	// getters
	

	public WebElement getusername() 
	{
		ExplicitWait.waitForElementToVisible(driver, username);
		return username;
	}

	public WebElement getpassword()
	{
		ExplicitWait.waitForElementToVisible(driver, password);
		return password;
	}

	public void getUsername(String un) 
	{
		username.sendKeys(un);
	}

	public void getPassword(String pw)
	{
		password.sendKeys(pw);
		
	}

	public WebElement getLoginbutton() 
	{
		return login_button;
	}
	
	public String getPasswordFieldType() 
	{
        return getpassword().getAttribute("type");
    }
	
	public void loginDetails(String un, String pw)
	{
		ExplicitWait.waitForElementToVisible(driver,username).clear();
		username.sendKeys(un);
		ExplicitWait.waitForElementToVisible(driver,password).clear();
		password.sendKeys(pw);
		ExplicitWait.waitForElementToVisible(driver,login_button);
		login_button.click();
	}
	
	public String geterrorMessage() 
	{
		return errorMessage.getText();
	}
	
}
