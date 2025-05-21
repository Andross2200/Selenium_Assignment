package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{
    // Interactive elements
    private final By usernameLocator = By.xpath("//*[@id=\"account_pulldown\"]");
    private final By SignOutButtonLocator = By.xpath("//*[@id=\"account_dropdown\"]/div/a[4]");
    private final By searchBarLocator = By.xpath("//*[@id=\"store_nav_search_term\"]");

    // Static page elements
    private final By bannerLocator = By.xpath("//*[@id=\"global_header\"]");
    private final By storeNavigationLocator = By.xpath("//*[@id=\"responsive_page_template_content\"]/div[3]/div[2]/div[4]/div");
    private final By specialOffersLocator = By.xpath("//*[@id=\"module_special_offers\"]");
    private final By[] carouselsLocators = {
            By.xpath("//*[@id=\"responsive_page_template_content\"]/div[3]/div[2]/div[4]"),
            By.xpath("//*[@id=\"responsive_page_template_content\"]/div[3]/div[2]/div[7]"),
            By.xpath("//*[@id=\"responsive_page_template_content\"]/div[3]/div[2]/div[8]"),
    };
    private final By trendingLocator = By.xpath("//*[@id=\"responsive_page_template_content\"]/div[3]/div[2]/div[22]");

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver.get("https://store.steampowered.com/");
        if(!driver.getTitle().equals("Welcome to Steam")) {
            throw new IllegalStateException("This is not a HomePage. Current page is " + driver.getTitle());
        }
    }

    public Boolean isUserSignedIn() {
        return this.waitAndReturnElement(usernameLocator).getText().contains("andre.shamanaev");
    }

    public void signOut() {
        this.waitAndReturnElement(usernameLocator).click();
        this.waitAndReturnElement(SignOutButtonLocator).click();
    }

    public Boolean isBannerVisible() {
        try {
            this.waitAndReturnElement(bannerLocator);
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public Boolean isStoreNavigationVisible() {
        try {
            this.waitAndReturnElement(storeNavigationLocator);
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public Boolean isSpecialOffersVisible() {
        try {
            this.waitAndReturnElement(specialOffersLocator);
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public Boolean isCarouselsVisible() {
        try {
            for(By carousel : carouselsLocators) {
                this.waitAndReturnElement(carousel);
            }
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public Boolean isTrendingVisible() {
        try {
            this.waitAndReturnElement(trendingLocator);
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public SearchPage searchForProduct(String searchString) {
        WebElement seachBar = this.waitAndReturnElement(searchBarLocator);
        seachBar.sendKeys(searchString+"\n");

        return new SearchPage(this.driver, searchString);
    }
}
