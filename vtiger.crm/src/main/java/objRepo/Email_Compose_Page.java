package objRepo;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import genericUtility.ExplicitWait;

public class Email_Compose_Page
{
    WebDriver driver;

    public Email_Compose_Page(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "parent_name")
    private WebElement toField;
    
    @FindBy(xpath = "//input[@name='search_text']")
    private WebElement searchbtn;
    
    @FindBy(xpath = "//select[@name='search_field']")
    private WebElement inlastname;
    
    @FindBy(xpath = "//option[@label='First Name']")
    private WebElement firstnameincontact;
    
    @FindBy(xpath = "//input[@type='button']")
    private WebElement contactsearch;
    
    @FindBy(xpath = "//img[@alt='Select']")
    private WebElement createto;

    @FindBy(name ="ccmail")
    private WebElement ccField;

    @FindBy(id = "bcc_name")
    private WebElement bccField;

    @FindBy(xpath = "//input[@id='subject']")
    private WebElement subjectField;

    @FindBy(name = "cke_contents_description")
    private WebElement messageBody;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement attachmentField;

    @FindBy(xpath = "//input[@name='Send']")
    private WebElement sendBtn;
    
    @FindBy(xpath = "//input[@title='Save [Alt+S]']")
    private WebElement savebtn;
    
    @FindBy(xpath = "//select")
    private WebElement recipientType;
    
    @FindBy(xpath = "//select[option[normalize-space()='Contacts']]/following-sibling::img")
    private WebElement toLookupBtn;
    
    
    @FindBy(xpath = "(//option[text()='Organization Name'])[4]")
    private WebElement inoppo;
    
    @FindBy(xpath = "//input[@class='crmbutton small create']")
    private WebElement searchnow_button;
    
    @FindBy(name = "search_text")
    private WebElement searchText;
    
    
    public void chooseFile(String path) {
        File file = new File(path);

        if (!file.exists()) {
            throw new RuntimeException("File not found: " + path);
        }

        attachmentField.sendKeys(file.getAbsolutePath());
    }
    
    public void selectto(String name) 
    {
    	ExplicitWait.waitForElementToVisible(driver, searchText);
        searchText.clear();
        searchText.sendKeys(name);
//        inoppo.click();
        searchnow_button.click();
    }

    public void selectContact(String name)
    {
        driver.findElement(
            By.xpath("//a[normalize-space()='" + name + "']")
        ).click();
    }
    
    public void selectContactSearch(String name)
    {
    	searchbtn.sendKeys(name);
    	inlastname.click();
    	firstnameincontact.click();
    	contactsearch.click();
    }
    
    public WebElement getcreateto()
    {
    	ExplicitWait.waitForElementToVisible(driver, createto);
    	return createto;
    }
    
    public void getsavebtn()
    {
    	savebtn.click();
    }
    
    public void clickToLookup()
    {
        toLookupBtn.click();
    }

    public boolean isToDisplayed()
    {
        return toField.isDisplayed();
    }

    public boolean isCCDisplayed()
    {
        return ccField.isDisplayed();
    }

    public boolean isBCCDisplayed()
    {
        return bccField.isDisplayed();
    }

    public boolean isSubjectDisplayed()
    {
        return subjectField.isDisplayed();
    }

    public boolean isMessageBodyDisplayed()
    {
        return messageBody.isDisplayed();
    }

    public boolean isAttachmentDisplayed()
    {
        return attachmentField.isDisplayed();
    }

    public boolean isSendDisplayed()
    {
        return sendBtn.isDisplayed();
    }
    
	public String getToValue()
	{
	    return toField.getAttribute("value");
	}


    public void selectRecipientType(String type)
    {
        Select select = new Select(recipientType);
        select.selectByVisibleText(type);
    }


	public void attachFile(String filePath)
	{
	    attachmentField.sendKeys(filePath);
	}

    public void enterSubject(String subject)
    {
        subjectField.sendKeys(subject);
    }

    public void enterCC(String email)
    {
        ccField.sendKeys(email);
    }

    public void enterBCC(String email)
    {
        bccField.sendKeys(email);
    }

    public void clickSend()
    {
        sendBtn.click();
    }
    
    public void enterMessage(String message)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
            "CKEDITOR.instances['description'].setData(arguments[0]);",
            message
        );
    }
}