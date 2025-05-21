import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.ProductPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ProductPageStaticTest extends BaseTest {
    private final String productName;
    private final String productUrl;

    public ProductPageStaticTest(String productName, String productUrl) {
        this.productName = productName;
        this.productUrl = productUrl;
    }

    @Test
    public void testStaticStructure() {
        ProductPage page = new ProductPage(this.driver, this.productName, this.productUrl);

        Assert.assertEquals("Page title is correct", this.productName + " on Steam", page.getPageTitle());
        Assert.assertEquals("Product name on page", this.productName, page.getProductName());
        Assert.assertTrue("Product highlight is present", page.isHighlightPresent());
        Assert.assertTrue("Product metadata is present", page.isProductMetadataPresent());
        Assert.assertTrue("Product description is present", page.isDescriptionPresent());
        Assert.assertTrue("Related products are present", page.isRelatedProductsPresent());
        Assert.assertTrue("Reviews are present", page.isReviewsPresent());
    }

    @Parameterized.Parameters
    public static Collection<Object> productsForTest() {
        return Arrays.asList(new Object[][] {
                {"Total War: SHOGUN 2", "201270/Total_War_SHOGUN_2/"},
                {"Stellaris", "281990/Stellaris/"},
                {"ENDLESS™ Space 2", "392110/ENDLESS_Space_2/"},
                {"Sid Meier's Civilization VII", "1295660/Sid_Meiers_Civilization_VII/"},
                {"Anno 1800", "916440/Anno_1800/"}
        });
    }
}