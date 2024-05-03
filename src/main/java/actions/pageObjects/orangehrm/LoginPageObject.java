package actions.pageObjects.orangehrm;

import actions.commons.BasePage;
import actions.environmentSettings.environmentFactory.Local;
import actions.util.PropertiesConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.orangehrm.LoginPageUI;

public class LoginPageObject extends BasePage {
    private WebDriver driver;
    public LoginPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step
    public void enterUsername() {
        waitForWebElementVisible(LoginPageUI.USERNAME_TEXTBOX);
        sendKeysToWebElement(PropertiesConfig.configReader().getOrangeAdminUsername(),
                LoginPageUI.USERNAME_TEXTBOX);
    }

    @Step
    public void enterPassword() {
        waitForWebElementVisible(LoginPageUI.PASSWORD_TEXTBOX);
        sendKeysToWebElement(PropertiesConfig.configReader().getOrangeAdminPassword(),
                LoginPageUI.PASSWORD_TEXTBOX);
    }

    @Step
    public void clickOnLoginButton() {
        waitForWebElementClickable(LoginPageUI.LOGIN_BUTTON);
        clickOnWebElement(LoginPageUI.LOGIN_BUTTON);
    }
}
