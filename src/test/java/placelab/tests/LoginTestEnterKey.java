package placelab.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import placelab.utilities.WebDriverSetup;

public class LoginTestEnterKey {
    public WebDriver driver;
    private String host = System.getProperty("host");
    private String homePageUrl = "https://demo.placelab.com/dashboard/traffic";
    private String password = System.getProperty("password");
    private String username = System.getProperty("username");
    private String user = "Damir";
    private String userRole = "Group Admin";

    //Specify the driver and browser that will be used for this scenario

    @BeforeSuite
    public void initDriver() {
        driver = WebDriverSetup.getWebDriver("opera");
    }

    //Actual test case implementation

    @Test
    public void testLoginPage() {

        //Go to PlaceLab demo app
        driver.navigate().to(host);

        //Validate that user is redirected to the right page
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");

        //fil out login parameters
        WebElement enterUsername = driver.findElement(By.id("email"));
        enterUsername.sendKeys(username);

        WebElement enterPassword = driver.findElement(By.id("password"));
        enterPassword.sendKeys(password);

        //Validate if Login button works with pressing Enter key on keyboard
        WebElement submit = driver.findElement(By.xpath("//input[@type='submit']"));
        submit.sendKeys(Keys.RETURN);

        //Validate that user is successfully logged in
        Assert.assertEquals(driver.getCurrentUrl(),homePageUrl);
        try {
            WebElement userName = driver.findElement(By.id("user-name"));
            assert (userName.getText().contains(user));
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("Expected user is not logged in!");
        }

        //Validate that user has right user role
        try {
            WebElement role = driver.findElement(By.id("user-role"));
            assert (role.getText().contains(userRole));
            throw new RuntimeException("Expected user role is not correct!");
        } catch (RuntimeException m) {
            m.printStackTrace();
        }

    }

    //Clean up - close the browser

    @AfterSuite
    public void quitDriver() {
        driver.close();
    }
}