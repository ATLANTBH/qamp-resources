package placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Login {

    private WebDriver driver;

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyLoginPage(final String host) {
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");
        getPLLogo().isDisplayed();
    }

    private WebElement getPLLogo() {
        WebElement logo = driver.findElement(By.xpath("//img[@src='/assets/logo" +
                "-526ea19604d26801aca90fe441f7df4775a24a5d74ae273dbc4af85f42241259.png']"));
        return logo;
    }

    public void enterPassword(final String password) {
        WebElement enterPassword = driver.findElement(By.name("password"));
        enterPassword.sendKeys(password);
    }

    public void enterUsername(final String username) {
        WebElement enterUsername = driver.findElement(By.name("email"));
        enterUsername.sendKeys(username);
    }

    public String getErrorMessage(){
       return driver.findElement(By.className("error-area")).getText();
    }

    //Submit login form
    public void submit() {
        WebElement submit = driver.findElement(By.xpath("//input[@type='submit']"));
        submit.click();
    }

}