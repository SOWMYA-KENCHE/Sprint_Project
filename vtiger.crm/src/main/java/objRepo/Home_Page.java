package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtility.ExplicitWait;

public class Home_Page 
{
	// initialization

	WebDriver driver;
	public Home_Page(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	// declare 
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement profile;
	
	@FindBy(xpath = "//a[text()='Sign Out']")
	private WebElement signout;
	
	@FindBy(xpath = "//a[normalize-space()='Products']")
	private WebElement productlink;
	
	@FindBy(xpath = "//a[normalize-space()='Documents']")
	private WebElement documentslink;
	
	@FindBy(xpath = "//a[normalize-space()='Email']")
	private WebElement emaillink;
	
	@FindBy(xpath = "//a[normalize-space()='Contacts']")
	private WebElement Contactslink;
	
	@FindBy(xpath = "//a[normalize-space()='Organizations']")
	private WebElement Organizationslink;
	
	 @FindBy(xpath = "//a[text()='Opportunities']")
	 private WebElement opportunitiesLink;
	 
	 @FindBy(name = "query_string")
	 private WebElement search;
	 
	 @FindBy(xpath = "//a[text()='Organizations']")
	 private WebElement calendertext;
	
	 // getters
	
	 public WebElement getsearch()
	 {
			return search;
	 }
	 
	 public WebElement getOrganizationslink() 
	 {
		 	ExplicitWait.waitForElementToVisible(driver, Organizationslink);
			return Organizationslink;
	 }
	 
	 public WebElement getproductlink() 
	 {
			return productlink;
	 }
	 
	 public WebElement getdocumentslink() 
	 {
			return documentslink;
	 }
	 
	 public WebElement getemaillink() 
	 {
			return emaillink;
	 }
	 
	 public WebElement getopportunitiesLink() 
	 {
			return opportunitiesLink;
	 }
	
	public WebElement getProfile() 
	{
		ExplicitWait.waitForElementToVisible(driver, profile);
		return profile;
	}

	public WebElement getSignout() 
	{
		ExplicitWait.waitForElementToVisible(driver, signout);
		return signout;
	}
	
	public WebElement getCaltext() {
		return calendertext;
	}
   
}
