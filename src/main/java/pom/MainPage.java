package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MainPage extends BasePage {
    private final By burgerIngredientsSection = By.className("BurgerIngredients_ingredients__1N8v2");
    private final By dynamicButton = By.xpath(".//div[@class = 'BurgerConstructor_basket__container__2fUl3 mt-10']//button");
    private final By bunSection = By.xpath(".//h2[contains(@class, 'text text_type_main-medium mb-6 mt-10') and text() = 'Булки']");
    private final By sauceMenuSelect = By.xpath(".//span[contains(@class, 'text text_type_main-default') and text() = 'Соусы']");
    private final By sauceSection = By.xpath(".//h2[contains(@class, 'text text_type_main-medium mb-6 mt-10') and text() = 'Соусы']");
    private final By fillingMenuSelect = By.xpath(".//span[contains(@class, 'text text_type_main-default') and text() = 'Начинки']");
    private final By fillingSection = By.xpath(".//h2[contains(@class, 'text text_type_main-medium mb-6 mt-10') and text() = 'Начинки']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public By getBunSection() {
        return bunSection;
    }

    public By getSauceSection() {
        return sauceSection;
    }

    public By getFillingSection() {
        return fillingSection;
    }

    @Step("Открываем главную страницу")
    public MainPage openMainPage() {
        getDriver().get("https://stellarburgers.nomoreparties.site/");
        new WebDriverWait(getDriver(), Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(getDriver().findElement(burgerIngredientsSection)));
        return this;

    }

    @Step("Нажимаем Войти в аккаунт")
    public MainPage logInButtonClick() {
        waitTillAnimationOverlayInvisible();
        new WebDriverWait(getDriver(), Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(getDriver().findElement(dynamicButton)));
        getDriver().findElement(dynamicButton).click();
        return this;
    }

    @Step("Выбираем пункт меню - Соусы")
    public MainPage sauceMenuSectionClick() {
        getDriver().findElement(sauceMenuSelect).click();
        return this;
    }

    @Step("Выбираем пункт меню - Начинки")
    public MainPage fillingseMenuSectionClick() {
        getDriver().findElement(fillingMenuSelect).click();
        return this;
    }


    @Step("Проверяем что главная страница открылась")
    public boolean isMainPageDisplayed() {
        getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return getDriver().findElement(burgerIngredientsSection).isDisplayed();
    }

    @Step("Получаем название кнопки Войти в аккаунт/Оформить заказ")
    public String getDynamicButtonName() {
        return getDriver().findElement(dynamicButton).getText();
    }


}
