package TS_02;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_04 {
	
	WebDriver driver;
	
	@BeforeMethod
		public void setUp() {
		
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		//Step 1 - launch website
		 driver.get("https://katalon-demo-cura.herokuapp.com/");
		 
	}
	
	
	@Test
	
		public void appointmentNoManditoryFields() {
		
		//preconditions- SUCCESSFUL LOGIN
			
			driver.findElement(By.xpath("//*[@id=\"menu-toggle\"]")).click();
			driver.findElement(By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[3]/a")).click();
		
			driver.findElement(By.xpath("//*[@id=\"txt-username\"]")).sendKeys("John Doe");
			driver.findElement(By.xpath("//*[@id=\"txt-password\"]")).sendKeys("ThisIsNotAPassword");
				
			driver.findElement(By.xpath("//*[@id=\"btn-login\"]")).click();
		
		//click on 'Make Appointment' button
			
			driver.navigate().refresh();
			WebElement bookButton = driver.findElement(By.xpath("//*[@id='btn-make-appointment']"));
			bookButton.click();
			
			//driver.findElement(By.xpath("//*[@id=\"btn-make-appointment\"]")).click();
			
		//Filling in non-mandatory fields
		
		//Field - FACILITY
			
		//locate the dropdown element
			WebElement dropDownElement = driver.findElement(By.xpath("//*[@id=\"combo_facility\"]"));
			
		//create select object
			Select dropdown = new Select(dropDownElement);
			
		//select by index ( we could do a random number generator here)
			dropdown.selectByValue("Tokyo CURA Healthcare Center");
		
		//Field - HOSPITAL READMISSION
			
			WebElement checkbox = driver.findElement(By.id("chk_hospotal_readmission"));
		
			if (!checkbox.isSelected()) {
				
			    checkbox.click();
			}
			
		//Field - HEALTHCARE PROGRAM 
			
			/*List<WebElement> healthCareProgram = driver.findElements(By.xpath("\"//input[@name='programs']\""));
			
			for(WebElement hcp : healthCareProgram) {
				
				//String value = hcp.getDomProperty("value");
				String value = hcp.getAttribute("value");
				
			    if (value != null && value.equalsIgnoreCase("Medicare")) {
			        hcp.click();
			        break;
			        
			    }
			}*/
			
			driver.findElement(By.id("radio_program_medicaid")).click();
			
		//Field - COMMENT
			
			driver.findElement(By.xpath("//*[@id=\"txt_comment\"]")).sendKeys("This is an appointment booking with regards to diabetes");
			
		//Book Appointment
			
			driver.findElement(By.xpath("//*[@id=\"btn-book-appointment\"]")).click();
						
		//Validation
			
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
