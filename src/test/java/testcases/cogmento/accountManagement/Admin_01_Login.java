package testcases.cogmento.accountManagement;

import actions.commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class Admin_01_Login extends BaseTest {
    private WebDriver driver;

    @BeforeClass
    @Parameters({"environmentType", "runTestsIn", "browserName", "ipAddress", "portNumber", "platformName"})
    public void setup(String environmentType, String runTestsIn, String browserName,
                      String ipAddress, String portNumber, String platformName) {
        driver = getBrowserDriver(environmentType, runTestsIn, browserName, ipAddress, portNumber, platformName);


    }
}
