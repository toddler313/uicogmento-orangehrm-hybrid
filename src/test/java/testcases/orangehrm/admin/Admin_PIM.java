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

public class Admin_PIM extends BaseTest {
    private WebDriver driver;
    private FakerDataHelper dataHelper;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private ViewEmployeeListPageObject viewEmpListPage;
    private AddEmployeePageObject addEmpPage;
    private ViewPersonalDetailPageObject personalPage;
    private String firstName,lastName,middleName,employeeId, profileImg, profileImgPath;

    public static String userUsername, userPassword;

    @BeforeClass
    @Parameters({"environmentType", "runTestsIn", "browserName", "ipAddress", "portNumber", "platformName"})
    public void setup(String environmentType, String runTestsIn, String browserName,
                      @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Windows") String platformName) {
        driver = getBrowserDriver(environmentType, runTestsIn, browserName, ipAddress, portNumber, platformName);
        dataHelper = new FakerDataHelper();
        profileImg = "portrait" + new Random().nextInt(7) + ".png";
        profileImgPath = GlobalConstants.get().getUploadFolderPath() + profileImg;
        BaseTest.runTestsIn = runTestsIn;

        loginPage = PageObjectManager.getLoginPage(driver);
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
    }

    @AfterClass
    public void tearDown() {
        closeBrowserDriver();
    }

//    @Test
    public void TC_01_Check_Field_Validations_Add_Employee_Page() {
        log.info("TC_01_CheckFieldValidation - Step1: Open Employee List (PIM) page");
        viewEmpListPage = (ViewEmployeeListPageObject) dashboardPage.clickOnItemInMainMenu("PIM");

        log.info("TC_01_CheckFieldValidation - Step2: Wait for page and spinners loaded");
        viewEmpListPage.checkPageLoaded(); viewEmpListPage.waitForAllSpinnersLoaded();

        log.info("TC_01_CheckFieldValidation - Step3: Click on Add new employee button");
        addEmpPage = viewEmpListPage.clickOnAddNewEmployeeButton();

        log.info("TC_01_CheckFieldValidation - Step4: Wait for page and spinner loaded");
        addEmpPage.checkPageLoaded(); addEmpPage.waitForAllSpinnersLoaded();

        log.info("TC_01_CheckFieldValidation - Step5: Click on Save button");
        addEmpPage.clickOnSaveButton();

        log.info("TC_01_CheckFieldValidation - Step6: Verify FirstName-empty validation message");
//        Assert.assertEquals(addEmpPage.getFirstNameValidationMessage(), "Required");
//
//        log.info("TC_01_CheckFieldValidation - Step6: Verify LastName-empty validation message");
//        Assert.assertEquals(addEmpPage.getLastNameValidationMessage(), "Required");
//
//        log.info("TC_01_CheckFieldValidation - Step13: Enter First Name with more than 30 characters then verify validation message");
//        addEmpPage.enterFirstName(); Assert.assertEquals(addEmpPage.getFirstNameValidationMessage(), "Should not exceed 30 characters");
//
//        log.info("TC_01_CheckFieldValidation - Step13: Enter Last Name with more than 30 characters then verify validation message");
//        addEmpPage.enterLastName(); Assert.assertEquals(addEmpPage.getLastNameValidationMessage(), "Should not exceed 30 characters");
//
//        log.info("TC_01_CheckFieldValidation - Step13: Enter Middle Name with more than 30 characters then verify validation message");
//        addEmpPage.enterMiddleName(); Assert.assertEquals(addEmpPage.getMiddleNameValidationMessage(), "Should not exceed 30 characters");
//
//        log.info("TC_01_CheckFieldValidation - Step13: Enter Employee Id with more than 10 characters then verify validation message");
//        addEmpPage.enterEmployeeId(); Assert.assertEquals(addEmpPage.getEmployeeIdValidationMessage(), "Should not exceed 10 characters");
//
//        log.info("TC_01_CheckFieldValidation - Step13: Enter valid First Name and verify validation message goes away");
//        addEmpPage.enterFirstName(); Assert.assertTrue(addEmpPage.isFirstNameValidationMessageGoesAway());
//
//        log.info("TC_01_CheckFieldValidation - Step13: Enter valid Last Name and verify validation message goes away");
//        addEmpPage.enterLastName(); Assert.assertTrue(addEmpPage.isLastNameValidationMessageGoesAway());
//
//        log.info("TC_01_CheckFieldValidation - Step13: Enter valid Middle Name and verify validation message goes away");
//        addEmpPage.enterMiddleName(); Assert.assertTrue(addEmpPage.isMiddleNameValidationMessageGoesAway());
//
//        log.info("TC_01_CheckFieldValidation - Step13: Enter valid Middle Name and verify validation message goes away");
//        addEmpPage.enterMiddleName(); Assert.assertTrue(addEmpPage.isMiddleNameValidationMessageGoesAway());
//
//        log.info("TC_01_CheckFieldValidation - Step13: Enter valid Employee Id and verify validation message goes away");
//        addEmpPage.enterMiddleName(); Assert.assertTrue(addEmpPage.isEmployeeIdValidationMessageGoesAway());
//
//        log.info("TC_01_CheckFieldValidation - Step17: Click on Create Login Details toggle");
//        addEmpPage.clickOnCreateLoginDetailsToggle();
//
//        log.info("TC_01_CheckFieldValidation - Step5: Click on Save button");
//        addEmpPage.clickOnSaveButton();
//
//        log.info("TC_01_CheckFieldValidation - Step5: Verify Username-empty validation message");
//        Assert.assertEquals(addEmpPage.getUsernameValidationMessage(), "Required");
//
//        log.info("TC_01_CheckFieldValidation - Step5: Verify Password-empty validation message");
//        Assert.assertEquals(addEmpPage.getPasswordValidationMessage(), "Required");
//
//        log.info("TC_01_CheckFieldValidation - Step5: Verify ConfirmPassword-empty validation message");
//        Assert.assertEquals(addEmpPage.getConfirmPasswordValidationMessage(), "Required");
//
//        log.info("TC_01_CheckFieldValidation - Step17: Enter Login Username with less than 5 characters then verify validation message");
//        addEmpPage.enterLoginUsername(); Assert.assertEquals(addEmpPage.getUsernameValidationMessage(), "Should be at least 5 characters");
//
//        log.info("TC_01_CheckFieldValidation - Step17: Enter Login Username with more than 40 characters then verify validation message");
//        addEmpPage.enterLoginUsername(); Assert.assertEquals(addEmpPage.getUsernameValidationMessage(), "Should not exceed 40 characters");
//
//        log.info("TC_01_CheckFieldValidation - Step13: Enter valid Login Username then verify validation message goes away");
//        addEmpPage.enterMiddleName(); Assert.assertTrue(addEmpPage.isUsernameValidationMessageGoesAway());
//
//        /**
//         *  Play with Password/ Confirm Password validation messages.
//         */
//        log.info("TC_01_CheckFieldValidation - Step18: Enter Login Password with less than 4 characters then verify validation message and \"Very Weak\" label");
//        addEmpPage.enterLoginPassword(); Assert.assertEquals(addEmpPage.getPasswordValidationMessage(), "Should have at least 8 characters");
//        Assert.assertEquals(addEmpPage.getPasswordStrengGuidance(), "Very Weak");

    }

