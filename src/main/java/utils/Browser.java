package utils;

import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Browser {


    @Step("Read target browser from .env")
    public static WebDriver getDriver(){

        Dotenv dotenv = null;
        dotenv = Dotenv.configure().load();
        String browser = dotenv.get("BROWSER");
        switch (browser){
            case "CHROME":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
                return new ChromeDriver(options);
            case "FIREFOX":
                return new FirefoxDriver();
            default:
                ChromeOptions defaultOptions = new ChromeOptions();
                defaultOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");
                return new ChromeDriver(defaultOptions);
        }
    };
}
