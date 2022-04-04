package placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;

public class ReportForm extends Page {
    private String page;
    private String report_title;
//private HashMap<int,String>Incident_areas =new HashMap<int, String>();

    public ReportForm(String URL) {
        page = URL;
    }

    public ReportForm() {
        page = System.getProperty("getURL");
    }

    public String getPage() {
        return page;
    }

    public String input(WebDriver driver) {
        try {
            //WebDriverWait w = new WebDriverWait(driver, 10);
            //WebElement button=driver.findElementBy.className("line-input large"));
            // w.until(ExpectedConditions.visibilityOfElementLocated(By.className("line-input large")));
            WebElement button = driver.findElement(By.className("line-input large"));
            Thread.sleep(10000);
            button.click();
            button.sendKeys("QAMPERI SU KRALJEVI/CE");
            Thread.sleep(10000);

            button = driver.findElement(By.className("/html/body/div[3]/div[1]/div[3]/div/form/div/div[2]/div[1]/div/button"));
            Thread.sleep(10000);
            button.click();
            button = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[3]/div/form/div/div[2]/div[1]/div/ul/div/li[19]/a/label"));
            Thread.sleep(10000);

            button.click();
            button = driver.findElement(By.className("date-container"));
            Thread.sleep(10000);

            button.sendKeys("NAAJJJ REPORT");
            Thread.sleep(10000);

            button = driver.findElement(By.className("btn large-btn run-btn run-all-btn"));
            Thread.sleep(10000);

            button.click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver.getCurrentUrl();
    }


}
