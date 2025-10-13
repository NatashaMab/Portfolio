package TS_03;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import HelperMethods.preConditions;

public class TC_06 {
	
	WebDriver driver;
	preConditions helper;
	
	@BeforeMethod
	public void setUp() {
		
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://katalon-demo-cura.herokuapp.com/ ");
		
		helper = new preConditions(driver);
	}
	
	@Test
	public void historyFunctionalityWithNoPriorBookings() {
		
		//Precondition - Successful login 
		helper.LoginPreCondition("John Doe","ThisIsNotAPassword");
		
		helper.navigate(helper.getM_history());
		
		String NoHistory = "No appointment.";
		
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"history\"]/div/div[1]/div/p")).getText(),NoHistory,"No History exclaimation was not found");
	}
	
	@AfterMethod()
	public void tearDown() {
		
		driver.quit();
	}
	

}
