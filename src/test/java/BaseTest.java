import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import utils.Browser;

public class BaseTest {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @Before
    @Step("Prepare driver for test")
    public void baseSetUp(){
        driver = Browser.getDriver();

        driver.manage().window().maximize();
    }

    @After
    @Step("Quit driver")
    public void baseTearDown(){
        driver.quit();
    }
}
