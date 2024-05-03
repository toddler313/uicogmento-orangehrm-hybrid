package actions.pageObjects.orangehrm;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.BasePageUI;

public class ViewSystemUsersPageObject extends BasePage {
    private WebDriver driver;
    public ViewSystemUsersPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

}
