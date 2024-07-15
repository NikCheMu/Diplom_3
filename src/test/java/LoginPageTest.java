import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pom.LoginPage;
import pom.MainPage;
import utils.Browser;
import utils.Utils;

import java.util.concurrent.TimeUnit;

public class LoginPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    private LoginPage loginPage;

    private String validName;

    private String validEmail;

    private String validPassword;

    @Before
    @Step("Prepare data and driver")
    public void setUp(){
        driver = Browser.getDriver();
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        validName = Utils.getRandomName();
        validEmail = Utils.getRandomEmail();
        validPassword = Utils.getRandomPassword(8);
        Utils.registerUser(validEmail,validPassword,validName);
    }


    @Test
    @DisplayName("Проверяем что после успешной авторизации отображается главная страница")
    public void succesfullAuthorizationDrivesUserToMainPage(){
        loginPage.openLoginPage()
                .fillEmail(validEmail)
                .fillPassword(validPassword)
                .logInButtonClick();
        Assert.assertTrue(mainPage.isMainPageDisplayed());
        Assert.assertEquals("Оформить заказ",mainPage.getDynamicButtonName());
    }

    @After
    @Step("Quit driver")
    public void teardown() {
        driver.quit();
        Utils.deleteUser(validEmail,validPassword);
    }
}
