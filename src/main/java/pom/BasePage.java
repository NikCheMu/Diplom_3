package pom;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;


public class BasePage {
    private final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }


    @Step("Получаем координаты элемента")
    public Point getElementLocation(By element) throws InterruptedException {
        Thread.sleep(2000);
       return driver.findElement(element).getLocation();
    }

}
