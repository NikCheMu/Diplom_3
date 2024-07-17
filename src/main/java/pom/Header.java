package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header extends BasePage {

    private final By accountRefButton = By.xpath(".//a[contains(@href,'/account')]");
    private final By logoRefButton = By.xpath(".//a[contains(@href,'/')]");
    private final By constructorRefButton = By.linkText("Конструктор");

    public Header(WebDriver driver) {
        super(driver);
    }

    @Step("Нажимаем кнопку Личный кабинет")
    public Header accountRefButtonClick() {
        waitTillAnimationOverlayInvisible();
        new WebDriverWait(getDriver(), Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(getDriver().findElement(accountRefButton)));
        getDriver().findElement(accountRefButton).click();
        return this;
    }

    @Step("Нажимаем кнопку Конструктор")
    public Header constructorRefButtonClick() {
        waitTillAnimationOverlayInvisible();
        new WebDriverWait(getDriver(), Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(getDriver().findElement(constructorRefButton)));
        getDriver().findElement(constructorRefButton).click();
        return this;
    }

    @Step("Нажимаем на логотип")
    public Header logoRefButtonClick() {
        waitTillAnimationOverlayInvisible();
        new WebDriverWait(getDriver(), Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(getDriver().findElement(logoRefButton)));
        getDriver().findElement(logoRefButton).click();
        return this;
    }


}
