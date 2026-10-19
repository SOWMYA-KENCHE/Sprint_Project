package objRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtility.ExplicitWait;

public class Products_Page 
{
	// initialization

	WebDriver driver;
	public Products_Page(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	// declare 
	
	@FindBy(xpath = "//a[normalize-space()='Products']")
	private WebElement productlink;
	
	@FindBy(xpath = "//img[@alt='Create Product...']")
	private WebElement createProduct;
	
	@FindBy(xpath = "(//input[@type='text'])[2]")
	private WebElement search;
	
	@FindBy(xpath = "//div[@id='basicsearchcolumns_real']/..//option[text()='Product Name']")
	private WebElement inproduct;
	
	@FindBy(xpath = "//input[@class='crmbutton small create']")
	private WebElement searchnow;
	
	public void getsearch(String value)
	{
		search.clear();
		search.sendKeys(value);
		search.sendKeys(Keys.ENTER);
	}
	public void getsearchfull(String value)
	{
		search.clear();
		search.sendKeys(value);
		search.sendKeys(Keys.ENTER);
		inproduct.click();
		searchnow.click();
	}
    
    public WebElement getInproduct()
    {
		return inproduct;
	}

	public WebElement getSearchnow() 
	{
		return searchnow;
	}

	public WebElement getProductlink()
    {
		return productlink;
	}

	public WebElement getCreateProduct() {
		return createProduct;
	}	
	
	public void clickRecord(String name) {
        driver.findElement(By.xpath("//a[text()='" + name + "']")).click();
    }
	
	public boolean isRecordPresent(String name) {
        return !driver.findElements(By.xpath("//a[text()='" + name + "']")).isEmpty();
    }
}
