package actions.pageObjects.orangehrm;

import actions.commons.BasePage;
import actions.commons.PageObjectManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.orangehrm.ViewEmployeeListPageUI;

public class ViewEmployeeListPageObject extends BasePage {
    private WebDriver driver;

    public ViewEmployeeListPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step
    public AddEmployeePageObject clickOnAddNewEmployeeButton() {
        waitForWebElementClickable(ViewEmployeeListPageUI.ADD_EMPLOYEE_BUTTON);
        clickOnWebElement(ViewEmployeeListPageUI.ADD_EMPLOYEE_BUTTON);
        return PageObjectManager.getAddEmployeePage(driver);
    }
}
