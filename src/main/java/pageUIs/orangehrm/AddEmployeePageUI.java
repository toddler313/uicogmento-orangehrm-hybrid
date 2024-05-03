package pageUIs.orangehrm;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;

public class AddEmployeePageUI {
    public static final String FIRSTNAME_TEXT_BOX = "css=input[name='firstName']";
    public static final String LASTNAME_TEXT_BOX = "css=input[name='lastName']";
    public static final String MIDDLENAME_TEXT_BOX = "css=input[name='middleName']";
    public static final String EMPLOYEE_ID_TEXT_BOX = "xpath=//label[text()='Employee Id']/parent::div/following-sibling::div/input";
    public static final String UPLOAD_PORTRAIT_IMG = "css=div.employee-image-wrapper > img";
    public static final String UPLOAD_PORTRAIT_INPUT = "xpath=//div[@class='employee-image-wrapper']//img[@alt='profile picture']/ancestor::div[contains(@class,'oxd-file-div')]/preceding-sibling::input";
    public static final String FIRST_LAST_MIDDLE_NAME_VALIDATION_MESSAGE = "xpath=//input[@name='firstName']/parent::div/following-sibling::span[contains(@class,'oxd-input-field-error-message')]";
}