    @Test
    public void TC_02_Add_New_Employee_Successfully_Without_Login_Details() {
        firstName = dataHelper.getFirstName();
        lastName = dataHelper.getLastName();
        middleName = dataHelper.getMiddleName();
        employeeId = dataHelper.randomString(1,6);
        userUsername = dataHelper.getEmailAddress();
        userPassword = dataHelper.getPassword();

        log.info("TC_02_addNewEmployeeWithoutLoginDetails - Step1: Open Employee List (PIM)");
        viewEmpListPage = (ViewEmployeeListPageObject) dashboardPage.clickOnItemInMainMenu("PIM");

        log.info("TC_02_addNewEmployeeWithoutLoginDetails - Step2: Check page and spinners loaded");
        viewEmpListPage.checkPageLoaded(); viewEmpListPage.waitForAllSpinnersLoaded();

        log.info("TC_02_addNewEmployeeWithoutLoginDetails - Step3: Click on Add new employee button");
        addEmpPage = viewEmpListPage.clickOnAddNewEmployeeButton();

        log.info("TC_02_addNewEmployeeWithoutLoginDetails - Step4: check page and spinners loaded");
        addEmpPage.checkPageLoaded(); addEmpPage.waitForAllSpinnersLoaded();

        log.info("TC_02_addNewEmployeeWithoutLoginDetails - Step5: Enter valid First/Middle/Last Name");
        addEmpPage.enterFirstName(firstName); addEmpPage.enterMiddleName(middleName); addEmpPage.enterLastName(lastName);

        log.info("TC_02_addNewEmployeeWithoutLoginDetails - Step8: Enter valid Employee ID");
        addEmpPage.enterEmployeeId(employeeId);

        log.info("TC_02_addNewEmployeeWithoutLoginDetails - Step9: Upload Employee portrait");
        addEmpPage.uploadEmployeePortrait(profileImg, profileImgPath, runTestsIn);

        log.info("TC_02_addNewEmployeeWithoutLoginDetails - Step10: Click on Save button");
        addEmpPage.clickOnSaveButton();

        log.info("TC_02_addNewEmployeeWithoutLoginDetails - Step12: Wait for page and spinners loaded");
        addEmpPage.waitForAllSpinnersLoaded();

        log.info("TC_02_addNewEmployeeWithoutLoginDetails - Step11: Navigate to recent created Employee personal detail page");
        personalPage = PageObjectManager.getViewPersonalDetailsPage(driver);

        log.info("TC_02_addNewEmployeeWithoutLoginDetails - Step12: Wait for page and spinners loaded");
        personalPage.checkPageLoaded(); personalPage.waitForAllSpinnersLoaded();

        log.info("TC_02_addNewEmployeeWithoutLoginDetails - Step13: Verify header is the name of the Employee: " + firstName + " " + lastName);
        Assert.assertEquals(personalPage.getEmployeeNameOnHeader(), firstName + " " + lastName);
    }


}
