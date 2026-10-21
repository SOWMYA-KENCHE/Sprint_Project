package crm.vtiger.Opportunities;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClassCrm;
import genericUtility.DataFromExcelFile;
import objRepo.Home_Page;
import objRepo.Opportunities_Create_Page;
import objRepo.Opportunities_Page;

public class Smoke_TestCase_Oppo extends BaseClassCrm
{
	 @Test(groups = "smoke")
	    public void searchOpportunity() throws IOException, InterruptedException
	    {
	    	// Verify login
	        Home_Page home = new Home_Page(driver);
	        Assert.assertEquals(home.getCaltext().getText(),"Organizations");
	        Reporter.log("User is successfully logged in with valid credentials",true);
	        // Navigate to Opportunities page
	        Opportunities_Page oppo = new Opportunities_Page(driver);
	        oppo.getOpportunitiesLink().click();
	        // search for an opprtunity
	        DataFromExcelFile file = new DataFromExcelFile();
	        String name = file.getDataFromExcel("opportunities", 1, 0);
	        oppo.searchOpportunity(name);
	        oppo.getInoppo().click();
	        oppo.getsearchnowButton().click();
	        Assert.assertTrue(oppo.verifyOpportunity(name), "Search result should contain the requested opportunity name");
	        Reporter.log("searched opportunity is displayed ",true);
	    	
	    }
	 

}
