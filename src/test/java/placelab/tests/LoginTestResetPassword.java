package placelab.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import placelab.utilities.WebDriverSetup;

public class LoginTestResetPassword {
    public WebDriver driver;
    private String host = System.getProperty("host");
    private String forgotPassUrl = "https://demo.placelab.com/password/forgot";
    private String password = System.getProperty("password");
    private String username = System.getProperty("username");
    private String forgotPassTextOne = "Change your password";
    private String forgotPassTextTwo = "Let's find your account";

    //Specify the driver and browser that will be used for this scenario

    @BeforeSuite
    public void initDriver() {
        driver = WebDriverSetup.getWebDriver("opera");
    }

    //Actual test case implementation

    @Test
    public void testPasswordReset() {

        //Go to PlaceLab demo app
        driver.navigate().to(host);

        //Validate that user is redirected to the right page
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");

        //Click on Forgot your password? link
        WebElement forgotPassword = driver.findElement(By.xpath("//div[@ id = 'password-area']/a"));
        forgotPassword.click();

        //Validate that the Forgot your password site opens
        Assert.assertEquals(driver.getCurrentUrl(),forgotPassUrl);

        WebElement textOne = driver.findElement(By.xpath("//div[@id = 'login']/p"));
        textOne.getText().contains(forgotPassTextOne);

        WebElement textTwo = driver.findElement(By.xpath("//div[@id = 'login']/p/small"));
        textOne.getText().contains(forgotPassTextTwo);
    }

    //Clean up - close the browser

    @AfterSuite
    public void quitDriver() {
        driver.close();
    }
}