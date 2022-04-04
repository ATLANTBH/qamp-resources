package placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage extends Page {
    // private WebDriver driver;
    private String host;
    private String username = System.getProperty("username");
    private String password = System.getProperty("password");

    public LoginPage() {
        host = System.getProperty("host");
    }

    public LoginPage(String host) {
        this.host = host;
    }

    public String verifyLoginPage(WebDriver driver) {
        driver.navigate().to(host);
        getLogo(driver).isDisplayed();
        return driver.getCurrentUrl();
    }

    public WebElement getLogo(WebDriver driver) {
        WebElement logo = driver.findElement(By.xpath("/html/body/div/div[1]/div/img"));
        return logo;
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public void testLoginPagePositive(WebDriver driver) {
        WebElement userName = driver.findElement(By.cssSelector("input[placeholder$='Email']"));
        userName.sendKeys(username);
        WebElement PassWord = driver.findElement(By.name("password"));
        PassWord.sendKeys(password);
        WebElement Submit = driver.findElement(By.cssSelector("input[value$='Log in']"));
        Submit.click();
        // correct webpage
    }

    public String getPage() {
        return host;
    }

    public String testLoginPageBadUsr(WebDriver driver) {
        driver.findElement(By.name("email")).sendKeys(username);
        WebElement enterPass = driver.findElement(By.name("password"));
        enterPass.sendKeys("QAMPERI SU KRALJEVI/CE");
        WebElement LoginButton = driver.findElement(By.cssSelector("input[value$='Log in']"));
        LoginButton.click();
        WebElement Mssg = driver.findElement(By.className("error-area"));
        return Mssg.getText();
    }

    public String testLoginPageBadPass(WebDriver driver) {
        driver.findElement(By.name("email")).sendKeys("Manuelna automacija"); //little oximoron for input
        WebElement enterPass = driver.findElement(By.name("password"));
        enterPass.sendKeys(password);
        WebElement LoginButton = driver.findElement(By.cssSelector("input[value$='Log in']"));
        LoginButton.click();
        WebElement Mssg = driver.findElement(By.className("error-area"));
        return Mssg.getText();
    }

}
