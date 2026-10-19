package objRepo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Opportunities_Page {

    WebDriver driver;

    public Opportunities_Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Declare WebElements

    @FindBy(xpath = "//a[text()='Opportunities']")
    private WebElement opportunitiesLink;

    @FindBy(xpath = "//img[@alt='Create Opportunity...']")
    private WebElement createOpportunityButton;

    @FindBy(name = "search_text")
    private WebElement searchText;
    
    @FindBy(xpath = "(//option[text()='Opportunity Name'])[1]")
    private WebElement inoppo;
    
    @FindBy(xpath = "//input[@class='crmbutton small create']")
    private WebElement searchnow_button;
    
    @FindBy(xpath = "//input[@class='crmbutton small delete']")
    private WebElement deleteButton;
    
    @FindBy(xpath = "//a[normalize-space()='More Information']")
    private WebElement moreInformation;
    
    // Getters


    public void searchOpportunity(String name) {
        searchText.clear();
        searchText.sendKeys(name);
    }
    
    
    public void clickMoreInformation() {
        moreInformation.click();
    }
    
    public WebElement getOpportunitiesLink()
    {
        return opportunitiesLink;
    }

    public WebElement getCreateOpportunityButton()
    {
        return createOpportunityButton;
    }
    
    public WebElement getSearchText() {
        return searchText;
    }

    public WebElement getInoppo() {
        return inoppo;
    }
    
    public void searchOpportunityfull(String name) 
    {
        searchText.clear();
        searchText.sendKeys(name);
        inoppo.click();
        searchnow_button.click();
    }
    
    public WebElement getsearchnowButton() {
        return searchnow_button;
    }
    
    public boolean verifyOpportunity(String name) 
    {
        return new WebDriverWait(driver, Duration.ofSeconds(20)).until(d -> {
            try { return d.findElements(By.xpath("//table[contains(@class,'listTable')]//tr | //table[@class='lvt small']//tr")).stream().anyMatch(r -> r.getText().contains(name)); }
            catch (Exception e) { return false; }
        });
    }
    
    public WebElement getDeleteButton() 
    {
        return deleteButton;
    }


    public boolean isOpportunityPresent(String name) 
    {
        return !driver.findElements(By.xpath("//a[normalize-space()='"+ name + "']")).isEmpty();
    }
    
    public void getcheckbox(String name) 
    {
    	driver.findElement(By.xpath("//a[normalize-space()='"+ name +"']/../..//input[@type='checkbox']")).click();
        
    }
    
    public void clickRecord(String name) {
        driver.findElement(
            By.xpath("//a[normalize-space()='" + name + "']")
        ).click();
    }

    
}