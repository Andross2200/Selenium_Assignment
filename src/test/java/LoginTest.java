import org.junit.*;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.HomePage;
import pages.SignInPage;

public class LoginTest extends BaseTest{

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
