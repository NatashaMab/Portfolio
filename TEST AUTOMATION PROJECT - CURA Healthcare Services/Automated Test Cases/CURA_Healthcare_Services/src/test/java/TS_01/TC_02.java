package TS_01;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

//Login with incorrect details

public class TC_02 {
	
	public void LoginValidation() {
	
	WebDriver driver = new ChromeDriver();
	
	//maximize the window
	 driver.manage().window().maximize();
	 
	//wait for a specific amount of time when searching for an element
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

	 //Step 1 - launch website
	 driver.get("https://katalon-demo-cura.herokuapp.com/");
	 
	 //Click on the 'Make Appointment ' button.
	 driver.findElement(By.xpath("//*[@id=\\\"btn-make-appointment\\\"]")).click();
	 
	 //Input any Username and Password - apart from the one specified 
	 driver.findElement(By.xpath("//*[@id=\\\"txt-username\\\"]")).sendKeys("Ashton Mayor");
	 
	 driver.findElement(By.xpath("//*[@id=\\\"txt-password\\\"]")).sendKeys("Password");
	 
	 driver.findElement(By.xpath("//*[@id=\"btn-login\"]")).click();
	 
	 String expectedLoginErr = "Login failed! Please ensure the username and password are valid.";
			 
	 Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/div[1]/p[2]")).getText(), expectedLoginErr);
	 
	}
}
