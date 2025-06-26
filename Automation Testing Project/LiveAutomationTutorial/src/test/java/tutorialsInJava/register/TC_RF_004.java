//Hello Nat
package tutorialsInJava.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_004 {
	
	@Test
	public static void notificationVerification() {
		
		// create our driver
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.get("http://tutorialninja.com/demo");
		
		//1-Click on 'My Account' Drop menu
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		
		//2-Click on Register option
		driver.findElement(By.linkText("Register")).click();
		
		//3-DON'T ENTER ANYTHING
		
		//4-Click on 'Continue' button
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		//Expected messages for each field
		
		String firstNameWM = "First Name must be between 1 and 32 characters!";
		String lastNameWM = "Last Name must be between 1 and 32 characters!";
		String EmailWM = "E-Mail Address does not apperar to be valid";
		String TelephoneWM = "Telephone must be between 3 and 32 characters!";
		String PasswordWM = "Password must be between 4 and 20 characters!";
		String PrivacyPolicyWM = "Warning: You must agree to the Privacy Policy!";
		
		//EXPECTED RESULT VERIFICATION
		
		//"//input[@id='input-firstname']/following-sibling::div"
		Assert.assertEquals(driver.findElement(By.id("input-firstname")).getText(), firstNameWM);
		//"//input[@id='input-lastname']/following-sibling::div"
		Assert.assertEquals(driver.findElement(By.id("input-lastname")).getText(), lastNameWM);
		//"//input[@id='input-email']/following-sibling::div"
		Assert.assertEquals(driver.findElement(By.id("input-email")).getText(), EmailWM);
		//"//input[@id='input-telephone']/following-sibling::div"
		Assert.assertEquals(driver.findElement(By.id("input-telephone")).getText(),TelephoneWM);
		//"//input[@id='input-password']/following-sibling::div"
		Assert.assertEquals(driver.findElement(By.id("input-password")).getText(),PasswordWM);
		
		//the privacy policy verification
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText(), PrivacyPolicyWM);
		
		driver.quit();
	}
	
	//CORRECTION
	/*
	 * when finding the element that actually contain the messages - eg - 'first name must be...'
	 * we use x-path: "//input[@id='input-firstname']/following-sibling::div".getText()
	 * */

}
