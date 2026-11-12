/*In this class we configure our browsers
     specify the browser we will be using
     specify url
     arguments - e.g. headless and incognito
     get our url
     customise our browser e.g. maximise
*/
package utils;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserFactory {

    //instance of driver we will be using
    static WebDriver driver;

    //function to start the browser
    public static WebDriver startBrowser(String browserChoice, String url){

        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        //Chrome
        if (browserChoice.equalsIgnoreCase("chrome")){

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            if (isHeadless) {
                options.addArguments("--headless=new");
            }
            driver = new ChromeDriver(options);

        //Firefox
        } else if (browserChoice.equalsIgnoreCase("firefox")){
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("-private");
            if (isHeadless) {
                options.addArguments("-headless");
            }
            driver = new FirefoxDriver(options);

        //Safari
        } else if (browserChoice.equalsIgnoreCase("safari")){
            driver = new SafariDriver();

        //Edge
        } else {
            driver = new EdgeDriver();
        }

        driver.get(url);
        driver.manage().window().setSize(new Dimension(1920,1080));
        //driver.manage().window().maximize();

        return driver;
    }
}
