package app;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.GoogleMainPage;
import pages.GoogleResultPage;
import pages.TinkoffJobPage;
import pages.TinkoffMobileOperatorPage;
import test.BrowsersFactory;

import java.net.MalformedURLException;

import java.util.concurrent.TimeUnit;

public class Application {

    Logger logger = LoggerFactory.getLogger(Application.class);
    private WebDriverWait wait;
    private WebDriver driver;
    public GoogleMainPage google;
    public GoogleResultPage googleResults;
    public TinkoffJobPage tinkoffJob;
    public TinkoffMobileOperatorPage tinkoffMobile;

    public final String browserName = System.getProperty("browser") == null ? "chrome" : System.getProperty("browser");


    public Application() throws MalformedURLException {
        driver = new EventFiringWebDriver(getDriver());
        ((EventFiringWebDriver) driver).register(new BrowsersFactory.MyListener());
        wait = new WebDriverWait(driver, 10);
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //page
        google = new GoogleMainPage(driver);
        googleResults = new GoogleResultPage(driver);
        tinkoffJob = new TinkoffJobPage(driver);
        tinkoffMobile = new TinkoffMobileOperatorPage(driver);
    }

    public void quit() {
        driver.quit();
        driver = null;
    }

    private WebDriver getDriver() throws MalformedURLException {
        return BrowsersFactory.buildDriver(browserName);
    }

}
