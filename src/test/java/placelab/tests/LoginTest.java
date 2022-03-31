package placelab.tests;

import org.checkerframework.checker.index.qual.Positive;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import placelab.utilities.WebDriverSetup;

public class LoginTest {
    public WebDriver driver;
    private String usr = "Igor Slagalo ";
    private String role = "Group Admin";
    private String host = System.getProperty("host");
    private String username = System.getProperty("username");
    private String password = System.getProperty("password");
    private String homePage = System.getProperty("home");


    //Specify the driver and browser that will be used for this scenario
    //@Parameters({"browser"})
    @BeforeSuite(alwaysRun = true)
    public void initDriver() {
        //driver = WebDriverSetup.getWebDriver("firefox");
    }
    //Actual test case implementation
    @BeforeTest(alwaysRun = true, groups = {"Positive,Negative"}, description = "Verify the allocation of resources, " +
            "in this case the acces to webpage " + "PlaceLab")
    @Parameters({"browser"})
    public void open(String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.navigate().to(host);
        //Validate that user is redirected to the right page
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");
        boolean logoOnPage = driver.findElement(By.xpath("/html/body/div/div[1]/div/img")).isDisplayed();
        Assert.assertTrue(logoOnPage);

    }

    @Test(groups = {"Positive"}, priority = 3, suiteName = "Login Tests", description = "Validating login with " +
            "correct login data for" + "PlaceLab")
    public void testLoginPagePositive() {

        WebElement userName = driver.findElement(By.cssSelector("input[placeholder$='Email']"));
        userName.sendKeys(username);
        WebElement PassWord = driver.findElement(By.name("password"));
        PassWord.sendKeys(password);
        WebElement LoginButton = driver.findElement(By.cssSelector("input[value$='Log in']"));
        LoginButton.click();
        Assert.assertEquals(driver.getCurrentUrl(), homePage); //validate again that the user is redirected to the
        // correct webpage
        try {
            Assert.assertEquals(driver.findElement(By.id("user-name")).getText(), usr);
            WebElement usrRole = driver.findElement(By.id("user-role"));
            Assert.assertEquals(usrRole.getText(), role);
        } catch (RuntimeException err) {
            err.printStackTrace(); //line nmb and class name shown if exception occurs
            throw new RuntimeException("User under the username and role not logged in.");
        }

    }


    @Test(groups = {"Negative"}, priority = 1, suiteName = "Login Tests", description = "Validating that login is not possible with invalid password")
    public void testLoginInvalidPass() {
        driver.findElement(By.name("email")).sendKeys(username);
        WebElement enterPass = driver.findElement(By.name("password"));
        enterPass.sendKeys("QAMPERI SU KRALJEVI/CE");
        WebElement LoginButton = driver.findElement(By.cssSelector("input[value$='Log in']"));
        LoginButton.click();
    }

    @Test(groups = {"Negative"}, suiteName = "Login Tests", priority = 2, description = "Validating that login is not possible with invalid username")
    public void testLoginInvalidUsr() {
        driver.findElement(By.name("email")).sendKeys("Manuelna automacija"); //little oximoron for input
        WebElement enterPass = driver.findElement(By.name("password"));
        enterPass.sendKeys(password);
        WebElement LoginButton = driver.findElement(By.cssSelector("input[value$='Log in']"));
        LoginButton.click();
    }


    @AfterTest(alwaysRun = true,groups = {"Positive,Negative"}, description = "Validating the webpage state after login is denied")
    //Clean up - close the browser
    public void close() {
      // Assert.assertEquals(driver.getCurrentUrl(), host);
       //Assert.assertEquals(driver.findElement(By.className("error-area")).getText(), "Invalid credentials!", "User " +
         //       "should not be able to Log In with invalid credentials !");
       driver.quit();
    }
    /*
    @AfterTest(groups={"Positive"},description = "Validating the logout sequence on page")
    public void logOut() {

        //Logout sequence should be implemented here
        driver.close();
    }
*/
    @AfterSuite(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
    }


}