package actions.environmentSettings.browserDriverConfig;

import actions.environmentSettings.interfaces.BrowserDriverFactory;
import actions.commons.GlobalConstants;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import static org.apache.commons.lang3.SystemUtils.IS_OS_MAC;

public class SafariDriverManager implements BrowserDriverFactory {

    @Override
    public WebDriver initDriver() {
        if (!IS_OS_MAC) {
            throw new RuntimeException("Safari is not supported on " + GlobalConstants.get().getOsName());
        }
        return new SafariDriver(initDriverOptions());
    }

    public SafariOptions initDriverOptions() {
        SafariOptions sOptions = new SafariOptions();
        sOptions.setAcceptInsecureCerts(true);
        sOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        sOptions.setUseTechnologyPreview(true);
        sOptions.setCapability("safari.cleanSession", true);
        return  sOptions;
    }
}
