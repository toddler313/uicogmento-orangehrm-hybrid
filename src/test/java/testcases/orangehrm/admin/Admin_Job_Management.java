package testcases.orangehrm.admin;

import actions.commons.BaseTest;
import actions.commons.GlobalConstants;
import actions.commons.PageObjectManager;
import actions.pageObjects.orangehrm.*;
import actions.util.FakerDataHelper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;

public class Admin_Job_Management extends BaseTest {
    private WebDriver driver;
    private FakerDataHelper dataHelper;
    private String jobTitle, jobDescription, jobNotes, img1,filePath1;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private ViewSystemUsersPageObject adminUserMgmtPage;
    private ViewJobTitleListPageObject adminListJobTitlesPage;
    private SaveJobTitlePageObject adminSaveJobTitlesPage;

    @BeforeClass
    @Parameters({"environmentType", "runTestsIn", "browserName", "ipAddress", "portNumber", "platformName"})
    public void setup(String environmentType, String runTestsIn, String browserName,
                      @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Windows") String platformName) {
        driver = getBrowserDriver(environmentType, runTestsIn, browserName, ipAddress, portNumber, platformName);
        dataHelper = new FakerDataHelper();
        img1 = "img" + new Random().nextInt(9) + ".png";
        filePath1 = GlobalConstants.get().getUploadFolderPath() + img1;
        BaseTest.runTestsIn = runTestsIn;

        log.info("Pre-conditions. Open Login page then apply Cookies to load Dashboard page");
        loginPage = PageObjectManager.getLoginPage(driver);
//        loginPage.applyCookies(Admin_Login.loggedCookies);
//        loginPage.refreshPage();
//        dashboardPage = PageObjectManager.getDashboardPage(driver);

        log.info("Pre-conditions: Login to system");
        loginPage.checkPageLoaded();

        log.info("Enter Admin Username");
        loginPage.enterUsername();

        log.info("Enter Admin Password");
        loginPage.enterPassword();

        log.info("Click on Login button");
        loginPage.clickOnLoginButton();

        log.info("Initiate Dashboard page");
        dashboardPage = PageObjectManager.getDashboardPage(driver);

        log.info("Check Dashboard page loaded successfully");
        dashboardPage.checkPageLoaded(); dashboardPage.waitForAllSpinnersLoaded();

        log.info("Verify Dashboard menu is being selected");
        Assert.assertTrue(dashboardPage.currentSelectedItemInMainMenuIs("Dashboard"));

    }

    @Test
    public void TC_01_Admin_Create_Job_Title() {
        jobTitle = dataHelper.getJobTitle();
        jobDescription = dataHelper.getJobDescription();
        jobNotes = dataHelper.getJobNotes();

        log.info("TC_01_Admin_Create_Job_Title 1- Click on Admin main menu on the left");
        adminUserMgmtPage = (ViewSystemUsersPageObject) dashboardPage.clickOnItemInMainMenu("Admin");

        log.info("TC_01_Admin_Create_Job_Title 2- Wait for page and spinners loaded");
        adminUserMgmtPage.checkPageLoaded(); adminUserMgmtPage.waitForAllSpinnersLoaded();

        log.info("TC_01_Admin_Create_Job_Title 3- Verify current selected menu is Admin");
        Assert.assertTrue(adminUserMgmtPage.currentSelectedItemInMainMenuIs("Admin"));

        log.info("TC_01_Admin_Create_Job_Title 4- Select Job Titles from dropdown Job");
        adminUserMgmtPage.selectItemFromDropdown("Job", "Job Titles");

        log.info("TC_01_Admin_Create_Job_Title 5- Initiate Admin List Job Titles page");
        adminListJobTitlesPage = PageObjectManager.getViewJobTitlesListPage(driver);

        log.info("TC_01_Admin_Create_Job_Title 6- Wait for page and spinners loaded");
        adminListJobTitlesPage.checkPageLoaded(); adminListJobTitlesPage.waitForAllSpinnersLoaded();

        log.info("TC_01_Admin_Create_Job_Title 7- Check Empty List");
        adminListJobTitlesPage.checkIfTheListContainsRecordsThenVerifyToastPopup();

        log.info("TC_01_Admin_Create_Job_Title 8- Click on Add button");
        adminSaveJobTitlesPage = adminListJobTitlesPage.clickOnAddButton();

        log.info("TC_01_Admin_Create_Job_Title 9- Initiate Admin Save Job Titles page");
        adminSaveJobTitlesPage.checkPageLoaded();

        log.info("TC_01_Admin_Create_Job_Title 10- Verify Add Job Title header");
        Assert.assertTrue(adminSaveJobTitlesPage.checkAddJobTitleHeader());

        log.info("TC_01_Admin_Create_Job_Title 1- Enter Job Title");
        adminSaveJobTitlesPage.enterJobTitle(jobTitle);

        log.info("TC_01_Admin_Create_Job_Title 12- Enter Job Description");
        adminSaveJobTitlesPage.enterJobDescription(jobDescription);

        log.info("TC_01_Admin_Create_Job_Title 13- Upload Job Specification and check");
        adminSaveJobTitlesPage.uploadJobSpecification(img1, filePath1, runTestsIn);

        log.info("TC_01_Admin_Create_Job_Title 14- Verify Job Specification uploaded successfully");
        Assert.assertTrue(adminSaveJobTitlesPage.isJobSpecificationUploadedSuccessfully(img1));

        log.info("TC_01_Admin_Create_Job_Title 15- Enter Job Notes");
        adminSaveJobTitlesPage.enterNotes(jobNotes);

        log.info("TC_01_Admin_Create_Job_Title 16- Click on Save button");
        adminSaveJobTitlesPage.clickOnSaveButton();

        log.info("TC_01_Admin_Create_Job_Title 17- Initiate Admin List Job Titles page");
        adminListJobTitlesPage = PageObjectManager.getViewJobTitlesListPage(driver);

        log.info("TC_01_Admin_Create_Job_Title 18- Check page and spinners loaded");
        adminListJobTitlesPage.checkPageLoaded(); adminListJobTitlesPage.waitForAllSpinnersLoaded();

        log.info("TC_01_Admin_Create_Job_Title 19- Verify \"Record Found\" header");
        Assert.assertTrue(adminListJobTitlesPage.isNRecordsFoundHeaderDisplayed());

        log.info("TC_01_Admin_Create_Job_Title 20- Verify created Job Title in list: " + jobTitle);
        Assert.assertTrue(adminListJobTitlesPage.getRecentCreatedJobTitle(jobTitle).isDisplayed());

        log.info("TC_01_Admin_Create_Job_Title 21- Verify Description of the job: " + jobDescription);
        Assert.assertEquals(adminListJobTitlesPage.getRecentCreatedJobDescription(jobTitle), jobDescription);

        log.info("TC_01_Admin_Create_Job_Title 21- Verify new job recorded in backend");
        Assert.assertTrue(adminListJobTitlesPage.isNewJobRecordedInBackend(jobTitle, jobDescription, jobNotes));
    }


    @Test
    public void TC_02_Admin_Edit_Job() {

    }

    @Test
    public void TC_03_Admin_Delete_Job() {

    }

    @Test
    public void TC_04_Admin_Create_Pay_Grade() {

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserDriver();
    }
}
