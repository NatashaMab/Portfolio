package TS_01;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_01 {
@Test

	public void LoginValidation() {
		
		 WebDriver driver  = new ChromeDriver(); 
		 
		 //maximize the window
		 driver.manage().window().maximize();
		 
		 //wait for a specific amount of time when searching for an element
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	
		 //Step 1 - launch website
		 driver.get("https://katalon-demo-cura.herokuapp.com/");
		 
		 //Step 2 - click on the 'Make a Appointment Button'
		 driver.findElement(By.xpath("//*[@id=\"btn-make-appointment\"]")).click();
		 
		 //Step 3 - input username - 'John Doe'
		 driver.findElement(By.xpath("//*[@id=\"txt-username\"]")).sendKeys("John Doe");
		 
		 //Step 4 - input password - 'ThisIsNotAPassword'
		 driver.findElement(By.xpath("//*[@id=\"txt-password\"]")).sendKeys("ThisIsNotAPassword");
		 
		 //Step 5 - click on the 'Login' button
		 driver.findElement(By.xpath("//*[@id=\"btn-login\"]")).click();
		 
		 //EXPECTED RESULTS VALIDATION
		 //1 - Redirection to the 'Make Appointment' page
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 
		 WebElement appointmentsection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"appointment\"]")));
		
		 WebElement logoutlink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[5]/a")));
			
		 Assert.assertTrue(appointmentsection.isDisplayed());
		 Assert.assertTrue(logoutlink.isDisplayed());
		 
		 // trying to get my commits to work
		 
		 
	}
}
