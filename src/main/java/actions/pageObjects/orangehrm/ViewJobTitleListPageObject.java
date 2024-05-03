package actions.pageObjects.orangehrm;

import actions.commons.BasePage;
import actions.commons.PageObjectManager;
import actions.util.MySQLConnection;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.BasePageUI;
import pageUIs.orangehrm.ViewJobTitleListPageUI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ViewJobTitleListPageObject extends BasePage {
    private WebDriver driver;
    public ViewJobTitleListPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void checkIfTheListContainsRecordsThenVerifyToastPopup() {
//        if (getWebElement(BasePageUI.NO_RECORD_FOUND_IN_TABLE).isDisplayed()) {
//
//        }
    }

    @Step
    public SaveJobTitlePageObject clickOnAddButton() {
        waitForWebElementClickable(BasePageUI.GREEN_SAVE_BUTTONS);
        clickOnWebElement(BasePageUI.GREEN_SAVE_BUTTONS);
        return PageObjectManager.getSaveJobTitlesPage(driver);
    }

    @Step("Check header \"1 Record Found\" or \"N Records Found\"")
    public boolean isNRecordsFoundHeaderDisplayed() {
        int numberOfRows = getWebElements(ViewJobTitleListPageUI.TOTAL_RECORDS_IN_LIST).size();
        String headerContent = getWebElement(ViewJobTitleListPageUI.LIST_HEADER).getText();
        if (numberOfRows == 1) {
            return ("("+ numberOfRows +") Record Found").equals(headerContent) ? true : false;
        }
        return ("("+ numberOfRows +") Records Found").equals(headerContent) ? true : false;
    }

    @Step("Verify created Job title equals to: {0}")
    public WebElement getRecentCreatedJobTitle(String jobTitle) {
        return getWebElement(ViewJobTitleListPageUI.DYNAMIC_JOB_TITLE, jobTitle);
    }

    @Step("Verify created Job description equals to: {0}")
    public String getRecentCreatedJobDescription(String jobTitle) {
        List<WebElement> elements = getWebElements(ViewJobTitleListPageUI.DYNAMIC_JOB_DESCRIPTION_BY_TITLE, jobTitle);
        if (elements.size() > 1) {
            actionsMoveToElement(elements.get(1));
            jsHighlightElement(elements.get(1));
            elements.get(1).click();
        }
        return elements.get(0).getText();
    }

    public boolean isNewJobRecordedInBackend(String expectedJobTitle, String expectedJobDescription, String expectedJobNotes) {
        Connection conn = MySQLConnection.getMySQLConnection();
        String jobTitle = null, jobDescription = null, jobNotes = null;
        Statement statement;
        ResultSet rs;
        String sql = "select * from ohrm_job_title where job_title = '" + expectedJobTitle + "'";
        int rowCount = 0;

        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            while(rs.next()) {
                jobTitle = rs.getString("job_title");
                jobDescription = rs.getString("job_description");
                jobNotes = rs.getString("note");
                rowCount++;
                Integer[] total = new Integer[5];
            }
            if (conn != null) conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ((rowCount == 1) && jobTitle.equals(expectedJobTitle) && jobDescription.equals(expectedJobDescription) &&
                jobNotes.equals(expectedJobNotes));
    }
}
