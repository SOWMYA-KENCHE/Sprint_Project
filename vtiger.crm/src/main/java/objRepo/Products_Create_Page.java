package objRepo;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtility.ExplicitWait;

public class Products_Create_Page 
{
	// initialization

	WebDriver driver;
	public Products_Create_Page(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	// declare 
	
	@FindBy(xpath = "//a[normalize-space()='Products']")
	private WebElement productlink;
	
	@FindBy(xpath = "//img[@alt='Create Product...']")
	private WebElement createProduct;
	
    @FindBy(name="productname") 
    private WebElement productName;
    
    @FindBy(xpath = "//option[text()='Product Name']")
    private WebElement prodctsearchin;
    
    @FindBy(xpath = "(//input[@type='text'])[2]")
	private WebElement searchText;
    
    @FindBy(xpath = "//input[@class='crmbutton small create']")
    private WebElement searchnow_button;
    
    @FindBy(name="productcode") 
    private WebElement productCode;
    
    @FindBy(name="unit_price") 
    private WebElement unitPrice;
    
    @FindBy(name="description")
    private WebElement description;
    
    @FindBy(xpath = "//input[@title='Save [Alt+S]']")
    private WebElement saveBtn;
    
    public void productsearchlink(String name) throws InterruptedException 
    {
        searchText.clear();
        searchText.sendKeys(name);
        prodctsearchin.click();
        searchnow_button.click();
        Thread.sleep(2000);
    }
    
    public void clickSave() {
        saveBtn.click();
    }
    
    public WebElement getProductlink() {
		return productlink;
	}

	public WebElement getCreateProduct() {
		return createProduct;
	}

	public void getProductName(String name) 
	{
		ExplicitWait.waitForElementToVisible(driver, productName);
		productName.clear();
		productName.sendKeys(name);
	}

	public void getProductCode(String name)
	{
		ExplicitWait.waitForElementToVisible(driver, productCode);
		productCode.clear();
		productCode.sendKeys(name);
	}

	public void getUnitPrice(String name) 
	{
		ExplicitWait.waitForElementToVisible(driver, unitPrice);
		unitPrice.clear();
		unitPrice.sendKeys(name);
	}

	public WebElement getDescription(String name) {
		return description;
	}
	
	public boolean isFormDisplayed() 
	{
        try 
        {	
            return saveBtn.isDisplayed();
        } 
        catch (NoSuchElementException e) {
            return false;
        }
    }

	@FindBy(name = "vendor_name")
	private WebElement vendorNameTb;

	@FindBy(xpath = "//img[contains(@onclick,'Vendors')]")
	private WebElement vendorLookupBtn;

	public void selectVendor(String vendorName) {

	    String parentWindow = driver.getWindowHandle();

	    vendorLookupBtn.click();

	    WebDriverWait wait =
	        new WebDriverWait(driver, Duration.ofSeconds(15));

	    wait.until(d -> d.getWindowHandles().size() > 1);

	    for (String window : driver.getWindowHandles()) {
	        if (!window.equals(parentWindow)) {
	            driver.switchTo().window(window);
	            break;
	        }
	    }

	    WebElement searchBox = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(
	            By.name("search_text")
	        )
	    );

	    searchBox.clear();
	    searchBox.sendKeys(vendorName, Keys.ENTER);

	    WebElement vendor = wait.until(
	        ExpectedConditions.elementToBeClickable(
	            By.xpath("//a[normalize-space()='" + vendorName + "']")
	        )
	    );

	    vendor.click();

	    driver.switchTo().window(parentWindow);
	}

	public String getVendorName() {
	    return vendorNameTb.getAttribute("value");
	}
	
	
}
