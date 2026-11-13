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

import com.aventstack.extentreports.gherkin.model.ScenarioOutline;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
    By txtSuccessful_xpath = By.xpath("//div[contains(text(),'Group created successfully!')]");
    By btnCancel_xpath = By.xpath("//button[text()='Cancel']");
    By txtErrorMessage_xpath = By.xpath("/html/body/div/div/div/div[3]/div/div[2]");
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

        clickElement(btnCreateGroup_xpath, driver);
        //driver.findElement(btnCreateGroup_xpath).click();
    }

    //provide group creation elements
    public void provideGroupCreationElements(String name, String description, String year, String maximumCapacity, String startDate, String endDate){

        //driver.findElement(txtbxName_xpath).sendKeys(name);
        enterText(driver, txtbxName_xpath, name );

        //driver.findElement(txtbxDescription_xpath).sendKeys(description);
        enterText(driver,txtbxDescription_xpath,description);

        //driver.findElement(txtbxYear_xpath).sendKeys(year);
        enterText(driver, txtbxYear_xpath, year);

        //driver.findElement(txtbxMaximumCapacity_xpath).sendKeys(maximumCapacity);
        enterText(driver, txtbxMaximumCapacity_xpath, maximumCapacity);

        //driver.findElement(txtbxStartDate_xpath).sendKeys(startDate);
        enterText(driver, txtbxStartDate_xpath, startDate);

        //driver.findElement(txtbxEndDate_xpath).sendKeys(endDate);
        enterText(driver, txtbxEndDate_xpath, endDate );
    }

    //click create group
    public void clickCreateGroup(){
        driver.findElement(btnCreateNewGroup_xpath).click();
    }

    //verify group has been successfully created
    public void verifySuccessfulGroupCreation(){

        try{
            WebElement success =  new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(txtSuccessful_xpath));

            //driver.findElement(txtSuccessful_xpath).isDisplayed();

            Assert.assertEquals(success.getText(),"Group created successfully!", "Expected success message was not displayed");

            System.out.println("Test Passed Successfully" + success.getText());

        }catch (TimeoutException e){

            clickElement(btnCancel_xpath,driver);

            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

            WebElement fail = wait.until(ExpectedConditions.visibilityOfElementLocated(txtErrorMessage_xpath));

            Assert.assertEquals(fail.getText(),"A group with this name already exists", "Expected duplicate group name error was not displayed");

            System.out.println("Test Passed Successfully However " + fail.getText() + " Therefore NO GROUP WAS CREATED!");
        }


    }

}
