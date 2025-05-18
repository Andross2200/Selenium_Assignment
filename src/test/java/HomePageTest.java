import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.HomePage;

public class HomePageTest {
    private WebDriver driver;

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        this.driver = new ChromeDriver(options);
        this.driver.manage().window().maximize();
    }

    @Test
    public void testLoadingCorrectness() {
        HomePage homePage = new HomePage(driver);

        Assert.assertEquals("Page title", "Welcome to Steam", homePage.getPageTitle());
        Assert.assertTrue("Banner is visible", homePage.isBannerVisible());
        Assert.assertTrue("Store navigation is visible", homePage.isStoreNavigationVisible());
        Assert.assertTrue("Event ad is visible", homePage.isEventAdVisible());
        Assert.assertTrue("Special offers are visible", homePage.isSpecialOffersVisible());
        Assert.assertTrue("Carousels are visible", homePage.isCarouselsVisible());
        Assert.assertTrue("Trending is visible", homePage.isTrendingVisible());
    }
}
