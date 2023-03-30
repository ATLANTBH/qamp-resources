package placelab.tests;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import placelab.pages.HomePage;
import placelab.pages.Login;
import placelab.utilities.WebDriverSetup;

import java.time.Duration;

public class LoginTest {
    public WebDriver driver;
    private SoftAssert softAssert = new SoftAssert();
    private String host = System.getProperty("host");
    private String user = System.getProperty("user");
    private String username = System.getProperty("username");
    private String password = System.getProperty("password");
    private String homePageUrl = System.getProperty("homepage");
    private Login login;
    private HomePage homePage;


    //Specify the driver and browser that will be used for this scenario

    @Parameters({"browser"})

    @BeforeSuite(alwaysRun = true)
    public void initDriver(String browser) {

        driver = WebDriverSetup.getWebDriver(browser);
        login = new Login(driver);
        homePage = new HomePage(driver);
    }

    @BeforeTest(alwaysRun = true, groups = {"Positive, Negative"}, description = "Verify that user is able to open " +
            "PlaceLab App.")
    public void openApp() {

        //Go to PlaceLab demo app
        driver.navigate().to(host);

        //Validate that user is redirected to the right page
        login.verifyLoginPage(host);


    }

    //Actual test case implementation

    @Test(priority = 1, groups = {"Positive"}, description = "This test verifies that user is able to log in to " +
            "PlaceLab with valid credentials.", suiteName = "Smoke Test")
    public void testLoginPagePositive() throws InterruptedException {

        //Fill out login parameters
        login.enterUsername(username);
        login.enterPassword(password);

        //Click on Login button
        login.submit();

        //Validate that user is successfully logged in
        Assert.assertEquals(driver.getCurrentUrl(), homePageUrl);

        assert (homePage.getUser().contains(user));
        softAssert.assertEquals(homePage.getUserRole(), "Basic User", "Logged user has invalid user role !");
        homePage.signOut();
        Assert.assertEquals(driver.getCurrentUrl(), host);

        softAssert.assertAll();
    }


    @Test(priority = 2, groups = {"Negative"}, description = "This test verifies that user can not log in with " +
            "invalid Password.")
    public void testLoginPageInvalidPassword() {

        //Fill out login parameters
        login.enterUsername(username);
        login.enterPassword("Random123!");

        //Click on Login button
        login.submit();

    }

    @Test(priority = 3, groups = {"Negative"}, description = "This test verifies that user can not log in with " +
            "invalid Username.")
    public void testLoginPageInvalidUsername() {

        //Fill out login parameters
        login.enterUsername("qamp.user@gmail.com");
        login.enterPassword(password);

        //Click on Login button
        login.submit();

    }

    @AfterTest(dependsOnGroups = {"Negative"}, description = "Verify that user is not logged in")
    public void failedLogin() {

        //Validate that user is not logged in
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(login.getErrorMessage(), "Invalid credentials!", "User " +
                "should not be able to Log In with invalid credentials !");
    }

    @AfterTest(dependsOnGroups = {"Positive"}, description = "Verify that user can sign out!")
    public void signOut() {

        //Validate that user is logged out
        homePage.signOut();

        Assert.assertEquals(driver.getCurrentUrl(), host);

    }


    //Clean up - close the browser

    @AfterSuite(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
    }
}