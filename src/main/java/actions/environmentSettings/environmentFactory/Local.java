package actions.environmentSettings.environmentFactory;

import actions.enums.Browser;
import actions.environmentSettings.browserDriverConfig.ChromeDriverManager;
import actions.environmentSettings.browserDriverConfig.EdgeDriverManager;
import actions.environmentSettings.browserDriverConfig.FirefoxDriverManager;
import actions.environmentSettings.browserDriverConfig.SafariDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Local {
    private WebDriver driver;
    private String browserName;

    public Local(String browserName) {
        this.browserName = browserName;
    }

    public WebDriver initDriver() {
        Browser browser = Browser.valueOf(browserName.toUpperCase());
            switch (browser) {
                case CHROME -> driver = new ChromeDriverManager().initDriver();
                case FIREFOX -> driver = new FirefoxDriverManager().initDriver();
                case MSEDGE -> driver = new EdgeDriverManager().initDriver();
                case SAFARI -> driver = new SafariDriverManager().initDriver();
                default -> throw new RuntimeException("Unsupported Browser Type.");
            }
        return driver;
    }

}
