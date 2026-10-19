package objRepo;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtility.ExplicitWait;

public class Documents_Page 
{
	// initialization

	WebDriver driver;
	public Documents_Page(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	// declare 
	
	 @FindBy(xpath = "//img[@alt='Create Document...']")
	 private WebElement createDocumentImg;

	    @FindBy(name = "notes_title")
	    private WebElement titleTb;

	    @FindBy(xpath = "//input[@title='Save [Alt+S]']")
	    private WebElement saveBtn;
	    
	    @FindBy(xpath = "//input[@type='file']")
	    private WebElement fileInput;
	    
	    @FindBy(name = "search_text")
	    private WebElement searchTb;

	    @FindBy(xpath = "//input[@name='submit']")
	    private WebElement searchBtn;
	    
	    @FindBy(xpath = "(//option[text()='Title'])[1]")
	    private WebElement selectin;
	    
	    @FindBy(xpath = "//input[@type='checkbox']")
	    private WebElement check;
	  
	    
	    public void check()
	    {
	    	check.click();
	    }
	    
	    public WebElement gettitle()
	    {
	    	return titleTb;
	    }
	    public void searchDocument(String title) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	        WebElement searchBox = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                By.name("search_text")
	            )
	        );

	        searchBox.clear();
	        searchBox.sendKeys(title);

	        Select dropdown = new Select(
	            wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("(//select[option[normalize-space()='Title']])[1]")
	            ))
	        );

	        dropdown.selectByVisibleText("Title");

	        wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//input[@name='submit']")
	        )).click();
	    }
	    
	    public void editTitle(String title)
	    {
	        titleTb.clear();
	        titleTb.sendKeys(title);
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
	                    // Retry while waiting for an interactable record
	                }
	            }

	            return false;
	        });
	    }

	    public void chooseFile1(String path) {
	        fileInput.sendKeys(path);
	    }
	    
	    public void chooseFile(String path) {
	        File file = new File(path);

	        if (!file.exists()) {
	            throw new RuntimeException("File not found: " + path);
	        }

	        fileInput.sendKeys(file.getAbsolutePath());
	    }
	    
	    public void createDocument(String title, String filePath) {
	        enterTitle(title);
	        chooseFile(filePath);
	        clickSave();
	    }
	    
	    public boolean isRecordPresent(String name) {
	        return !driver.findElements(By.xpath("//a[text()='" + name + "']")).isEmpty();
	    }

	    public boolean isFileSizeErrorDisplayed() {
	        return driver.findElements(
	            By.xpath("//*[contains(translate(text(),'FILE SIZE','file size'),'file size')"
	                   + " or contains(translate(text(),'TOO LARGE','too large'),'too large')]")
	        ).size() > 0;
	    }
	    
	    public void clickCreateDocument() {
	        createDocumentImg.click();
	    }

	    public boolean isCreateDocumentDisplayed() {
	        return createDocumentImg.isDisplayed();
	    }
	    
	    public void enterTitle(String title) {
	        titleTb.clear();
	        if (!title.isEmpty()) {
	            titleTb.sendKeys(title);
	        }
	    }

	    public void clickSave() {
	        saveBtn.click();
	    }

	    public boolean isFormDisplayed() {
	        try {
	            return saveBtn.isDisplayed();
	        } catch (NoSuchElementException e) {
	            return false;
	        }
	    }
	    
}
