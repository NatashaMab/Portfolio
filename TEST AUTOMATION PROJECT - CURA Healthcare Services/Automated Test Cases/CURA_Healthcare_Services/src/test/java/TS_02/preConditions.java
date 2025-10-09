package TS_02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class preConditions {
	
	
	//Pre-condition method 
	public static void LoginPreCondition(WebDriver driver, String Username, String password) {
		
		//PRE-CONDITIONS Login - this pre-condition could be a method
				//menu
				driver.findElement(By.xpath("//*[@id=\\\"menu-toggle\\\"")).click();
				//login
				driver.findElement(By.xpath("//*[@id=\\\"sidebar-wrapper\\\"]/ul/li[3]/a")).click();
				
				//fill username
				driver.findElement(By.xpath("//*[@id=\\\"txt-username\\\"]")).sendKeys("John Doe");
				//fill password
				driver.findElement(By.xpath("//*[@id=\\\"txt-password\\\"]")).sendKeys("ThisIsNotAPassword");
	
				driver.findElement(By.xpath("//*[@id=\\\"btn-login\\\"]")).click();
				
	}
}
