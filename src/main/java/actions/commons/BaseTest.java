package actions.commons;

import actions.enums.Environment;
import actions.environmentSettings.environmentFactory.Docker;
import actions.environmentSettings.environmentFactory.Local;
import actions.util.PropertiesConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public final Logger log;

    public static String runTestsIn = null;
    public BaseTest() {
        log = LogManager.getLogger(getClass());
    }

    @BeforeSuite
    public void beforeSuite() {
        cleanFolder("logs");
        cleanFolder("allure-results");
    }

    public WebDriver getWebDriver() {
        return driver.get();
    }

    public WebDriver getBrowserDriver(String environmentType, String runTestsIn, String browserName,
                                      String ipAddress, String portNumber, String platformName) {
        switch (runTestsIn) {
            case "local" -> driver.set(new Local(browserName).initDriver());
            case "browserstack" -> driver.set(null);
            case "docker" -> driver.set(new Docker(browserName, ipAddress, portNumber, platformName).initDriver());
            default -> {
                throw new RuntimeException("Unsupported environment type to run Tests. Available options are 'local','browserstack','docker'");
            }
        }
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get().get(getEnvironmentUrl(environmentType));
        driver.get().manage().window().setSize(new Dimension(1920, 1080));
//        driver.get().manage().window().setPosition(new Point(600,60));
        return driver.get();
    }

    public String getEnvironmentUrl(String environmentType) {
        String url = null;
        Environment type = Environment.valueOf(environmentType.toUpperCase());
        switch (type) {
            case COGMENTO -> url = PropertiesConfig.configReader().getCogmentoUrl();
            case ORANGE -> url = PropertiesConfig.configReader().getOrangeUrl();
            default -> {
                throw new RuntimeException("Environment Type not supported. Available options are 'dev','test','staging','production'");
            }
        }
        return url;
    }

    protected void closeBrowserDriver() {
        String cmd = null;
        try {
            String osName = GlobalConstants.get().getOsName();
            String driverInstanceName = driver.get().toString().toLowerCase();
            String browserDriverName = null;

            log.info("Platform Name: " + osName);
            log.info("Driver instance: " + driverInstanceName);

            if (driverInstanceName.contains("chrome")) {
                browserDriverName = "chromedriver";
            } else if (driverInstanceName.contains("firefox")) {
                browserDriverName = "geckodriver";
            } else if (driverInstanceName.contains("edge")) {
                browserDriverName = "msedgedriver";
            } else if (driverInstanceName.contains("opera")) {
                browserDriverName = "operadriver";
            } else {
                browserDriverName = "safaridriver";
            }

            if (osName.contains("Windows")) {
                cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
            } else {
                cmd = "pkill " + browserDriverName;
            }

            if (driver != null) {
                driver.get().manage().deleteAllCookies();
                driver.get().quit();
                driver.remove();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException e) {
                log.info(e.getMessage());
            } catch (InterruptedException e) {
                log.info(e.getMessage());
            }
        }
    }

    private void cleanFolder(String folderName) {
        try {
            File targetFolder = new File(GlobalConstants.get().getClassPath() + File.separator + folderName);
            if (targetFolder.exists()) {
                File[] files = targetFolder.listFiles();
                for (int i=0; i<files.length; i++) {
                    if (files[i].isFile() && !files[i].getName().equals("environment.properties")) {
                        new File(files[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
