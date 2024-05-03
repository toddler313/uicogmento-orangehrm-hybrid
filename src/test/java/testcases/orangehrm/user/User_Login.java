package testcases.orangehrm.user;

import actions.commons.BaseTest;
import actions.commons.PageObjectManager;
import actions.pageObjects.orangehrm.DashboardPageObject;
import actions.pageObjects.orangehrm.LoginPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class User_Login extends BaseTest {
    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;

    @BeforeClass
    @Parameters({"environmentType", "runTestsIn", "browserName", "ipAddress", "portNumber", "platformName"})
    public void setup(String environmentType, String runTestsIn, String browserName,
                      String ipAddress, String portNumber, String platformName) {
        driver = getBrowserDriver(environmentType, runTestsIn, browserName, ipAddress, portNumber, platformName);
        log.info("BeforeClass - Initiate Login page");
        loginPage = PageObjectManager.getLoginPage(driver);

    }

    @AfterClass
    public void tearDown() {

    }

    @Test
    public void TC_01_User_Login_to_System() {
        log.info("TC 01: User Login - Step1: Check Login page loaded");
        loginPage.checkPageLoaded();

        log.info("TC 01: User Login - Step2: Enter User login Username");
        loginPage.enterUsername();

        log.info("TC 01: User Login - Step3: Enter User login Password");
        loginPage.enterPassword();

        log.info("TC 01: User Login - Step4: Click on Login button");
        loginPage.clickOnLoginButton();

        log.info("TC 01: User Login - Step5: Initiate Dashboard page");
        dashboardPage = PageObjectManager.getDashboardPage(driver);

        log.info("TC 01: User Login - Step6: Check Dashboard page loaded successfully");
        dashboardPage.checkPageLoaded(); dashboardPage.waitForAllSpinnersLoaded();

        log.info("TC 01: User Login - Step7: Verify Dashboard menu is being selected");
        Assert.assertTrue(dashboardPage.currentSelectedItemInMainMenuIs("Dashboard"));
    }
}
