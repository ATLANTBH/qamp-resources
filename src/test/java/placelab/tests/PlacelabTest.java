package placelab.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import placelab.pages.MainDashboard;
import placelab.pages.Queries;
import placelab.pages.ReportForm;
import placelab.utilities.WebDriverSetup;
import placelab.pages.LoginPage;

import java.time.Duration;

public class PlacelabTest {

    public WebDriver driver;
    private String usr = "Igor Slagalo ";
    private String role = "Group Admin";   //primitive but is an easy workaround to get the wanted messages
    // in this case. A better but more extensive and time consuming solution were to make a class container for these
    // strings so they can be manipulated and called at will
    private String errorMsg = "Invalid credentials!";
    private String host = System.getProperty("host");
    private String homePage = System.getProperty("home");
    private String query = System.getProperty("queries");
    private String reportURL = System.getProperty("repURL");
    private LoginPage login = new LoginPage(host);
    private MainDashboard dashboard = new MainDashboard(host);
    private ReportForm report = new ReportForm(reportURL);
    private Queries traffic = new Queries();  //implementation of the last page object, after report creation

    //Specify the driver and browser that will be used for this scenario
    @BeforeSuite(alwaysRun = true)

    public void initDriver() {
        //nothing here
    }

    @BeforeTest(alwaysRun = true, groups = {"Positive,Negative"}, description = "Verify the allocation of resources, " +
            "in this case the acces to webpage " + "PlaceLab")
    @Parameters({"browser"})
    public void open(String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        // driver.manage().window().maximize();
        Assert.assertEquals(login.verifyLoginPage(driver), host);
        Assert.assertEquals(login.getPageTitle(driver), "PlaceLab");
        Assert.assertTrue(login.getLogo(driver).isDisplayed());
    }

    @Test(groups = {"Positive"}, priority = 3, suiteName = "Login Tests", description = "Validating login with " +
            "correct login data for" + "PlaceLab")
    public void SmokeTest() {  //positive outcome expected
        login.testLoginPagePositive(driver);
        Assert.assertEquals(driver.getCurrentUrl(), homePage); //validate again that the user is redirected to the
        //right page
        try {
            Assert.assertEquals(driver.findElement(By.id("user-name")).getText(), usr);
            WebElement usrRole = driver.findElement(By.id("user-role"));
            Assert.assertEquals(usrRole.getText(), role);
            //dashboard.createQuery();
            Assert.assertEquals(dashboard.createQuery(driver), reportURL);
            Assert.assertEquals(report.input(driver), query);

        } catch (RuntimeException err) {
            err.printStackTrace(); //line nmb and class name shown if exception occurs
            throw new RuntimeException("User under the username and role not logged in.");
        }

    }

    @Test(groups = {"Negative"}, priority = 1, suiteName = "Login Tests", description = "Validating that login is " +
            "not possible with invalid password")
    public void testLoginInvalidPass() {
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(login.testLoginPageBadPass(driver), errorMsg);
    }

    @Test(groups = {"Negative"}, suiteName = "Login Tests", priority = 2, description = "Validating that login is " +
            "not possible with invalid username")
    public void testLoginInvalidUsr() {
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(login.testLoginPageBadUsr(driver), errorMsg);
    }

    @AfterTest(groups = {"Positive"}, description = "Validating the logout sequence on page")
    public void quitPage() {
        dashboard.backTrace(driver);
        driver.close();
    }

    @AfterSuite(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
    }

}