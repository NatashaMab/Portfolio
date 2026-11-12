package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utilities {

    WebDriver driver;

    public void clickElement(By locator, WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10)
        );

        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );

        element.click();
    }

    public void enterText(WebDriver driver, By locator, String text) {

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10)
        );

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        element.sendKeys(text);
    }
}
