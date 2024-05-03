package actions.pageObjects.orangehrm;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.BasePageUI;

public class DashboardPageObject extends BasePage {
    private WebDriver driver;
    public DashboardPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

}
