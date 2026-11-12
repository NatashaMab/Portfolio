/*
 * provide all elements and actions that will be associated with the login page
 * verify - we are on the Login page
 * submit - username
 * submit - password
 * click - login
 *
 * Elements include locators (By)
 * */

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    //driver
    WebDriver driver;

    //locators
    By txtLogin_id = By.id("login-heading");
    By txtbxUsername_id = By.id("login-email");
    By txtbxPassword_id = By.id("login-password");
    By btnLogin_id = By.id("login-submit");

    //CONSTRUCTOR
    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //verify we are on the login page
    public void verifyOnLoginPage(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(txtLogin_id));
        driver.findElement(txtLogin_id).isDisplayed();
    }

    //submit username and password
    public void provideLoginDetails(String username, String password){
        driver.findElement(txtbxUsername_id).sendKeys(username);
        driver.findElement(txtbxPassword_id).sendKeys(password);
    }

    //click login button
    public void clickLogin(){
        driver.findElement(btnLogin_id).click();
    }

}
