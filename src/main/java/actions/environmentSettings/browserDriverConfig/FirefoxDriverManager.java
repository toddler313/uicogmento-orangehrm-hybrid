package actions.environmentSettings.browserDriverConfig;

import actions.environmentSettings.interfaces.BrowserDriverFactory;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverManager implements BrowserDriverFactory {

    @Override
    public WebDriver initDriver() {
        return new FirefoxDriver(initDriverOptions());
    }

    public FirefoxOptions initDriverOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addPreference("dom.webnotifications.enabled", false);
        options.addPreference("geo.enabled", false);
        options.addPreference("geo.provider.use_corelocation", false);

        return options;
    }

}
