package TS_03;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import HelperMethods.preConditions;

public class TC_07 {
	
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
	public void historyFunctionalityWithPriorBookings() {
		
		helper.LoginPreCondition("John Doe", "ThisIsNotAPassword");
		
		helper.submitClick(helper.getBtnMakeAppointment());	
		
		String providedDate = helper.makeAppointment("This an appointment for diabetes");
		
		helper.submitClick(helper.getBtnBook());
		
		helper.navigate(helper.getM_history());
		
		String pageSource = driver.getPageSource();
		
        Assert.assertTrue(pageSource.contains(providedDate), "Text not found on the page!");
	}
	
	@AfterMethod()
	public void tearDown() {
		
	}

}
