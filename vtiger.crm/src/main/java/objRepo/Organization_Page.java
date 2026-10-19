package objRepo;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtility.ExplicitWait;

import java.time.Duration;
import java.util.List;

public class Organization_Page
{
    private final WebDriver driver;
    
    public Organization_Page(WebDriver driver) 
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[text()='Organizations']")
    private WebElement organizationLink;
    
    @FindBy(name = "search_text")
    private WebElement searchText;
    
    @FindBy(xpath = "(//option[text()='Organization Name'])[1]")
    private WebElement inoppo;
    
    @FindBy(xpath = "//input[@class='crmbutton small create']")
    private WebElement searchnow_button;
    
    @FindBy(xpath = "//span[@id='dtlview_Organization Name']")
    private WebElement orgname;
    
    public WebElement getorganizationLink()
    {
    	ExplicitWait.waitForElementToVisible(driver, organizationLink);
    	return organizationLink;
    }
    
    public WebElement getorgname()
    {
    	return orgname;
    }
    
    public void searchorganization(String name) throws InterruptedException 
    {
        searchText.clear();
        searchText.sendKeys(name);
        inoppo.click();
        searchnow_button.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[normalize-space()='" + name + "']")).click();
    }
    
    public void searchorganization1(String name) throws InterruptedException 
    {
        searchText.clear();
        searchText.sendKeys(name);
        inoppo.click();
        searchnow_button.click();
    }
    
}
