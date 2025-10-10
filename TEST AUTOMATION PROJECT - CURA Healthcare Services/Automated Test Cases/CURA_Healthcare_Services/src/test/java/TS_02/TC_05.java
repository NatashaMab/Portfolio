package TS_02;

import java.time.Duration;


import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import HelperMethods.preConditions;

public class TC_05 {
	
	WebDriver driver;
	preConditions helper;
	
	@BeforeMethod
	public void setUp(){
		
		// Setup WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://katalon-demo-cura.herokuapp.com/");
        
        // Create helper (passing driver)
        helper = new preConditions(driver);
	
	}
	
	@Test
	public void appointmentWithNoFields(){
		
		//Login Pre-Condtion
		helper.LoginPreCondition("John Doe","ThisIsNotAPassword");
		
		/*we refresh the page so we can retrieve the button to make the appointment
		  after we have refreshed the page when we login.
		*/
		driver.navigate().refresh();
		
				
		helper.clickMakeAppointment();
		
		//No filling in of any fields
		
		helper.clickBookAppointment();
		
		
    	//VALIDATION
        //Test Logic
		String message = helper.getValidationMessage();
        Assert.assertEquals(message, "Please fill out this field.");
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
		
	}
	

}
