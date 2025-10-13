package TS_04;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import HelperMethods.preConditions;

public class TC_09 {
	
	WebDriver driver;
	preConditions helper;
	
	@BeforeMethod()
	public void setUp() {
		
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://katalon-demo-cura.herokuapp.com/ ");
		
		helper = new preConditions(driver);	
		
	}
	
	@Test()
	public void logoutFunctionality() {
		
		//login
		helper.LoginPreCondition("John Doe", "ThisIsNotAPassword");
		
		//verify that you are logged in by checking contents of menu
		driver.findElement(helper.getM_menu()).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement logoutlink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[5]/a")));
		
		//if logout link is present then you have logged in successfully
		Assert.assertTrue(logoutlink.isDisplayed());
		
		//logout
		driver.findElement(helper.getM_menu()).click();
		
		WebElement logoutButton = wait.until(ExpectedConditions.visibilityOf(driver.findElement(helper.getBtnLogout())));

		wait.until(ExpectedConditions.elementToBeClickable(helper.getBtnLogout()));

		logoutButton.click();
		
		//if logout link is no longer displayed then you have logged out successfully
		Assert.assertTrue(!logoutlink.isDisplayed());

	}
	
	@AfterMethod()
	public void tearDown() {
		
	}

}
