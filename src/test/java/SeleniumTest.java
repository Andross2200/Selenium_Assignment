import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumTest {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By usernameInputLocator = By.id("username");
    private final By passwordInputLocator = By.id("password");
    private final By buttonLocator = By.className("radius");
    private final By popupLocator = By.id("flash");

    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
//        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.wait = new WebDriverWait(driver, 10);
    }

    @After
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }

    @Test
    public void testCorrectCredentials() {
        this.driver.get("http://the-internet.herokuapp.com/login");

        WebElement usernameInput = waitVisibiiltyAndFindElement(usernameInputLocator);
        usernameInput.sendKeys("tomsmith");

        WebElement passwordInput = waitVisibiiltyAndFindElement(passwordInputLocator);
        passwordInput.sendKeys("SuperSecretPassword!");

        WebElement loginButton = waitVisibiiltyAndFindElement(buttonLocator);
        loginButton.click();

        WebElement loginConfirmationPopup = waitVisibiiltyAndFindElement(popupLocator);
        Assert.assertTrue("Correct credentials login", loginConfirmationPopup.getText().contains("You logged into a secure area!"));

        WebElement logoutButton = waitVisibiiltyAndFindElement(buttonLocator);
        logoutButton.click();

        WebElement logoutConfirmationPopup = waitVisibiiltyAndFindElement(popupLocator);
        Assert.assertTrue("Correct credentials logout", logoutConfirmationPopup.getText().contains("You logged out of the secure area!"));
    }

    @Test
    public void testIncorrectCredentials() {
        this.driver.get("http://the-internet.herokuapp.com/login");

        WebElement usernameInput = waitVisibiiltyAndFindElement(usernameInputLocator);
        usernameInput.sendKeys("user \n");

        WebElement passwordInput = waitVisibiiltyAndFindElement(passwordInputLocator);
        passwordInput.sendKeys("password \n");

        WebElement loginButton = waitVisibiiltyAndFindElement(buttonLocator);
        loginButton.click();

        WebElement errorPopup = waitVisibiiltyAndFindElement(popupLocator);
        Assert.assertTrue("Incorrect credentials login", errorPopup.getText().contains("Your username is invalid!"));
    }

    private WebElement waitVisibiiltyAndFindElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }
}
