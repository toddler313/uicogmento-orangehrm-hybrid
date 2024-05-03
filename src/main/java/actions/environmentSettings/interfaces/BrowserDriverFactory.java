package actions.environmentSettings.interfaces;

import org.openqa.selenium.WebDriver;

/**
 *  Interface for all browser driver managers
 */
public interface BrowserDriverFactory {
    public WebDriver initDriver();
}
