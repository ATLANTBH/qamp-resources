package placelab.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import placelab.utilities.WebDriverSetup;

public class LoginTest {
    public WebDriver driver;
    private String host = System.getProperty("host");
    private String username=System.getProperty("username");
    private String password=System.getProperty("password");
    private String homePage=System.getProperty("home");


    //Specify the driver and browser that will be used for this scenario

    @BeforeSuite
    public void initDriver() {

        driver = WebDriverSetup.getWebDriver("firefox");

    }

    //Actual test case implementation

    @Test
    public void testLoginPage() {

        //Go to PlaceLab demo app
        driver.navigate().to(host);
        //Validate that user is redirected to the right page
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");
        WebElement Name = driver.findElement(By.cssSelector("input[placeholder$='Email']"));
        Name.sendKeys(username);
        WebElement PassWord = driver.findElement(By.name("password"));
        PassWord.sendKeys(password);
        WebElement LoginButton=driver.findElement(By.cssSelector("input[value$='Log in']"));
        LoginButton.click();
        Assert.assertEquals(driver.getCurrentUrl(),homePage); //validate again that the user is redirected to the
        // correct webpage
    }

    //Clean up - close the browser

    @AfterSuite
    public void quitDriver() {
     //   driver.close();
    }


}