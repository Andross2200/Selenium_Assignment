import org.junit.Assert;
import org.junit.Test;
import pages.HomePage;
import pages.SearchPage;
import pages.SignInPage;

public class ProductSearchTest extends BaseTest {

    @Test
    public void testSearchAfterLogin() throws InterruptedException {
        String searchTerm = "civ V";
        SignInPage signInPage = new SignInPage(driver);
        HomePage homePage = signInPage.loginUser("selenium_tester", "Ogw5?ai6");
        SearchPage searchPage = homePage.searchForProduct(searchTerm);

        Assert.assertEquals("Filter of search is same as search term", searchTerm, searchPage.getSearchFilter());
    }
}