/*
 * provide all elements and actions that will be associated with the admin panel page
 * verify - we are on the Admin Panel page
 * click - groups
 * click - create new group
 * fill out new group information
 *      name
 *      description
 *      year
 *      maximum capacity - must be 100
 *      start date
 *      end date
 * click - create new group
 * verify - group has successfully been created
 *
 * */
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utilities;

import java.time.Duration;
import java.util.Date;

public class AdminPanelPage extends Utilities {

    WebDriver driver;

    //locators
    By txtAdminPanelHeader_xpath= By.xpath("//h2[text()='\uD83D\uDD27 Admin Panel']");
    By btnGroups_xpath= By.xpath("//button[text()='Groups']");
    By btnCreateGroup_xpath = By.xpath("//button[normalize-space(.)='+ Create New Group']");
    By txtbxName_xpath = By.xpath("//input[@placeholder='e.g., 2nd Group 2026']");
    By txtbxDescription_xpath = By.xpath("//textarea[@name='description']");
    By txtbxYear_xpath = By.xpath("//input[@value='2026']");
    By txtbxMaximumCapacity_xpath = By.xpath("//input[@value='1']");
    By txtbxStartDate_xpath = By.xpath("//input[@name='startDate']");
    By txtbxEndDate_xpath = By.xpath("//input[@name='endDate']");
    By btnCreateNewGroup_xpath = By.xpath("//button[text()='Create Group']");

    //CONSTRUCTOR
    public AdminPanelPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //verify we are on the Admin Panel Page
    public void verifyOnAdminPanelPage(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(txtAdminPanelHeader_xpath));
        driver.findElement(txtAdminPanelHeader_xpath).click();
    }

    //navigate to the group creation popup
    public void navigateToCreateGroup(){
        driver.findElement(btnGroups_xpath).click();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        WebElement createGroupButton = wait.until(
//                ExpectedConditions.elementToBeClickable(
//                        By.xpath("//button[normalize-space(.)='+ Create New Group']")
//                )
//        );
//
//        createGroupButton.click();
        clickElement(btnCreateGroup_xpath, driver);
        //driver.findElement(btnCreateGroup_xpath).click();
    }

    //provide group creation elements
    public void provideGroupCreationElements(String name, String description, String year, String maximumCapacity, String startDate, String endDate){
        driver.findElement(txtbxName_xpath).sendKeys(name);
        //driver.findElement(txtbxDescription_xpath).sendKeys(description);
        enterText(driver,txtbxDescription_xpath,description);
        driver.findElement(txtbxYear_xpath).sendKeys(year);
        driver.findElement(txtbxMaximumCapacity_xpath).sendKeys(maximumCapacity);
        driver.findElement(txtbxStartDate_xpath).sendKeys(startDate);
        driver.findElement(txtbxEndDate_xpath).sendKeys(endDate);
    }

    //click create group
    public void clickCreateGroup(){
        driver.findElement(btnCreateNewGroup_xpath).click();
    }

    //verify group has been successfully created

}
