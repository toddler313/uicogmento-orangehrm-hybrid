package actions.commons;

import actions.pageObjects.orangehrm.*;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageObjectManager {
    @Step
    public static LoginPageObject getLoginPage(WebDriver driver) {
        return new LoginPageObject(driver);
    }
    @Step
    public static DashboardPageObject getDashboardPage(WebDriver driver) {
        return new DashboardPageObject(driver);
    }
    @Step
    public static ViewSystemUsersPageObject getViewSystemUsersPage(WebDriver driver) {
        return new ViewSystemUsersPageObject(driver);
    }
    @Step
    public static ViewJobTitleListPageObject getViewJobTitlesListPage(WebDriver driver) {
        return new ViewJobTitleListPageObject(driver);
    }
    @Step
    public static SaveJobTitlePageObject getSaveJobTitlesPage(WebDriver driver) {
        return new SaveJobTitlePageObject(driver);
    }
    @Step
    public static ViewEmployeeListPageObject getViewEmployeeListPage(WebDriver driver) {
        return new ViewEmployeeListPageObject(driver);
    }
    @Step
    public static AddEmployeePageObject getAddEmployeePage(WebDriver driver) {
        return new AddEmployeePageObject(driver);
    }
    @Step("Get Page {1}")
    public static BasePage getPageObject(WebDriver driver, String pageName) {
        switch (pageName) {
            case "Admin" -> {
                return getViewSystemUsersPage(driver);
            }
            case "Dashboard" -> {
                return getDashboardPage(driver);
            }
            case "PIM" -> {
                return getViewEmployeeListPage(driver);
            }
            default -> {
                return null;
            }
        }


    }

    @Step
    public static ViewPersonalDetailPageObject getViewPersonalDetailsPage(WebDriver driver) {
        return new ViewPersonalDetailPageObject(driver);
    }
    @Step
    public static MyInfoPageObject getMyInfoPage(WebDriver driver) {
        return new MyInfoPageObject(driver);
    }
}
