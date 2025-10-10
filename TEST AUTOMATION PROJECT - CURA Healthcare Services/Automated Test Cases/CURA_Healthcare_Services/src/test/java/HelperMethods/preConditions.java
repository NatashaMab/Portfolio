package HelperMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/*This is my helper class that will handle SET-UP logic
-properties (variables)
-functions (clicking buttons)
*/
public class preConditions {
	
	//driver to actually interact with the website
	WebDriver driver;
	
	//This is to be used in conjunction with 'findElement'
	By bookButton = By.id("btn-book-appointment");
	By makeAppointment = By.id("btn-make-appointment");
	By dateField = By.id("txt_visit_date");
	
	//constructor that will take in our driver
	//when we construct an instance of this class we set it up as well
	public preConditions(WebDriver driver) {
		
		this.driver = driver;
		 
		
	}
	
	//Pre-condition method 
	//Login
	public void LoginPreCondition(String Username, String password) {
		
		//PRE-CONDITIONS Login - this pre-condition could be a method
				//menu
				this.driver.findElement(By.xpath("//*[@id=\"menu-toggle\"]")).click();
				//login
				this.driver.findElement(By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[3]/a")).click();
				
				//fill username
				this.driver.findElement(By.xpath("//*[@id=\"txt-username\"]")).sendKeys("John Doe");
				//fill password
				this.driver.findElement(By.xpath("//*[@id=\"txt-password\"]")).sendKeys("ThisIsNotAPassword");
	
				this.driver.findElement(By.xpath("//*[@id=\"btn-login\"]")).click();
				
	}
	
	public void clickMakeAppointment() {
		driver.findElement(makeAppointment).click();
	}
	
	public void clickBookAppointment() {
		
		driver.findElement(bookButton).click();
		
	}
	
	public String getValidationMessage() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		WebElement element = driver.findElement(dateField);
		
		// Use JavaScript to get the native validation message

        return (String) js.executeScript("return arguments[0].validationMessage;", element);
	}
	
	
}
