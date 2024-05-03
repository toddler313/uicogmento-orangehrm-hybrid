package actions.pageObjects.orangehrm;

import actions.commons.BasePage;
import actions.util.PropertiesConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.BasePageUI;
import pageUIs.orangehrm.SaveJobTitlePageUI;

public class SaveJobTitlePageObject extends BasePage {
    private WebDriver driver;
    public SaveJobTitlePageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step
    public boolean checkAddJobTitleHeader() {
        waitForWebElementVisible(SaveJobTitlePageUI.ADD_JOB_TITLE_HEADER);
        return isWebElementDisplayed(SaveJobTitlePageUI.ADD_JOB_TITLE_HEADER);
    }

    @Step
    public void enterJobTitle(String jobTitle) {
        waitForWebElementVisible(SaveJobTitlePageUI.JOB_TITLE_TEXTBOX);
        sendKeysToWebElement(jobTitle, SaveJobTitlePageUI.JOB_TITLE_TEXTBOX);
    }

    @Step
    public void enterJobDescription(String jobDescription) {
        waitForWebElementVisible(SaveJobTitlePageUI.DYNAMIC_TEXTAREA, "Job Description");
        sendKeysToWebElement(jobDescription, SaveJobTitlePageUI.DYNAMIC_TEXTAREA, "Job Description");
    }

    @Step
    public void uploadJobSpecification(String fileName, String filePath, String runTestsIn) {
        waitForWebElementVisible(SaveJobTitlePageUI.JOB_SPECIFICATION_OUTPUT);
        switch (runTestsIn) {
            case "local" -> {
                uploadFile(SaveJobTitlePageUI.JOB_SPECIFICATION_INPUT, filePath);
            }
            case "docker" -> {
                uploadFile(SaveJobTitlePageUI.JOB_SPECIFICATION_INPUT,
                        PropertiesConfig.configReader().getDockerFilePath() + fileName);
            }
        }

    }

    public boolean isJobSpecificationUploadedSuccessfully(String fileName) {
        return (getTextFromWebElement(SaveJobTitlePageUI.JOB_SPECIFICATION_OUTPUT)
                .equals(fileName)) ? true : false;
    }

    @Step
    public void enterNotes(String jobNotes) {
        waitForWebElementVisible(SaveJobTitlePageUI.DYNAMIC_TEXTAREA, "Note");
        sendKeysToWebElement(jobNotes, SaveJobTitlePageUI.DYNAMIC_TEXTAREA, "Note");
    }

    @Step
    public void clickOnSaveButton() {
        waitForWebElementClickable(BasePageUI.GREEN_SAVE_BUTTONS);
        clickOnWebElement(BasePageUI.GREEN_SAVE_BUTTONS);
    }

}
