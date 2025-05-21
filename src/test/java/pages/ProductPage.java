package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage{

    // Page elements
    private final By productNameLocator = By.xpath("//*[@id=\"appHubAppName\"]");
    private final By productHighlightsLocator = By.xpath("//*[@id=\"game_highlights\"]");
    private final By productMetaDataLocator = By.xpath("//*[@id=\"tabletGrid\"]/div[1]/div[5]/div[1]");
    private final By productDescriptionLocator = By.xpath("//*[@id=\"tabletGrid\"]/div[1]/div[5]/div[2]");
    private final By relatedProductsLocator = By.xpath("//*[@id=\"tabletGrid\"]/div[1]/div[6]");
    private final By reviewsLocator = By.xpath("//*[@id=\"tabletGrid\"]/div[1]/div[7]");

    public ProductPage(WebDriver driver, String productName, String productUrl) {
        super(driver);
        this.driver.get("https://store.steampowered.com/app/"+productUrl);
        if(!driver.getTitle().equals(productName + " on Steam")) {
            throw new IllegalStateException("This is not a" + productName + " page. Current page is " + driver.getTitle());
        }
    }

    public String getProductName() {
        return this.waitAndReturnElement(productNameLocator).getText();
    }

    public Boolean isHighlightVisible() {
        try {
            this.waitAndReturnElement(productHighlightsLocator);
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public Boolean isProductMetadataVisible() {
        try {
            this.waitAndReturnElement(productMetaDataLocator);
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public Boolean isDescriptionVisible() {
        try {
            this.waitAndReturnElement(productDescriptionLocator);
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public Boolean isRelatedProductsVisible() {
        try {
            this.waitAndReturnElement(relatedProductsLocator);
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public Boolean isReviewsVisible() {
        try {
            this.waitAndReturnElement(reviewsLocator);
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }
}
