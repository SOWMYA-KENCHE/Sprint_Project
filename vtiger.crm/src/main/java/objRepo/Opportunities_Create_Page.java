package objRepo;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import genericUtility.ExplicitWait;

public class Opportunities_Create_Page {

    WebDriver driver;

    public Opportunities_Create_Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Declare WebElements

    @FindBy(name = "potentialname")
    private WebElement opportunityName;
    
    @FindBy(xpath = "//img[@language='javascript']/.")
    private WebElement relatedto;
    
    @FindBy(xpath = "//input[@name='probability']")
    private WebElement probability;
    
    @FindBy(id = "search_txt")
    private WebElement enterrelatedto;

    @FindBy(xpath = "//a[normalize-space()='testing']")
    private WebElement relatedname;

    @FindBy(name = "amount")
    private WebElement amount;

    @FindBy(name = "closingdate")
    private WebElement closingDate;

    @FindBy(name = "sales_stage")
    private WebElement salesStage;
    
    @FindBy(xpath = "//option[@value='Closed Won']")
    private WebElement newsalesStage;

    @FindBy(xpath = "//input[@title='Save [Alt+S]']")
    private WebElement saveButton;
    
    @FindBy(xpath = "//span[contains(@class,'dvHeaderText')]")
    private WebElement verifyCreate;
    
    @FindBy(xpath = "//input[@id='search_txt']")
    private WebElement searchtext;
    
    @FindBy(xpath = "//select[@name='search_field']")
    private WebElement insearch;
    
    @FindBy(xpath = "//input[@type='button']")
    private WebElement searchnowbutton;
    
    @FindBy(xpath = "//input[@title='Edit [Alt+E]']")
    private WebElement editBtn;
    
    // Getters
    
    public WebElement getnewsalesStage()
    {
    	return driver.findElement(By.xpath("//option[@value='Shutdown']"));
    }
    
    public void clickEdit()      
    {
    	ExplicitWait.waitForElementToVisible(driver, editBtn);
    	editBtn.click(); 
    }

	public void selectproduct(String name) throws InterruptedException 
	{
	
	    String parentWindow = driver.getWindowHandle();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    // Wait until the Products popup opens
	    wait.until(d -> d.getWindowHandles().size() > 1);
	    // Switch to the Products popup
	    for (String window : driver.getWindowHandles()) {
	        if (!window.equals(parentWindow)) {
	            driver.switchTo().window(window);
	            break;
	        }
	    }
	
	    // Locate and enter product name
	    ExplicitWait.waitForElementToVisible(driver, searchtext);
	
	    searchtext.clear();
	    searchtext.sendKeys(name);
	
	    // Select Product Name in the dropdown
	    ExplicitWait.waitForElementToVisible(driver, insearch);
	    insearch.click();
	
	    ExplicitWait.waitForElementToVisible(driver, searchnowbutton);
	
	    searchnowbutton.click();
	   // Wait for the exact product result
	    By productLocator = By.xpath(
	        "//a[normalize-space()='" + name + "']"
	    );

	    WebElement product = wait.until(
	        ExpectedConditions.elementToBeClickable(productLocator)
	    );

	    Reporter.log("Product found: " + name, true);

	    product.click();
	    
	    wait.until(d ->
        d.getWindowHandles().contains(parentWindow)
	    		);

    driver.switchTo().window(parentWindow);
	
	    Reporter.log("Searched for product: " + name, true);
	}

    public void getOpportunityName(String name)
    {
    	opportunityName.clear();
        opportunityName.sendKeys(name);
    }
    
    public WebElement getOpportunityNameField() {
        return opportunityName;
    }
    
    public void getprobability(String value) {
        probability.sendKeys(value);;
    }
    
    public WebElement getRelatedto() {
        return ExplicitWait.waitForElementToVisible(driver, relatedto);
    }

    public void selectOrganization(String orgName) 
    {
    	String parentWindow = driver.getWindowHandle();
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
         wait.until(driver ->driver.getWindowHandles().size() > 1);
        // Switch to the popup window
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        // Locate the popup search field
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_txt")));
        searchBox.clear();
        searchBox.sendKeys(orgName);
        searchBox.sendKeys(Keys.ENTER);
        // Select the matching organization
        WebElement organization = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='" + orgName + "']")));
        organization.click();
        // Return to the main Opportunities window
        driver.switchTo().window(parentWindow);
    }

    public void getAmount(String amt) {
    	amount.clear();
    	amount.sendKeys(amt);
    }

    public void getClosingDate(String cdate) 
    {
        closingDate.sendKeys(cdate);;
    }

    public void getSalesStage(String value) 
    {
		Select s2 = new Select(salesStage);
		s2.selectByValue(value);
        
    }

    public WebElement getSaveButton() {
        return saveButton;
    }
    
    public WebElement getverifyCreate()
    {
    	return verifyCreate;
    }
   
    
    
}