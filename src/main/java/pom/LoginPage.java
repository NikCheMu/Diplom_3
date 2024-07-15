package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By logInForm = By.className("Auth_login__3hAey");

    private final By inputFieldByName = By.name("name");

    private final By passwordField =  By.name("Пароль");

    private final By logInButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");

    @Step("Открываем страницу авторизации")
    public LoginPage openLoginPage(){
        getDriver().get("https://stellarburgers.nomoreparties.site/login");
        new WebDriverWait(getDriver(),Duration.ofSeconds(3) )
                .until(ExpectedConditions.visibilityOf(getDriver().findElement(inputFieldByName)));
        return this;
    }

    @Step("Заполняем Email")
    public LoginPage fillEmail(String email){
        getDriver().findElement(inputFieldByName).sendKeys(email);
        return this;
    }

    @Step("Заполняем пароль")
    public LoginPage fillPassword(String password){
        getDriver().findElement(passwordField).sendKeys(password);
        return this;
    }

    @Step("Нажимаем кнопку Войти")
    public LoginPage logInButtonClick(){
        waitTillAnimationOverlayInvisible();
        new WebDriverWait(getDriver(),Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(getDriver().findElement(logInButton)));
        getDriver().findElement(logInButton).click();
        return this;
    }

    @Step("Проверяем что открылась страница авторизации")
    public boolean isLogInFormDisplayed(){
        getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return getDriver().findElement(logInForm).isDisplayed();
    }
}
