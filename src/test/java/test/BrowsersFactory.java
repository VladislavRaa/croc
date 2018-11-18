package test;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BrowsersFactory {

    public static class MyListener extends AbstractWebDriverEventListener {

        Logger logger = LoggerFactory.getLogger(BrowsersFactory.class);

        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            logger.info("Обращение к элементу " + by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            logger.info("Найден элемент " + by);
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File file = new File("target", "sccreen-" + System.currentTimeMillis() + ".png");
            try {
                Files.copy(tmp, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.error(file.getAbsolutePath());
        }
    }

    public static WebDriver buildDriver(String browserName) throws MalformedURLException {
        switch (browserName) {

            case "chrome_invisible":
                ChromeOptions chromeInvisibleOpt = new ChromeOptions();
                chromeInvisibleOpt.addArguments("--disable-notifications");
                chromeInvisibleOpt.addArguments("--headless");
                return new ChromeDriver(chromeInvisibleOpt);

            case "firefox":
                //Disable login to console and redirect log to an external file
                System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
                System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "./src/test/java/firefox_logs/log");

                FirefoxOptions ffOpt = new FirefoxOptions();
                ffOpt.addPreference("dom.webnotifications.enabled", false);
                return new FirefoxDriver(ffOpt);

            default:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications");
                LoggingPreferences logPrefs = new LoggingPreferences();
                logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
                options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
                return new ChromeDriver(options);
        }
    }
}