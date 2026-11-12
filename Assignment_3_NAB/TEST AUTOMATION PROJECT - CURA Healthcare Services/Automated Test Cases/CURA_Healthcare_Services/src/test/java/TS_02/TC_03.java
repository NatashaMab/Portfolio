package TS_02;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//Make an appointment with all MANDATORY FIELDS filled in.

public class TC_03 {

	WebDriver driver;
	//WebDriverWait wait;
	
	@BeforeMethod
	public void setUp() {
		
		driver = new ChromeDriver();
		
		//maximize the window
		 driver.manage().window().maximize();
		 
		//wait for a specific amount of time when searching for an element
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		 //Step 1 - launch website
		 driver.get("https://katalon-demo-cura.herokuapp.com/");
	}
	
	@Test
	public void appointmentWithMandatoryFields() {
		
		//click on the 'Make Appointment' button
		driver.findElement(By.xpath("//*[@id=\"btn-make-appointment\"]")).click();
		
		waitURL("https://katalon-demo-cura.herokuapp.com/profile.php#login",10);
		
		//check that we are redirected to the login page
		Assert.assertEquals(driver.getCurrentUrl(), "https://katalon-demo-cura.herokuapp.com/profile.php#login","User was not directed to the login page");
		
		//Login
		driver.findElement(By.xpath("//*[@id=\"txt-username\"]")).sendKeys("John Doe");
		driver.findElement(By.xpath("//*[@id=\"txt-password\"]")).sendKeys("ThisIsNotAPassword");
		
		driver.findElement(By.xpath("//*[@id=\"btn-login\"]")).click();
		
		waitURL("https://katalon-demo-cura.herokuapp.com/#appointment",10);
		
		//check that we are redirected to the make appointment page
		Assert.assertEquals(driver.getCurrentUrl(), "https://katalon-demo-cura.herokuapp.com/#appointment","User was not redirected to the appoinment page");
		
		//fill in mandatory fields - date 
		//using a random date generator to generate dates ahead of the current date 
		
		String providedDate = getFutureDate(2);
		driver.findElement(By.xpath("//*[@id=\"txt_visit_date\"]")).sendKeys(providedDate);
		
		//click on 'book appointment' button
		driver.findElement(By.xpath("//*[@id=\"btn-book-appointment\"]")).click();
		
		/* 
		 * Redirection to 'Appointment Confirmation' page.     
		 * Display of the correct date on  'Appoinment Confirmation' page.
		 */
		waitURL("https://katalon-demo-cura.herokuapp.com/appointment.php#summary",10);
		
		//VALIDATION
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://katalon-demo-cura.herokuapp.com/appointment.php#summary","User was not directed to the summary page");
		
		//get visit date
		String DisplayedDate = driver.findElement(By.xpath("//*[@id=\"visit_date\"]")).getText();
		
		//compare
		Assert.assertEquals(DisplayedDate, providedDate, "An incorrect appointment summary was given");
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	
	//utility methods
	//Waiting for URLs to load - that way we don't read the current URL immediately 
	
	public void waitURL(String URL, int seconds) {
		
		new WebDriverWait(driver, Duration.ofSeconds(seconds))
			.until(ExpectedConditions.urlToBe(URL));
	}
	
	//generate dates so we aren't hardcoding dates
	public String getFutureDate(int daysAhead) {
		
	    LocalDate futureDate = LocalDate.now().plusDays(daysAhead);
	    return futureDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    
	}
	
}
