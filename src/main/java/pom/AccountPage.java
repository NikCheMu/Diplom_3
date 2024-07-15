package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class AccountPage extends BasePage{
    public AccountPage(WebDriver driver) {
        super(driver);
    }

    private final By accountDataForm = By.className("Profile_profile__3dzvr");

    private final By logOutButton = By.xpath(".//button[contains(@class,'Account_button__14Yp3')]");

    @Step("Проверяем что открылась страница Профиль")
    public boolean isAccountDataFormDisplayed(){
        getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return getDriver().findElement(accountDataForm).isDisplayed();
    }

    @Step("Ждем пока откроется страница Профиль")
    public AccountPage waitAccountDataFormDisplayed(){
        getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        getDriver().findElement(accountDataForm);
        return this;
    }

    @Step("Нажимаем кнопку Выход")
    public AccountPage logOutButtonClick(){
        getDriver().findElement(logOutButton).click();
        return this;
    }

}
