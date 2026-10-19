package objRepo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtility.ExplicitWait;
import net.bytebuddy.asm.MemberSubstitution.FieldValue;

public class Opportunities_Details_Page
{
	WebDriver driver;
	public Opportunities_Details_Page(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


    @FindBy(xpath = "//span[@class='dvHeaderText']")
    private WebElement headerText;

    @FindBy(xpath = "//span[@id='dtlview_Opportunity Name']")
    private WebElement nameValue;

    @FindBy(xpath = "//span[@id='dtlview_Related To']")
    private WebElement relatedToValue;

    @FindBy(xpath = "//span[@id='dtlview_Expected Close Date']")
    private WebElement closeDateValue;

    @FindBy(xpath = "//span[@id='dtlview_Sales Stage']")
    private WebElement salesStageValue;

    @FindBy(xpath = "//span[@id='dtlview_Amount']")
    private WebElement amountValue;

    @FindBy(xpath = "//span[@id='dtlview_Probability']")
    private WebElement probabilityValue;

    @FindBy(xpath = "//span[@id='dtlview_Type']")
    private WebElement typeValue;

    @FindBy(xpath = "//span[@id='dtlview_Lead Source']")
    private WebElement leadSourceValue;

    @FindBy(xpath = "//span[@id='dtlview_Next Step']")
    private WebElement nextStepValue;

    @FindBy(xpath = "//span[@id='dtlview_Description']")
    private WebElement descriptionValue;

    @FindBy(xpath = "//input[@title='Edit [Alt+E]']")
    private WebElement editBtn;

    @FindBy(name = "Duplicate")
    private WebElement duplicateBtn;

    @FindBy(name = "Delete")
    private WebElement deleteBtn;
    
    @FindBy(xpath = "//a[normalize-space()='More Information']")
    private WebElement moreInformation;
    
    @FindBy(xpath = "//img[@id='show_Potentials_Products']")
    private WebElement productsdropdown;
    
    @FindBy(xpath = "//input[@title='Select Products']")
    private WebElement selectProductBtn;
    
    // Getters
    
    public void clickMoreInformation()
    {
        moreInformation.click();
    }
    
    public void productsdropdown() 
    {
    	ExplicitWait.waitForElementToVisible(driver, productsdropdown);
    	productsdropdown.click();
    }
    
    public void selectProductBtn() 
    {
    	ExplicitWait.waitForElementToVisible(driver, selectProductBtn);
    	selectProductBtn.click();
    }
    
    public void selectProduct(String productname)
    {
        driver.findElement(By.xpath("//a[text()='" + productname + "']")).click();
    }
    

    public String getHeaderText()      
    {
    	return headerText.getText();
    }
    public String getName()       
    {
    	return nameValue.getText(); 
    }
    public String getRelatedTo()  
    {
    	return relatedToValue.getText(); 
    }
    public String getCloseDate()       
    {
    	return closeDateValue.getText(); 
    }
    public String getSalesStage()     
    {
    	return salesStageValue.getText(); 
    }
    public String getAmount()         
    {
    	return amountValue.getText(); 
    }
    public String getDescription()   
    {
    	return descriptionValue.getText(); 
    }


    public void clickDelete()    { deleteBtn.click(); }

    
    public void selectDocument(String documentName) {
        Common_Page common = new Common_Page(driver);
        common.selectDocument(documentName);
    }
}
