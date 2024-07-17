package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BasePage {
    private final WebDriver driver;
    private By animationOverlay = By.xpath(".//div[@class = 'Modal_modal_overlay__x2ZCr']");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    @Step("Ждем пока завершится анимация загрузки")
    public BasePage waitTillAnimationOverlayInvisible() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(3)).until(ExpectedConditions.invisibilityOf(getDriver().findElements(animationOverlay).get(0)));
        new WebDriverWait(getDriver(), Duration.ofSeconds(3)).until(ExpectedConditions.invisibilityOf(getDriver().findElements(animationOverlay).get(1)));
        return this;
    }


    @Step("Получаем координаты элемента")
    public Point getElementLocation(By element) throws InterruptedException {
        Thread.sleep(2000);
        return driver.findElement(element).getLocation();
    }

}
