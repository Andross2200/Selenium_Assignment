package pages;

import jakarta.mail.MessagingException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.EmailUtil;

public class SignInPage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    // Locators
    private final By usernameFieldLocator =
            By.xpath("//*[@id=\"responsive_page_template_content\"]/div[3]/div[1]/div/div/div/div[2]/div/form/div[1]/input");
    private final By passwordFieldLocator =
            By.xpath("//*[@id=\"responsive_page_template_content\"]/div[3]/div[1]/div/div/div/div[2]/div/form/div[2]/input");
    private final By loginButtonLocator =
            By.xpath("//*[@id=\"responsive_page_template_content\"]/div[3]/div[1]/div/div/div/div[2]/div/form/div[4]/button");
    private final By[] verificationCodeLocators = {
            By.xpath("//*[@id=\"responsive_page_template_content\"]/div[3]/div[1]/div/div/div/div[2]/form/div/div[2]/div[1]/div/input[1]"),
            By.xpath("//*[@id=\"responsive_page_template_content\"]/div[3]/div[1]/div/div/div/div[2]/form/div/div[2]/div[1]/div/input[2]"),
            By.xpath("//*[@id=\"responsive_page_template_content\"]/div[3]/div[1]/div/div/div/div[2]/form/div/div[2]/div[1]/div/input[3]"),
            By.xpath("//*[@id=\"responsive_page_template_content\"]/div[3]/div[1]/div/div/div/div[2]/form/div/div[2]/div[1]/div/input[4]"),
            By.xpath("//*[@id=\"responsive_page_template_content\"]/div[3]/div[1]/div/div/div/div[2]/form/div/div[2]/div[1]/div/input[5]")
    };

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get("https://store.steampowered.com/login/");
        this.wait = new WebDriverWait(driver, 10);
        if(!driver.getTitle().equals("Sign In")) {
            throw new IllegalStateException("This is not a SignIn page. Current page is " + driver.getTitle());
        }
    }

    protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    public HomePage loginUser(String username, String password) throws InterruptedException {
        // Enter username and password to initiate login process
        this.waitAndReturnElement(usernameFieldLocator).sendKeys(username);
        this.waitAndReturnElement(passwordFieldLocator).sendKeys(password);
        this.waitAndReturnElement(loginButtonLocator).click();
        // Verify login with code from email
        Thread.sleep(10000); // This test is needed to give Steam time to send the confirmation email
        String code = getLoginVerificationCode();
        System.out.println("Code: " + code);
        for (int i = 0; i < code.length(); i++) {
            this.waitAndReturnElement(verificationCodeLocators[i]).sendKeys(String.valueOf(code.charAt(i)));
        }
        Thread.sleep(10000);
        return new HomePage(driver);
    }

    private String getLoginVerificationCode() {
        EmailUtil emailUtil = new EmailUtil();
        String emailBody;
        try {
            emailBody = emailUtil.getNewEmailBody("Steam Support", "Your Steam account: Access from new web or mobile device");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return emailBody.split("Login Code")[1].substring(0,5);
    }

    public String getPageContent() {
        return this.driver.getPageSource();
    }
}
