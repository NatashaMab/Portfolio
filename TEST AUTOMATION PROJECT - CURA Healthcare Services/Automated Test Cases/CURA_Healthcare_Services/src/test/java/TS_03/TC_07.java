package TS_03;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import HelperMethods.preConditions;

//History functionality - with PRIOR APPOINTMENTS booked.

public class TC_07 {
	
	WebDriver driver;
	preConditions helper;
	
	@BeforeMethod
	public void setUp() {
		
ChromeOptions options = new ChromeOptions();
		
		options.addArguments("--start-maximized");
		options.addArguments("--disable-save-password-bubble");
		options.addArguments("--disable-notifications");
		options.addArguments("--incognito");

		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);
		
		driver = new ChromeDriver(options);
		
		
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
		
		driver.quit();
	}

}
