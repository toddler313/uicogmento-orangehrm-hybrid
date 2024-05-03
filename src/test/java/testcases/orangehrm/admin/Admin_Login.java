package testcases.orangehrm.admin;

import actions.commons.BaseTest;
import actions.commons.PageObjectManager;
import actions.pageObjects.orangehrm.LoginPageObject;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import actions.pageObjects.orangehrm.DashboardPageObject;

import java.util.Set;

public class Admin_Login extends BaseTest {
    private WebDriver driver;

    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;

    public static Set<Cookie> loggedCookies;

    @BeforeClass
    @Parameters({"environmentType", "runTestsIn", "browserName", "ipAddress", "portNumber", "platformName"})
    public void setup(String environmentType, String runTestsIn, String browserName,
                      String ipAddress, String portNumber, String platformName) {
        driver = getBrowserDriver(environmentType, runTestsIn, browserName, ipAddress, portNumber, platformName);

        log.info("Pre-conditions. Open Orange Login page");
        loginPage = PageObjectManager.getLoginPage(driver);
    }

    @Test
    public void TC_01_Admin_Login() {
        log.info("TC_01_Admin_Login - TC starts. Check Login page loaded successfully");
        loginPage.checkPageLoaded();

        log.info("TC_01_Admin_Login - Enter Admin Username");
        loginPage.enterUsername();

        log.info("TC_01_Admin_Login - Enter Admin Password");
        loginPage.enterPassword();

        log.info("TC_01_Admin_Login - Click on Login button");
        loginPage.clickOnLoginButton();

        log.info("TC_01_Admin_Login - Initiate Dashboard page");
        dashboardPage = PageObjectManager.getDashboardPage(driver);

        log.info("TC_01_Admin_Login - Check Dashboard page loaded successfully");
        dashboardPage.checkPageLoaded(); dashboardPage.waitForAllSpinnersLoaded();

        log.info("TC_01_Admin_Login - Verify Dashboard menu is being selected");
        Assert.assertTrue(dashboardPage.currentSelectedItemInMainMenuIs("Dashboard"));

        loggedCookies = dashboardPage.storeCookies();
        for (Cookie c : loggedCookies) {
            System.out.println(c);
        }
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

}
