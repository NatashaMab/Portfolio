package utils;

import org.openqa.selenium.WebDriver;
import pages.AdminPanelPage;
import pages.DashboardPage;
import pages.LandingPage;
import pages.LoginPage;

public class Base {

    public static final WebDriver driver = BrowserFactory.startBrowser("Chrome", "https://ndosisimplifiedautomation.vercel.app");
    public LandingPage landingpage = new LandingPage(driver);
    public LoginPage loginpage = new LoginPage(driver);
    public DashboardPage dashboardpage = new DashboardPage(driver);
    public AdminPanelPage adminpanelpage = new AdminPanelPage(driver);

}
