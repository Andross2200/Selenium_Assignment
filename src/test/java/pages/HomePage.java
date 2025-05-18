package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage{
    // Locators
    private final By usernameLocator = By.xpath("//*[@id=\"account_pulldown\"]");
    private final By SignOutButtonLocator = By.xpath("//*[@id=\"account_dropdown\"]/div/a[4]");

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
}
