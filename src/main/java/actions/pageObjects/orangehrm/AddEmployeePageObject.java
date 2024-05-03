package actions.pageObjects.orangehrm;

import actions.commons.BasePage;
import actions.util.PropertiesConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.BasePageUI;
import pageUIs.orangehrm.AddEmployeePageUI;

public class AddEmployeePageObject extends BasePage {
    private WebDriver driver;
    public AddEmployeePageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step
    public void enterFirstName(String firstName) {
        waitForWebElementVisible(AddEmployeePageUI.FIRSTNAME_TEXT_BOX);
        sendKeysToWebElement(firstName, AddEmployeePageUI.FIRSTNAME_TEXT_BOX);
    }

    @Step
    public void enterMiddleName(String middleName) {
        waitForWebElementVisible(AddEmployeePageUI.MIDDLENAME_TEXT_BOX);
        sendKeysToWebElement(middleName, AddEmployeePageUI.MIDDLENAME_TEXT_BOX);
    }

    @Step
    public void enterLastName(String lastName) {
        waitForWebElementVisible(AddEmployeePageUI.LASTNAME_TEXT_BOX);
        sendKeysToWebElement(lastName, AddEmployeePageUI.LASTNAME_TEXT_BOX);
    }

    @Step
    public void enterEmployeeId(String employeeId) {
        waitForWebElementVisible(AddEmployeePageUI.EMPLOYEE_ID_TEXT_BOX);
        sendKeysToWebElement(employeeId, AddEmployeePageUI.EMPLOYEE_ID_TEXT_BOX);
    }

    @Step
    public void clickOnSaveButton() {
        waitForWebElementClickable(BasePageUI.GREEN_SAVE_BUTTONS);
        clickOnWebElement(BasePageUI.GREEN_SAVE_BUTTONS);
    }

    @Step
    public void uploadEmployeePortrait(String fileName, String filePath, String runTestsIn) {
        isImageLoaded(AddEmployeePageUI.UPLOAD_PORTRAIT_IMG);
        switch (runTestsIn) {
            case "local" -> {
                uploadFile(AddEmployeePageUI.UPLOAD_PORTRAIT_INPUT, filePath);
            }
            case "docker" -> {
                uploadFile(AddEmployeePageUI.UPLOAD_PORTRAIT_INPUT,
                        PropertiesConfig.configReader().getDockerFilePath() + fileName);
            }
        }
    }


}
