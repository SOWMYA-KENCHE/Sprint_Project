package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Documents_Details_Page 
{
	// initialization

	WebDriver driver;
	public Documents_Details_Page(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	// declare 
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
    private WebElement headerText;

    @FindBy(xpath = "//span[@id='dtlview_Title']")
    private WebElement titleValue;

    @FindBy(name = "Edit")
    private WebElement editBtn;
    
    @FindBy(xpath = "//input[@title='Edit [Alt+E]']")
    private WebElement editBtn1;

    @FindBy(name = "Delete")
    private WebElement deleteBtn;

    public String getHeaderText()
    {
    	return headerText.getText(); 
    }
    public String getTitle()  
    {
    	return titleValue.getText(); 
    }
    public void clickEdit()      
    {
    	editBtn.click();
    }
    
    public void clickEdit1()      
    {
    	editBtn1.click();
    }
    public void clickDelete()    
    {
    	deleteBtn.click(); 
    }

}
