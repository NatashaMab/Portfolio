package TS_04;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import HelperMethods.preConditions;

//Logout functionality

public class TC_09 {
	
	WebDriver driver;
	preConditions helper;
	
	@BeforeMethod()
	public void setUp() {
		
		//configuring Chrome options so pop-ups don't show
		
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
	
	@Test()
	public void logoutFunctionality() {
		
		SoftAssert softAssert = new SoftAssert();
		
		//login
		helper.LoginPreCondition("John Doe", "ThisIsNotAPassword");
		
		driver.navigate().refresh();
		
		//verify that you are logged in by checking contents of menu
		driver.findElement(helper.getM_menu()).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement logoutlink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[5]/a")));
		
		//if logout link is present then you have logged in successfully
		softAssert.assertTrue(logoutlink.isDisplayed());
		
		//logout
		//driver.findElement(helper.getM_menu()).click();
		
		WebElement logoutButton = wait.until(ExpectedConditions.visibilityOf(driver.findElement(helper.getBtnLogout())));

		wait.until(ExpectedConditions.elementToBeClickable(helper.getBtnLogout()));
		
		logoutButton.click();
		
		//if logout link is no longer displayed then you have logged out successfully
		driver.findElement(helper.getM_menu()).click();
		
		WebElement loginbutton = driver.findElement(helper.getM_login());
		
		softAssert.assertTrue(loginbutton.isDisplayed(), "The user has NOT been logged out");
	 
	}
	
	@AfterMethod()
	public void tearDown() {
		
		driver.quit();
		
	}

}
