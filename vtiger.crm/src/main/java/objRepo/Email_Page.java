package objRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Email_Page
{
    WebDriver driver;

    public Email_Page(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[text()='Compose']")
    private WebElement composeEmailBtn;
    
    @FindBy(xpath = "//input[@name='search_text']")
    private WebElement searchtext;
    
    @FindBy(xpath = "//input[@name='find']")
    private WebElement find;
    

	@FindBy(xpath = "//a[normalize-space()='To Contacts']")
	private WebElement toContactsLink;
	
	public void openToContacts()
	{
	    toContactsLink.click();
	}


    public WebElement clickComposeEmail()
    {
       return composeEmailBtn;
    }
    
    public WebElement getfoundfindvalue(String name)
    {
    	return driver.findElement(By.xpath("//b[text()='"+name+" "+"']"));
    	
    }
    
    public boolean getfoundfind(String name)
    {
    	String data = driver.findElement(By.xpath("//b[text()='"+name+" "+"']")).getText();
    	if(data.contains(name))
    	{
    		return true;
    	}
    	else
    		return false;
    }
    
    public void emailfind(String value)
    {
    	searchtext.sendKeys(value);
    	find.click();
    }
    
    public void getfind()
    {
    	find.click();
    }

    public boolean isComposeEmailDisplayed()
    {
        return composeEmailBtn.isDisplayed();
    }
    
    public void switchToComposeWindow()
    {
        String parent = driver.getWindowHandle();

        for (String window : driver.getWindowHandles())
        {
            if (!window.equals(parent))
            {
                driver.switchTo().window(window);
                break;
            }
        }
    }
}