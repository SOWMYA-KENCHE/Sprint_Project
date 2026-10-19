package objRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtility.ExplicitWait;

public class Products_Details_Page 
{
	// initialization

	WebDriver driver;
	public Products_Details_Page(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	// declare 
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
    private WebElement headerText;

    @FindBy(xpath = "//span[@id='dtlview_Product Name']")
    private WebElement productNameValue;

    @FindBy(xpath = "//span[@id='dtlview_Part Number']")
    private WebElement partNumberValue;

    @FindBy(xpath = "//span[@id='dtlview_Unit Price']")
    private WebElement unitPriceValue;
    
    @FindBy(xpath = "//a[normalize-space()='More Information']")
    private WebElement moreInformation;
    
    @FindBy(xpath = "//input[@title='Add Document']")
    private WebElement addDocumentBtn;

    @FindBy(xpath = "//input[contains(@value,'Select Document')]")
    private WebElement selectDocumentBtn;
    
    @FindBy(xpath = "//a[normalize-space()='Documents']")
    private WebElement documentselect;

    @FindBy(name = "Edit")
    private WebElement editBtn;

    @FindBy(name = "Duplicate")
    private WebElement duplicateBtn;

    @FindBy(name = "Delete")
    private WebElement deleteBtn;
    
    @FindBy(xpath = "//img[@id='show_Products_Documents']")
    private WebElement selectdropdowndoc;
    
    public void selectdropdowndoc()
    {
        ExplicitWait.waitForElementToVisible(driver, selectdropdowndoc);
        selectdropdowndoc.click();
    }
    public void getdocumentselect() {
        ExplicitWait.waitForElementToVisible(driver, documentselect);
        documentselect.click();
    }
    
    public void clickAddDocument() 
    {
    	ExplicitWait.waitForElementToVisible(driver, addDocumentBtn);
        addDocumentBtn.click();
    }
    
    public String getHeaderText()    
    {
    	return headerText.getText(); 
    }
    public String getProductNamevalue()    
    {
    	return productNameValue.getText(); 
    }
    
    public String getProductName1(String name)    
    {
    	return driver.findElement(By.xpath("//span[text()='"+name+"']")).getText();
    }
    public String getPartNumber()    
    {
    	return partNumberValue.getText(); 
    }
    
    public WebElement getmoreInformation()    
    {
    	return moreInformation; 
    }
    
    public String getUnitPrice() 
    {
    	return unitPriceValue.getText(); 
    }

    public void clickEdit()   
    {
    	editBtn.click(); 
    }
    public void clickDuplicate()  
    {
    	duplicateBtn.click(); 
    }
    public void clickDelete()      
    {
    	deleteBtn.click(); 
    }

    public boolean isEditDisplayed()  
    {
    	return editBtn.isDisplayed(); 
    }
    public boolean isDuplicateDisplayed()
    {
    	return duplicateBtn.isDisplayed();
    }
    public boolean isDeleteDisplayed()   
    {
    	return deleteBtn.isDisplayed();
    }
}
