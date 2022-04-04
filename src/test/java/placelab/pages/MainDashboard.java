package placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.reporters.jq.Main;

public class MainDashboard extends Page {
    private String host;

    public MainDashboard() {
        host = System.getProperty("host");
    }

    public MainDashboard(String host) {
        this.host = host;
    }

    public String getPage() {
        return host;
    }

    public void backTrace(WebDriver driver) {
        try {
            //Logout sequence should be implemented her
            WebElement CloseForm = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div/ul[2]/li"));
            CloseForm.click();
            CloseForm = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div/ul[2]/li/ul/li[4]"));
            CloseForm.click();
            Assert.assertEquals(driver.getCurrentUrl(), host);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String createQuery(WebDriver driver) {
        WebElement button = driver.findElement(By.className("dropdown"));
        button.click();
        button = driver.findElement(By.id("incidentaggregatedreport"));
        button.click();
        return driver.getCurrentUrl();
    }
}
