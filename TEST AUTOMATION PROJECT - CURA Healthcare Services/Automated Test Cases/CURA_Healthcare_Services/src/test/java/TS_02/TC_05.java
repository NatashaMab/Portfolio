package TS_02;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_05 {
	
	WebDriver driver;
	
	
	@BeforeMethod
	public void setUp(){
		
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.get("https://katalon-demo-cura.herokuapp.com/");
		
	}
	
	@Test
	public void appointmentWithNoFields(){
		
		//Login Pre-Condtion
		preConditions.LoginPreCondition(driver,"John Doe","ThisIsNotAPassword");
		
		/*we refresh the page so we can retrieve the button to make the appointment
		  after we have refreshed the page when we login.
		*/
		driver.navigate().refresh();
		
		//retrieve the button again
		WebElement makeAppointmentButton = driver.findElement(By.xpath("//*[@id='btn-make-appointment']"));
		
		//click the button
		makeAppointmentButton.click();
		
		//No filling in of any fields
		
		//clicking the 'Book Appointment' button 
		driver.findElement(By.xpath("//*[@id=\\\"btn-book-appointment\\\"]")).click();
		
		//VALIDATION
		
		//get the date field
		
		WebElement dateField = driver.findElement(By.xpath("//*[@id=\"txt_visit_date\"]"));
		
		// Use JavaScript to get the native validation message
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        String message = (String) js.executeScript("return arguments[0].validationMessage;", dateField);
        
        Assert.assertEquals(message, "Please fill out this field.");
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
		
	}
	

}
