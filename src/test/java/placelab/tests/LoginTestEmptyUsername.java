package placelab.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import placelab.utilities.WebDriverSetup;

public class LoginTestEmptyUsername {
    public WebDriver driver;
    private String host = System.getProperty("host");
    private String password = System.getProperty("password");
    private String credentialsError = "Invalid credentials!";

    //Specify the driver and browser that will be used for this scenario

    @BeforeSuite
    public void initDriver() {
        driver = WebDriverSetup.getWebDriver("opera");
    }

    //Actual test case implementation

    @Test
    public void testLoginWithoutUsername() {

        //Go to PlaceLab demo app
        driver.navigate().to(host);

        //Validate that user is redirected to the right page
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");

        //fil out login parameters
        WebElement enterPassword = driver.findElement(By.id("password"));
        enterPassword.sendKeys(password);

        //Click on Login button
        WebElement submit = driver.findElement(By.xpath("//input[@type='submit']"));
        submit.click();

        //Validate that the user cannot sign in with username only
        Assert.assertEquals(driver.getCurrentUrl(),host);

        WebElement error = driver.findElement(By.xpath("//div[@class = 'error-area']"));
        error.getText().contains(credentialsError);
    }

    //Clean up - close the browser

    @AfterSuite
    public void quitDriver() {
        driver.close();
    }
}