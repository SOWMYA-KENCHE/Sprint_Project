package objRepo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtility.ExplicitWait;

public class Common_Page 
{

	WebDriver driver;
	public Common_Page(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
    @FindBy(id = "bas_searchfield")
    private WebElement searchFieldDd;

    @FindBy(name = "search_text")
    private WebElement searchTb;

    @FindBy(xpath = "//input[contains(@value,'Search Now')]")
    private WebElement searchNowBtn;

    @FindBy(xpath = "//a[text()='More Information']")
    private WebElement moreInfoTab;

    @FindBy(xpath = "//input[@title='Add Document']")
    private WebElement addDocumentBtn;

    @FindBy(xpath = "//input[contains(@value,'Select Document')]")
    private WebElement selectDocumentBtn;
    
    @FindBy(id = "show_Accounts_Documents")
    private WebElement selectdropdowndoc;
    
    @FindBy(xpath = "(//input[@type='text'])[2]")
	private WebElement searchText;
    
    @FindBy(xpath = "(//option[text()='Organization Name'])[1]")
    private WebElement inoppo;
    
    @FindBy(xpath = "//input[@class='crmbutton small create']")
    private WebElement searchnow_button;
    
    public void searchnany(String name) throws InterruptedException 
    {
        searchText.clear();
        searchText.sendKeys(name);
        inoppo.click();
        searchnow_button.click();
        Thread.sleep(2000);
    }
    
    public void selectdropdowndoc()
    {
        ExplicitWait.waitForElementToVisible(driver, selectdropdowndoc);
        selectdropdowndoc.click();
    }

    public void clickMoreInformation()
    {
        ExplicitWait.waitForElementToVisible(driver, moreInfoTab);
        moreInfoTab.click();
    }
    
    public void clickAddDocument()
    {
        ExplicitWait.waitForElementToVisible(driver, addDocumentBtn);
        addDocumentBtn.click();
    }
    
    public boolean isTextPresent(String text) {
        return driver.findElements(
            By.xpath("//*[contains(normalize-space(text()),'" + text + "')]")
        ).size() > 0;
    }

    public boolean isRecordPresent(String name) {
        return !driver.findElements(By.xpath("//a[text()='" + name + "']")).isEmpty();
    }

    public void clickRecord(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(d -> {
            List<WebElement> records = d.findElements(
                By.xpath("//a[normalize-space()='" + name + "']")
            );

            for (WebElement record : records) {
                try {
                    if (record.isDisplayed() && record.isEnabled()) {
                        record.click();
                        return true;
                    }
                } catch (ElementNotInteractableException |
                         StaleElementReferenceException e) {
                }
            }

            return false;
        });
    }
    public void selectDocument(String title) {
        String parent = driver.getWindowHandle();

        selectDocumentBtn.click();

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(parent)) {
                driver.switchTo().window(window);
                break;
            }
        }

        driver.findElement(By.id("search_txt")).sendKeys(title);
        driver.findElement(By.id("search_txt")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//a[normalize-space()='" + title + "']")).click();

        driver.switchTo().window(parent);
    }
}
