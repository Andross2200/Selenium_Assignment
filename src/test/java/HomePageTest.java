import org.junit.Assert;
import org.junit.Test;
import pages.HomePage;

public class HomePageTest extends BaseTest {
    
    @Test
    public void testLoadingCorrectness() {
        HomePage homePage = new HomePage(driver);

        Assert.assertEquals("Page title", "Welcome to Steam", homePage.getPageTitle());
        Assert.assertTrue("Banner is visible", homePage.isBannerVisible());
        Assert.assertTrue("Store navigation is visible", homePage.isStoreNavigationVisible());
        Assert.assertTrue("Special offers are visible", homePage.isSpecialOffersVisible());
        Assert.assertTrue("Carousels are visible", homePage.isCarouselsVisible());
        Assert.assertTrue("Trending is visible", homePage.isTrendingVisible());
    }
}
