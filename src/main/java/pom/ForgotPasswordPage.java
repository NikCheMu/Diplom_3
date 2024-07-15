package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage extends BasePage{

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }


    private final By logInRef = By.linkText("Войти");

    private final By inputFieldByName = By.name("name");

    @Step("Открываем страницу Забыли пароль")
    public ForgotPasswordPage openForgotPasswordPage(){
        getDriver().get("https://stellarburgers.nomoreparties.site/forgot-password");
        new WebDriverWait(getDriver(), Duration.ofSeconds(3) )
                .until(ExpectedConditions.visibilityOf(getDriver().findElement(inputFieldByName)));
        return this;

    }

    @Step("Нажимаем на кнопку Войти")
    public ForgotPasswordPage logInRefClick(){
        waitTillAnimationOverlayInvisible();
        new WebDriverWait(getDriver(),Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(getDriver().findElement(logInRef)));
        getDriver().findElement(logInRef).click();
        return this;
    }
}
