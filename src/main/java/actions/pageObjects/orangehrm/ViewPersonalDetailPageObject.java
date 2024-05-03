package actions.pageObjects.orangehrm;

import actions.commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.orangehrm.ViewPersonalDetailPageUI;

public class ViewPersonalDetailPageObject extends BasePage {
    private WebDriver driver;
    public ViewPersonalDetailPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step
    public String getEmployeeNameOnHeader() {
        waitForWebElementVisible(ViewPersonalDetailPageUI.EMPLOYEE_NAME_HEADER);
        System.out.println(getTextFromWebElement(ViewPersonalDetailPageUI.EMPLOYEE_NAME_HEADER));
        return getTextFromWebElement(ViewPersonalDetailPageUI.EMPLOYEE_NAME_HEADER);
    }
}
