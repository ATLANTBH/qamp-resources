package placelab.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import placelab.utilities.WebDriverSetup;

public class LoginTestTermsOfUse {
    public WebDriver driver;
    private String host = System.getProperty("host");
    private String termsUrl = "https://demo.placelab.com/terms_of_service";
    private String termsTitle = "Terms and conditions of use";

    //Specify the driver and browser that will be used for this scenario

    @BeforeSuite
        public void initDriver() {
        driver = WebDriverSetup.getWebDriver("opera");
    }

    //Actual test case implementation

    @Test
    public void testTermsOfUsePage() {

        //Go to PlaceLab demo app
        driver.navigate().to(host);

        //Validate that user is redirected to the right page
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");

        //Click on Terms of Use link
        WebElement termsOfUse = driver.findElement(By.linkText("Terms of Use"));
        termsOfUse.click();

        //Get current window handle
        String originalWindow = driver.getWindowHandle();

        //loop to find new window handle
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        //Validate that user is redirected to the Terms of use page
        Assert.assertEquals(driver.getCurrentUrl(),termsUrl);

        WebElement logo = driver.findElement(By.xpath("//div[@id=\"terms_of_service_container\"]/h3/img"));
        boolean logoPresent = logo.isDisplayed();
        Assert.assertTrue(logoPresent);

        WebElement termsTitleCurrent = driver.findElement(By.xpath("//div[@class = 'terms-header']"));
        assert (termsTitleCurrent.getText().contains(termsTitle));
        // Are these two assertions above and bellow equal? What is best method
        // in this case. I tried both and both works.
//        Assert.assertEquals(termsTitleCurrent.getText(), termsTitle);
    }

    //Clean up - close the browser
    //Now I have two opened tabs, I couldn't find better method,
    // so I call quit instead of close, hope it's ok :)

    @AfterSuite
    public void quitDriver() {
        driver.quit();
    }
}