package tutorialsInJava.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TC_RF_002 {

	@Test
	public void VerifyingEmail() {
	
		WebDriver driver = new ChromeDriver();
		
		//global wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		//open browser 
		driver.manage().window().maximize();
		
		// go to website 
		driver.get("https://www.amazon.in/");
		
		//START NAVIGATION
		driver.findElement(By.xpath("//span[text() = 'Hello, sign in']")).click();
		driver.findElement(By.xpath("//*[@id=\"ap_email_login\"]")).sendKeys("ankarahousewholesale@gmail.com");
		
		driver.findElement(By.xpath("//*[@id=\"continue\"]/span/input")).click();
		driver.findElement(By.xpath("//*[@id=\"auth-fpp-link-bottom\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"continue\"]")).click();
		driver.findElement(By.id("ap_email")).sendKeys("ankarahousewholesale@gmail.com");
		driver.findElement(By.id("continue")).click();
		
		String email = "ankarahouse";
		String appPasscode = "";
		//VERIFICATION - access your email account
		
	}

}
