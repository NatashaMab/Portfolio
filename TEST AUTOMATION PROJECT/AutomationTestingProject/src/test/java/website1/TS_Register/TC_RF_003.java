package website1.TS_Register;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TC_RF_003 {
	
	@Test
	
	public void verifyRegistrationWithAllFields() {
	
	/*These are basically your 'pre-requisites' in the TC document
	 */
	WebDriver driver = new ChromeDriver();
	//global wait
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	//open browser 
	driver.manage().window().maximize();
	
	driver.get("https://tutorialsninja.com/demo/");
	
	/*These are your 'test steps' in the TC document and it includes your test data
	 */
	
	driver.findElement(By.xpath("//span[text()='My Account']")).click();
	driver.findElement(By.linkText("Register")).click();
	
	driver.findElement(By.id("input-firstname")).sendKeys("Nat");
	driver.findElement(By.id("input-lastname")).sendKeys("Mab");
	driver.findElement(By.id("input-email")).sendKeys(generateEmail());
	//System.out.println(generateEmail());
	driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
	
	/*MODIFICATION - you could also provide a random number generator function for 
	for the password instead of hard-coding it 
	*/
	
	driver.findElement(By.id("input-password")).sendKeys("1234");
    driver.findElement(By.id("input-confirm")).sendKeys("1234");
    
    //agreeing to the newsletter
    driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click(); 
    
	driver.findElement(By.name("agree")).click();
	driver.findElement(By.xpath("//input[@value='Continue']")).click();	

	/*This is now verification of your expected results
	 */
	
	Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
	
	String expectedHeading = "Your Account Has Been Created!";
	
	Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText(), expectedHeading);
	
	Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Success']")).isDisplayed());
	
	//clicking on the continue button
	driver.findElement(By.xpath("//a[text()='Continue']")).click();
	
	//verifying we are on the Account page once we have clicked on the continue button
	Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
	
	driver.quit();
	
	}
	
	public String generateEmail() {
		
		return new Date().toString().replaceAll(" ","").replaceAll("\\:","")+"@gmail.com";
		
	}
	

}
