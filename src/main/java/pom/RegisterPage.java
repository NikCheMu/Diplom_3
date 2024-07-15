package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends BasePage{
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    private final By inputFieldByName = By.name("name");

    private final By passwordField =  By.name("Пароль");

    private final By passwordErrorMessage = By.xpath(".//p[@class='input__error text_type_main-default']");

    private final By submitButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");

    private final By logInRefButton = By.xpath(".//a[contains(@href,'/login')]");

    @Step("Открываем страницу регистрации")
    public RegisterPage openRegisterPage(){
        getDriver().get("https://stellarburgers.nomoreparties.site/register");
        new WebDriverWait(getDriver(),Duration.ofSeconds(3) )
                .until(ExpectedConditions.visibilityOf(getDriver().findElement(inputFieldByName)));
        return this;
    }

    @Step("Заполняем Имя")
    public RegisterPage fillName(String name){
        getDriver().findElements(inputFieldByName).get(0).sendKeys(name);
        return this;
    }

    @Step("Заполняем Email")
    public RegisterPage fillEmail(String email){
        getDriver().findElements(inputFieldByName).get(1).sendKeys(email);
        return this;
    }
    @Step("Заполняем пароль")
    public RegisterPage fillPassword(String password){
        getDriver().findElement(passwordField).sendKeys(password);
        return this;
    }

    @Step("Нажимем Зарегистрироваться")
    public RegisterPage submitButtonClick(){
        getDriver().findElement(submitButton).click();
        return this;
    }

    @Step("Переходим на экран авторизации")
    public RegisterPage logInRefButtonClick(){
        getDriver().findElement(logInRefButton).click();
        return this;
    }

    @Step("Проверяем что валидационное сообщение отображается")
    public boolean isPasswordErrorMessageDisplayed(){
        return new WebDriverWait(getDriver(), Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOf(getDriver().findElement(passwordErrorMessage))).isDisplayed();
    }

    @Step("Получаем текст сообщения")
    public String passwordErrorMessageText(){
        return getDriver().findElement(passwordErrorMessage).getText();
    }
}
