/*PAGE OBJECT MODEL DESIGN PATTERN - organizing elements and their actions onto separate classes called 
page objects. Each class represents a specific page or component of the application,
encapsulating its locators(elements) and methods(interactions). 
READABILITY, REUSABILITY, MAINTAINABILITY
*/

package HelperMethods;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class preConditions {
	
	//driver
	WebDriver driver;
	Random rand = new Random();
	
	//Elements-Locators	
	//This is to be used in conjunction with 'findElement'
	
	//buttons
	By btnBook = By.id("btn-book-appointment");
	By btnMakeAppointment = By.id("btn-make-appointment");
	By btnLogin = By.id("btn-login");
	By btnLogout = By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[5]/a");
	
	//fields
	By f_dateField = By.id("txt_visit_date");
	By f_username = By.id("txt-username");
	By f_password = By.id("txt-password");
	By f_comment = By.id("txt_comment");
	By f_dropdown = By.id("combo_facility");
	By f_checkbox = By.id("chk_hospotal_readmission");
	
	
	//menu
	By m_menu = By.xpath("//*[@id=\"menu-toggle\"]");
	By m_home = By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[2]/a");
	By m_history = By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[3]/a");
	By m_profile = By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[4]/a");
	By m_login = By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[3]/a");
	By m_logout = By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[5]/a");
	
	//CONSTRUCTOR
	//constructor that will take in our driver
	
	public preConditions(WebDriver driver) {
		this.driver = driver;
	}
	
	//GETTERS
		public By getBtnBook() {
			return btnBook;
		}
	
		public By getBtnMakeAppointment() {
			return btnMakeAppointment;
		}
		
		public By getBtnLogout() {
			return btnLogout;
		}
	
		public By getF_dateField() {
			return f_dateField;
		}
	
		public By getF_username() {
			return f_username;
		}
	
		public By getF_password() {
			return f_password;
		}
	
		public By getF_comment() {
			return f_comment;
		}
	
		public By getM_menu() {
			return m_menu;
		}
	
		public By getM_home() {
			return m_home;
		}
	
		public By getM_history() {
			return m_history;
		}
	
		public By getM_profile() {
			return m_profile;
		}
	
		public By getM_logout() {
			return m_logout;
		}

	
	//NAVIGATION
	
	public void navigate(By destination) {
		
		driver.navigate().refresh();
		
		this.driver.findElement(m_menu).click();

		WebElement destination_WE = this.driver.findElement(destination);
		
		destination_WE.click();
		
	}
	
	//CLICKING BUTTONS - SUBMITTING INFORMATION
	public void submitClick(By button) {
		
		driver.navigate().refresh();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement buttonToClick = wait.until(ExpectedConditions.elementToBeClickable(button));

		buttonToClick.click();
		
	}
	
	
	//LOGIN
	public void LoginPreCondition(String Username, String password) {
		
		//PRE-CONDITIONS Login - this pre-condition could be a method
				//menu
				this.driver.findElement(m_menu).click();
				
				//login
				this.driver.findElement(m_login).click();
				
				//fill username
				this.driver.findElement(f_username).sendKeys("John Doe");
				//fill password
				this.driver.findElement(f_password).sendKeys("ThisIsNotAPassword");
	
				this.driver.findElement(btnLogin).click();	
	}
	
	//helper method for making an appointment
	public String getFutureDate(int daysAhead) {
		
	    LocalDate futureDate = LocalDate.now().plusDays(daysAhead);
	    return futureDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    
	}
	
	//helper method for randomly picking a value on the dropdown button
	
	public String facilitySelection() {
		
		int selection = rand.nextInt(3);
		
		String[] options = {"Tokyo CURA Healthcare Center","Hongkong CURA Healthcare Center","Seoul CURA Healthcare Center"};
		
		return options[selection];
	}
	
	
	//MAKE AN APPOINTMENT
	public String makeAppointment(String comment) {
		
	//locate the dropdown element
		WebElement dropDownElement = driver.findElement(f_dropdown);
		
	//create select object
		Select dropdown = new Select(dropDownElement);
		
	//select by index ( we could do a random number generator here)
		dropdown.selectByValue(facilitySelection());
	
	//Field - HOSPITAL READMISSION
		
		WebElement checkbox = driver.findElement(f_checkbox);
	
		if (!checkbox.isSelected()) {
			
		    checkbox.click();
		}
		
		String providedDate = getFutureDate(2);
		
		driver.findElement(f_dateField).sendKeys(providedDate);
		
		return providedDate;
	}
	

	
	public String getValidationMessage() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		WebElement element = driver.findElement(f_dateField);
		
		// Use JavaScript to get the native validation message

        return (String) js.executeScript("return arguments[0].validationMessage;", element);
	}
	
	
}
