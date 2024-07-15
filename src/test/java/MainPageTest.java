import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import pom.*;
import utils.Browser;
import utils.Utils;

import java.util.concurrent.TimeUnit;


public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    private LoginPage loginPage;

    private String validEmail;

    private String validPassword;

    private String validName;


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
    @DisplayName("Проверяем что пользователь может авторизоваться по кнопке Войти в аккаунт на главной странице ")
    public void afterAuthorizationMainPageIsShownWithMakeOrderButtonFromMainPageLogInButton(){
        mainPage.openMainPage()
                .logInButtonClick();
        loginPage.fillEmail(validEmail)
                .fillPassword(validPassword)
                .logInButtonClick();
        Assert.assertTrue(mainPage.isMainPageDisplayed());
        Assert.assertEquals("Оформить заказ",mainPage.getDynamicButtonName());
    }


    @Test
    @DisplayName("Проверяем что при смене раздела список ингридиентов скроллится")
    public void listOfIngredientsScrollsWhenUserSelectSection() throws InterruptedException {
        mainPage.openMainPage();
        Point bunSectionStartLocation =  mainPage.getElementLocation(mainPage.getBunSection());
        Point sauceSectionStartLocation =  mainPage.getElementLocation(mainPage.getSauceSection());
        Point fillingSectionStartLocation = mainPage.getElementLocation(mainPage.getFillingSection());
        mainPage.sauceMenuSectionClick();
        mainPage.fillingseMenuSectionClick();
        Point bunSectionCurrentLocation =  mainPage.getElementLocation(mainPage.getBunSection());
        Point sauceSectionCurrentLocation =  mainPage.getElementLocation(mainPage.getSauceSection());
        Point fillingSectionCurrentLocation = mainPage.getElementLocation(mainPage.getFillingSection());
        Assert.assertTrue(bunSectionStartLocation.getY()>bunSectionCurrentLocation.getY());
        Assert.assertTrue(sauceSectionStartLocation.getY()>sauceSectionCurrentLocation.getY());
        Assert.assertTrue(fillingSectionStartLocation.getY()>fillingSectionCurrentLocation.getY());
    }

    @After
    @Step("Quit driver")
    public void teardown() {
        driver.quit();
        Utils.deleteUser(validEmail,validPassword);
    }
}
