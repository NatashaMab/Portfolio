/*
 * provide all elements and actions that will be associated with the dashboard page
 * verify - we are on the Dashboard page and have successfully logged in
 * navigate to Group creation page (menu -> admin panel)
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

public class DashboardPage {

    WebDriver driver;

    //locators
    By txtDashboardWelcome_xpath = By.xpath("//h2[.//span[text()='Welcome']]");
    By btnMenu_xpath = By.xpath("//button[.//span[text()='Menu']]");
    By btnAdminPanel_xpath = By.xpath("//button[.//span[text()='Admin Panel']]");


    //CONSTRUCTOR
    public DashboardPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //verify on the Dashboard Page
    public void verifyOnDashboardPage(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(txtDashboardWelcome_xpath));
        driver.findElement(txtDashboardWelcome_xpath).isDisplayed();
    }

    //Navigate to the admin panel
    public void navigateToAdminPanelPage(){
        driver.findElement(btnMenu_xpath).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(btnAdminPanel_xpath));
        driver.findElement(btnAdminPanel_xpath).click();
    }
}
