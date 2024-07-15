package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header extends BasePage{

    public Header(WebDriver driver) {
        super(driver);
    }

    private final By accountRefButton = By.xpath(".//a[contains(@href,'/account')]");

    private final By logoRefButton = By.xpath(".//a[contains(@href,'/')]");

    private final By constructorRefButton = By.linkText("Конструктор");

    @Step("Нажимаем кнопку Личный кабинет")
    public Header accountRefButtonClick(){
        getDriver().findElement(accountRefButton).click();
        return this;
    }

    @Step("Нажимаем кнопку Конструктор")
    public Header constructorRefButtonClick(){
        getDriver().findElement(constructorRefButton).click();
        return this;
    }

    @Step("Нажимаем на логотип")
    public Header logoRefButtonClick(){
        getDriver().findElement(logoRefButton).click();
        return this;
    }



}
