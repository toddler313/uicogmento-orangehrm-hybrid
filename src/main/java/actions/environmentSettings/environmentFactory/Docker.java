package actions.environmentSettings.environmentFactory;

import actions.enums.Browser;
import actions.environmentSettings.browserDriverConfig.ChromeDriverManager;
import actions.environmentSettings.browserDriverConfig.EdgeDriverManager;
import actions.environmentSettings.browserDriverConfig.FirefoxDriverManager;
import actions.environmentSettings.browserDriverConfig.SafariDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class Docker {

    private WebDriver driver;
    private String browserName;
    private String ipAddress;
    private String portNumber;
    private String platformName;

    public Docker(String browserName, String ipAddress, String portNumber, String platformName) {
        this.browserName = browserName;
        this.ipAddress = ipAddress;
        this.portNumber = portNumber;
        this.platformName = platformName;
    }

    public WebDriver initDriver() {
        Browser browser = Browser.valueOf(browserName.toUpperCase());
        MutableCapabilities capability;

        switch (browser) {
            case FIREFOX -> capability = new FirefoxDriverManager().initDriverOptions();
            case CHROME -> capability = new ChromeDriverManager().initDriverOptions();
            case MSEDGE -> capability = new EdgeDriverManager().initDriverOptions();
            case SAFARI -> capability = new SafariDriverManager().initDriverOptions();
            default -> throw new RuntimeException("Unsupported Browser");
        }

        try {
            driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub", ipAddress, portNumber)), capability);
            ((RemoteWebDriver)driver).setFileDetector(new LocalFileDetector());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

}
