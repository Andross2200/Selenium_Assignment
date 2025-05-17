import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.HomePage;
import pages.SignInPage;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class LoginTest {

    private WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/home/selenium/chromedriver-linux64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-web-security");
        this.driver = new ChromeDriver(options);

    }

    @After
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }

    @Test
    public void testLogin() throws InterruptedException {
        SignInPage signInPage = new SignInPage(driver);
//        System.out.println(signInPage.getPageContent());
        HomePage homePage = signInPage.loginUser("", "");
        Assert.assertTrue("Login was successful", homePage.isUserSignedIn());
    }

    //TODO: Make a logout test that depends on the login test
}
