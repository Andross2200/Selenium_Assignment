import org.junit.*;
import org.openqa.selenium.TimeoutException;
import pages.HomePage;
import pages.SignInPage;
import util.MyConfig;

public class LoginTest extends BaseTest{

    @Test
    public void testLogin() throws InterruptedException {
        SignInPage signInPage = new SignInPage(driver);
        MyConfig config = new MyConfig();
        HomePage homePage = signInPage.loginUser(config.getProperty("steam.username"), config.getProperty("steam.password"));
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
