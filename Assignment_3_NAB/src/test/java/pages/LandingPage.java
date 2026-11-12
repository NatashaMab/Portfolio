/*
* provide all elements and actions that will be associated with the landing page
* verify - we are at landing page
* click - login button
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

public class LandingPage {

    //driver - that we will be interacting with
    WebDriver driver;

    //locators
    //login button
    By btnLogin_xpath = By.xpath("//button[.//span[text()='Login']]");
    //home button
    By btnHome_xpath = By.xpath("//button[.//span[text()='Home']]");

    //CONSTRUCTOR
    public LandingPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //actions
    //verify we are on the landing page
    public void verifyOnLandingPage(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(btnHome_xpath));
        driver.findElement(btnHome_xpath).isDisplayed();
    }

    //click the login button - navigate to Login Page
    public void navigateToLoginPage(){
        driver.findElement(btnLogin_xpath).click();
    }
}
