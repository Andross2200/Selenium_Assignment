import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertEquals;

public class SeleniumTest {
    private static ChromeDriver driver;

    @Before
    public void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-debugging-pipe");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
    }

    @Test
    public void shouldPrintPageTitle() {
        driver.get("https://makers.tech");
        assertEquals("We are Makers. Building the future.", driver.getTitle());
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}