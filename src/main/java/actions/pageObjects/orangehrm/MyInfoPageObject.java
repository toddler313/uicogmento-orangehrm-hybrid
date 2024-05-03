package actions.pageObjects.orangehrm;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;

public class MyInfoPageObject extends BasePage {
    private WebDriver driver;
    public MyInfoPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
