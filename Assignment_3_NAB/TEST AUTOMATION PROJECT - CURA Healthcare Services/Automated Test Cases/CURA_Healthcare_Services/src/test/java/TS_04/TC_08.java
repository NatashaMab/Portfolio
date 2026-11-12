package TS_04;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import HelperMethods.preConditions;

//Profile functionality - correct display

public class TC_08 {
	
	WebDriver driver;
	preConditions helper;
	
	
	@BeforeMethod()
	public void setUp(){
		
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://katalon-demo-cura.herokuapp.com/ ");
		
		helper = new preConditions(driver);
	}
	
	@Test
	public void profileFunctionalityCorrectDisplay() {
		
		SoftAssert softAssert = new SoftAssert();  
		
		//login
		helper.LoginPreCondition("John Doe", "ThisIsNotAPassword");
		
		//navigate to profile
		helper.navigate(helper.getM_profile());
			
		//asserts
		String pageSource = driver.getPageSource();
		
		WebElement button = driver.findElement(helper.getBtnLogout());
		
        // Both assertions will run
        softAssert.assertTrue(pageSource.contains("John Doe"), "Text 'John Doe' not found on page!");
        
        softAssert.assertTrue(button.isDisplayed(), "The logout button is not displayed");

        // This tells TestNG to report all collected assertion results
        softAssert.assertAll();
	
	}
	
	@AfterMethod()
	public void tearDown() {
		driver.quit();
	}
}
