import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class SeleniumTest {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By usernameInputLocator = By.id("username");
    private final By passwordInputLocator = By.id("password");
    private final By buttonLocator = By.className("radius");
    private final By popupLocator = By.id("flash");

    @Before
    public void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-debugging-pipe");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
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

    @After
    public void closeBrowser() {
        driver.quit();
    }
}