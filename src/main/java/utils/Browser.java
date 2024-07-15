package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browser {

    public static WebDriver getDriver(BrowserName browserName){
        switch (browserName){
            case CHROME:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
                return new ChromeDriver(options);
            case FIREFOX:
                return new FirefoxDriver();
            default:
                ChromeOptions defaultOptions = new ChromeOptions();
                defaultOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");
                return new ChromeDriver(defaultOptions);
        }
    };

    public enum BrowserName{
        CHROME,
        FIREFOX
    }
}
