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
import pom.RegisterPage;
import utils.Utils;

public class RegisterPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    private LoginPage loginPage;

    private RegisterPage registerPage;

    private String validName;

    private String validEmail;

    private String invalidPassword;

    private String validPassword;

    @Before
    @Step("Prepare data and driver")
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        validName = Utils.getRandomName();
        validEmail = Utils.getRandomEmail();
        validPassword = Utils.getRandomPassword(8);
        invalidPassword = Utils.getRandomPassword(5);
    }

    @Test
    @DisplayName("Проверяем что при невалидном пароле присутствует сообщение Некорректный пароль")
    public void errorMessageDisplayedIfShortPasswordProvided(){
        registerPage.openRegisterPage()
                .fillName(validName)
                .fillEmail(validEmail)
                .fillPassword(invalidPassword)
                .submitButtonClick();
        Assert.assertTrue(registerPage.isPasswordErrorMessageDisplayed());
        Assert.assertEquals("Некорректный пароль", registerPage.passwordErrorMessageText());
    }

    @Test
    @DisplayName("Проверяем что при успешной регистрации открыввается страница авторизации")
    public void succesfullRegistrationDrivesUsToLogInForm(){
        registerPage.openRegisterPage()
                .fillName(validName)
                .fillEmail(validEmail)
                .fillPassword(validPassword)
                .submitButtonClick();
        Assert.assertTrue(loginPage.isLogInFormDisplayed());
    }

    @Test
    @DisplayName("Проверяем что пользователь может авторизоваться перейдя с экрана регистрации на экран авторизации")
    public void successfullAuthorizationDrivesUsToMainPageViaLoginRefButton(){
        registerPage.openRegisterPage()
                .logInRefButtonClick();
        loginPage.fillEmail(validEmail)
                .fillPassword(validPassword)
                .logInButtonClick();
        Assert.assertTrue(mainPage.isMainPageDisplayed());
        Assert.assertEquals("Оформить заказ",mainPage.getDynamicButtonName());
    }



    @After
    @Step("Quit driver")
    public void teardown() {
        driver.quit();
    }
}
