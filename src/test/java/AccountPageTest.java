import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pom.AccountPage;
import pom.Header;
import pom.LoginPage;
import pom.MainPage;
import utils.Utils;

public class AccountPageTest extends BaseTest{
    private MainPage mainPage;

    private LoginPage loginPage;

    private AccountPage accountPage;

    private Header header;

    private String validName;

    private String validEmail;

    private String validPassword;


    @Before
    @Step("Prepare data")
    public void setUp() {
        mainPage = new MainPage(getDriver());

        loginPage = new LoginPage(getDriver());

        header = new Header(getDriver());

        accountPage = new AccountPage(getDriver());

        validName = Utils.getRandomName();

        validEmail = Utils.getRandomEmail();

        validPassword = Utils.getRandomPassword(8);

        Utils.registerUser(validEmail, validPassword, validName);
    }

    @Test
    @DisplayName("Проверяем что после нажатия на Выход отображается форма авторизации")
    public void logInPageIsShownWhenUserClickLogOutButton() {
        mainPage.openMainPage()
                .logInButtonClick();

        loginPage.fillEmail(validEmail)
                .fillPassword(validPassword)
                .logInButtonClick();

        header.accountRefButtonClick();

        accountPage.waitAccountDataFormDisplayed()
                .logOutButtonClick();

        Assert.assertTrue(loginPage.isLogInFormDisplayed());
    }

    @After
    @Step("Clean up")
    public void teardown() {
        Utils.deleteUser(validEmail, validPassword);
    }
}
