import org.junit.*;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.HomePage;
import pages.SignInPage;

public class LoginTest {

    private WebDriver driver;

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        this.driver = new ChromeDriver(options);
        this.driver.manage().window().maximize();
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
        HomePage homePage = signInPage.loginUser("", "");
        Assert.assertTrue("Login was successful", homePage.isUserSignedIn());
    }

    @Test(expected = TimeoutException.class)
    public void testLogout() throws InterruptedException {
        SignInPage signInPage = new SignInPage(driver);
        HomePage homePage = signInPage.loginUser("", "");
        homePage.signOut();
        homePage.isUserSignedIn();
    }
}
