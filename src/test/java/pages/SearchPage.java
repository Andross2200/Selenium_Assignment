package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage extends BasePage{

    // Page Elements
    private final By searchFilterLocator =
            By.xpath("//*[@class=\"page_content\"]/div[@class=\"termcontainer\"]/div[@class=\"searchtag tag_dynamic\"]/span");

    public SearchPage(WebDriver driver, String searchString) {
        super(driver);
        this.driver.get("https://store.steampowered.com/search/?term="+searchString.replace(" ", "+"));
        if(!driver.getTitle().equals("Steam Search")) {
            throw new IllegalStateException("This is not a Search page. Current page is " + driver.getTitle());
        }
    }

    public String getSearchFilter() {
        WebElement searchFilter = this.waitAndReturnElement(searchFilterLocator);
        return searchFilter.getText().replace("\"", "");
    }
}
