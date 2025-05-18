package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    // Locators
    private final By usernameLocator = By.xpath("//*[@id=\"account_pulldown\"]");
    private final By SignOutButtonLocator = By.xpath("//*[@id=\"account_dropdown\"]/div/a[4]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.driver.get("https://store.steampowered.com/");
        this.wait = new WebDriverWait(driver, 10);
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

    protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }
}
