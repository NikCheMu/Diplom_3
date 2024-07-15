import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pom.AccountPage;
import pom.Header;
import pom.LoginPage;
import pom.MainPage;
import utils.Utils;

public class HeaderNavigationTest {

    private WebDriver driver;
    private MainPage mainPage;

    private LoginPage loginPage;

    private AccountPage accountPage;

    private Header header;

    private String validName;

    private String validEmail;

    private String validPassword;


    @Before
    @Step("Prepare data and driver")
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        header = new Header(driver);
        accountPage = new AccountPage(driver);
        validName = Utils.getRandomName();
        validEmail = Utils.getRandomEmail();
        validPassword = Utils.getRandomPassword(8);
        Utils.registerUser(validEmail,validPassword,validName);
    }

    @Test
    @DisplayName("Проверяем что неавторизованный пользователь попадает на экран авторизации при нажатии на Личный кабинет")
    public void logInPageIsShownWhenUnauthorizedUserClickAccountButton(){
        mainPage.openMainPage();
        header.accountRefButtonClick();
        Assert.assertTrue(loginPage.isLogInFormDisplayed());
    }

    @Test
    @DisplayName("Проверяем что авторизованный пользователь попадает на экран личного кабинета при нажатии на Личный кабинет")
    public void profilePageIsShownWhenAuthorizedUserClickAccountButton(){
        mainPage.openMainPage();
        header.accountRefButtonClick();
        loginPage.fillEmail(validEmail)
                .fillPassword(validPassword)
                .logInButtonClick();
        header.accountRefButtonClick();
        Assert.assertTrue(accountPage.isAccountDataFormDisplayed());
    }

    @Test
    @DisplayName("Проверяем что отображается главная страница после нажатия на Конструктор")
    public void mainPageIsShownWhenUserClickConstructorRefButton(){
        mainPage.openMainPage();
        header.accountRefButtonClick();
        header.constructorRefButtonClick();
        Assert.assertTrue(mainPage.isMainPageDisplayed());
    }

    @Test
    @DisplayName("Проверяем что отображается главная страница после нажатия на Логотип")
    public void mainPageIsShownWhenUserClickLogoRefButton(){
        mainPage.openMainPage();
        header.accountRefButtonClick();
        header.logoRefButtonClick();
        Assert.assertTrue(mainPage.isMainPageDisplayed());
    }

    @Test
    @DisplayName("Проверяем что пользователь может авторизоваться перейдя на экран авторизации по нажатию на Личный кабинет")
    public void userLogInViaAuthorizationFormAfterAccountButtonPress(){
        mainPage.openMainPage();
        header.accountRefButtonClick();
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
        Utils.deleteUser(validEmail,validPassword);
    }
}
