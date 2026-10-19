package genericUtility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotUtility1 {

    public void takeScreenshot(WebDriver driver, String screenshotName)
    {
        // Convert driver to takess
        TakesScreenshot ts = (TakesScreenshot) driver;
        // take ss 
        File source = ts.getScreenshotAs(OutputType.FILE);
        // ss folder
        File folder = new File("screenshots");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        // des file
        File destination =new File(folder, screenshotName + ".png");
        try {
            Files.copy(source.toPath(),destination.toPath(),StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved at: "+ destination.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}