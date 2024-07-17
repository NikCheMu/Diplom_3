import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pom.ForgotPasswordPage;
import pom.LoginPage;
import pom.MainPage;
import utils.Utils;

public class ForgotPasswordPageTest extends BaseTest {

    private MainPage mainPage;

    private LoginPage loginPage;

    private ForgotPasswordPage forgotPasswordPage;

    private String validName;

    private String validEmail;

    private String validPassword;

    @Before
    @Step("Prepare data")
    public void setUp() {

        mainPage = new MainPage(getDriver());

        loginPage = new LoginPage(getDriver());

        forgotPasswordPage = new ForgotPasswordPage(getDriver());

        validName = Utils.getRandomName();

        validEmail = Utils.getRandomEmail();

        validPassword = Utils.getRandomPassword(8);

        Utils.registerUser(validEmail, validPassword, validName);
    }

    @Test
    @DisplayName("Проверяем что пользователь может авторизоваться перейдя на экран авторизации по нажатию на Войти")
    public void successfullAuthorizationDrivesUsToMainPageViaLoginRefButton() {
        forgotPasswordPage.openForgotPasswordPage()
                .logInRefClick();

        loginPage.fillEmail(validEmail)
                .fillPassword(validPassword)
                .logInButtonClick();

        Assert.assertTrue(mainPage.isMainPageDisplayed());

        Assert.assertEquals("Оформить заказ", mainPage.getDynamicButtonName());
    }

    @After
    @Step("Clean up")
    public void teardown() {
        Utils.deleteUser(validEmail, validPassword);
    }
}
