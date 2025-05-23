package pages;

import jakarta.mail.MessagingException;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import util.EmailUtil;

public class SignInPage extends BasePage{

    // Locators
    private final By usernameFieldLocator =
            By.xpath("//*[@id=\"responsive_page_template_content\"]/div[3]/div[1]/div/div/div/div[2]/div/form/div[1]/input");
    private final By passwordFieldLocator =
            By.xpath("//*[@id=\"responsive_page_template_content\"]/div[3]/div[1]/div/div/div/div[2]/div/form/div[2]/input");
    private final By loginButtonLocator =
            By.xpath("//*[@id=\"responsive_page_template_content\"]/div[3]/div[1]/div/div/div/div[2]/div/form/div[4]/button");
    private final By steamGuardRequestLocator =
            By.xpath("//*[@id=\"responsive_page_template_content\"]/div[3]/div[1]/div/div/div/div[2]/form/div/div[1]/div[2]");
    private final By[] verificationCodeLocators = {
            By.xpath("//*[@id=\"responsive_page_template_content\"]/div[3]/div[1]/div/div/div/div[2]/form/div/div[2]/div[1]/div/input[1]"),
            By.xpath("//*[@id=\"responsive_page_template_content\"]/div[3]/div[1]/div/div/div/div[2]/form/div/div[2]/div[1]/div/input[2]"),
            By.xpath("//*[@id=\"responsive_page_template_content\"]/div[3]/div[1]/div/div/div/div[2]/form/div/div[2]/div[1]/div/input[3]"),
            By.xpath("//*[@id=\"responsive_page_template_content\"]/div[3]/div[1]/div/div/div/div[2]/form/div/div[2]/div[1]/div/input[4]"),
            By.xpath("//*[@id=\"responsive_page_template_content\"]/div[3]/div[1]/div/div/div/div[2]/form/div/div[2]/div[1]/div/input[5]")
    };

    public SignInPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://store.steampowered.com/login/");
        if(!driver.getTitle().equals("Sign In")) {
            throw new IllegalStateException("This is not a SignIn page. Current page is " + driver.getTitle());
        }
    }

    public HomePage loginUser(String username, String password) throws InterruptedException {
        this.waitAndReturnElement(usernameFieldLocator).sendKeys(username);
        this.waitAndReturnElement(passwordFieldLocator).sendKeys(password);
        this.waitAndReturnElement(loginButtonLocator).click();

        if (this.isSteamGuardRequired()) {
            Thread.sleep(10000);
            String code = getLoginVerificationCode();
            System.out.println("Code: " + code);
            for (int i = 0; i < code.length(); i++) {
                this.waitAndReturnElement(verificationCodeLocators[i]).sendKeys(String.valueOf(code.charAt(i)));
            }
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

    private Boolean isSteamGuardRequired() {
        try {
            this.waitAndReturnElement(steamGuardRequestLocator);
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }
}
