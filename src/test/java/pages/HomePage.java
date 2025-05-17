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

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.driver.get("https://store.steampowered.com/");
        this.wait = new WebDriverWait(driver, 10);
        if(!driver.getTitle().equals("Welcome to Steam")) {
            throw new IllegalStateException("This is not a HomePage. Current page is " + driver.getTitle());
        }
    }

    //TODO: Try to implement this method by finding the element that contains this text.
    // Currently, the tester cannot find this element: /html/body/div[1]/div[7]/div[1],
    // Which contains the text needed to verify the login.
    // But as it can be seen, the element is present (this test would fail otherwise).
    public Boolean isUserSignedIn() {
        return this.driver.getPageSource().contains("andre.shamanaev");
    }

    protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }
}
