package actions.commons;


import actions.util.PropertiesConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUIs.BasePageUI;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    private WebDriver driver;
    private JavascriptExecutor jsExecutor;
    private Actions actions;
    private WebDriverWait explicitWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        jsExecutor = (JavascriptExecutor) driver;
        actions = new Actions(driver);
    }

    protected By getByLocator(String locator) {
        By by = null;
        if (locator.startsWith("id=") || locator.startsWith("ID=") || locator.startsWith("Id=")) {
            by = By.id(locator.substring(3));
        } else if (locator.startsWith("class=") || locator.startsWith("CLASS=") || locator.startsWith("Class=")) {
            by = By.className(locator.substring(6));
        } else if (locator.startsWith("name=") || locator.startsWith("NAME=") || locator.startsWith("Name=")) {
            by = By.name(locator.substring(5));
        } else if (locator.startsWith("css=") || locator.startsWith("CSS=") || locator.startsWith("Css=")) {
            by = By.cssSelector(locator.substring(4));
        } else if (locator.startsWith("xpath=") || locator.startsWith("XPATH=") ||
                locator.startsWith("XPath=") || locator.startsWith("xPath=") || locator.startsWith("Xpath=")) {
            by = By.xpath(locator.substring(6));
        } else {
            throw new RuntimeException("Locator Type is not supported.");
        }
        return by;
    }

    protected String getFullXPath(String dynamicXPath, String... params) {
        if (dynamicXPath.startsWith("xpath=") || dynamicXPath.startsWith("XPATH=") ||
                dynamicXPath.startsWith("XPath=") || dynamicXPath.startsWith("xPath=") ||
                dynamicXPath.startsWith("Xpath=")) {
            dynamicXPath = String.format(dynamicXPath, (Object[]) params);
        } else {
            throw new RuntimeException("Unsupported version of Dynamic Xpath Locator.");
        }
        return dynamicXPath;
    }

    protected void setGlobalImplicitWait(Duration duration) {
        driver.manage().timeouts().implicitlyWait(duration);
    }

    protected WebDriverWait setExplicitWait(Duration duration) {
        return new WebDriverWait(driver, duration);
    }

    protected void waitForWebElementVisible(String locator) {
        explicitWait = setExplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getShortTimeout()));
        setGlobalImplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getShortTimeout()));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
        setGlobalImplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getLongTimeout()));
    }

    protected void waitForWebElementsPresence(String locator) {
        explicitWait = setExplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getShortTimeout()));
        setGlobalImplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getShortTimeout()));
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locator)));
        setGlobalImplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getLongTimeout()));
    }

    protected void waitForWebElementsInvisible(String locator) {
        explicitWait = setExplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getShortTimeout()));
        setGlobalImplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getShortTimeout()));
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getWebElements(locator)));
        setGlobalImplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getLongTimeout()));
    }

    protected void waitForWebElementClickable(String locator) {
        WebDriverWait explicitWait = setExplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getShortTimeout()));
        setGlobalImplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getShortTimeout()));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
        setGlobalImplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getLongTimeout()));
    }


    protected boolean isWebElementDisplayed(String locator) {
        return getWebElement(locator).isDisplayed();
    }

    protected boolean isWebElementSelected(String locator) {
        return getWebElement(locator).isSelected();
    }

    protected boolean isWebElementEnabled(String locator) {
        return getWebElement(locator).isEnabled();
    }

    /**
     * Check whether the web element:
     * - Not exist in DOM -> return true
     * - Exist in DOM but  is not displayed -> return true
     * - Exist in DOm and is displayed -> return false
     *
     * @param locator
     */
    protected boolean isWebElementUndisplayed(String locator) {
        setGlobalImplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getShortTimeout()));
        List<WebElement> elements = getWebElements(locator);
        setGlobalImplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getLongTimeout()));

        if (elements.isEmpty()) {
            return true;
        } else if (!elements.isEmpty() && !elements.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    protected WebElement getWebElement(String locator) {
        return driver.findElement(getByLocator(locator));
    }

    protected List<WebElement> getWebElements(String locator) {
        return driver.findElements(getByLocator(locator));
    }

    protected void clickOnWebElement(String locator) {
        getWebElement(locator).click();
    }


    protected void sendKeysToWebElement(String keys, String locator) {
        WebElement element = getWebElement(locator);
        element.clear();
        element.sendKeys(keys);
    }

    protected String getTextFromWebElement(String locator) {
        return getWebElement(locator).getText();
    }

    protected void actionMoveToWebElementAndHover(String locator) {
        actions.moveToElement(getWebElement(locator)).perform();
    }

    protected void uploadFile(String locator, String filePath) {
        getWebElement(locator).sendKeys(filePath);
    }
    protected void robotFileUpload(String filePath) {
        try {
            Robot bot = new Robot();
            StringSelection copyToClipboard = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(copyToClipboard, null);

            bot.keyPress(KeyEvent.VK_CONTROL);
            bot.keyPress(KeyEvent.VK_V);
            bot.keyRelease(KeyEvent.VK_CONTROL);
            bot.keyRelease(KeyEvent.VK_V);
            bot.keyPress(KeyEvent.VK_ENTER);
            bot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    protected String getAttributeFromWebElement(String locator, String attributeName) {
        return getWebElement(locator).getAttribute(attributeName);
    }
    protected String getAttributeFromWebElement(WebElement element, String attributeName) {
        return element.getAttribute(attributeName);
    }

    protected String jsGetAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(locator));
    }
    public void jsSetAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getWebElement(locator));
    }
    public void jsSetAttributeInDOM(WebElement element, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", element);
    }
    protected void jsHighlightElement(String locator) {
        WebElement element = getWebElement(locator);
        String sourceStyle = getAttributeFromWebElement(locator, "style");
        jsSetAttributeInDOM(locator, "style", "border: 4px solid firebrick; border-style: dashed;");
        sleepInSeconds(1);
        jsSetAttributeInDOM(locator, "style", sourceStyle);
    }
    protected void jsHighlightElement(WebElement element) {
        String sourceStyle = getAttributeFromWebElement(element, "style");
        jsSetAttributeInDOM(element, "style", "border: 4px solid firebrick; border-style: dashed;");
        sleepInSeconds(1);
        jsSetAttributeInDOM(element, "style", sourceStyle);
    }
    protected boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getWebElement(locator));
        return status;
    }
    protected void actionsMoveToElement(String locator) {
        actions.moveToElement(getWebElement(locator));
    }
    protected void actionsMoveToElement(WebElement element) {
        actions.moveToElement(element);
    }


    public Set<Cookie> storeCookies() {
        return driver.manage().getCookies();
    }

    public void applyCookies(Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        sleepInSeconds(1);
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void sleepInSeconds(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Functions with Rest parameters
     */
    protected void waitForWebElementClickable(String dynamicXPath, String... params) {
        explicitWait = setExplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getShortTimeout()));
        setGlobalImplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getShortTimeout()));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getFullXPath(dynamicXPath, params))));
        setGlobalImplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getLongTimeout()));
    }

    protected WebElement getWebElement(String dynamicXPath, String... params) {
        return driver.findElement(getByLocator(getFullXPath(dynamicXPath, params)));
    }
    protected List<WebElement> getWebElements(String dynamicXPath, String... params) {
        return driver.findElements(getByLocator(getFullXPath(dynamicXPath, params)));
    }
    protected void clickOnWebElement(String dynamicXPath, String... params) {
        getWebElement(dynamicXPath, params).click();
    }

    protected void sendKeysToWebElement(String keys, String dynamicXPath, String... params) {
        getWebElement(dynamicXPath, params).sendKeys(keys);
    }

    protected void waitForWebElementVisible(String dynamicXpath, String params) {
        explicitWait = setExplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getShortTimeout()));
        setGlobalImplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getShortTimeout()));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getFullXPath(dynamicXpath, params))));
        setGlobalImplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getLongTimeout()));
    }

    protected WebElement waitForWebElementPresence(String dynamicXPath, String... params) {
        explicitWait = setExplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getShortTimeout()));
        setGlobalImplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getShortTimeout()));
        WebElement element = explicitWait.until(ExpectedConditions.presenceOfElementLocated((getByLocator(getFullXPath(dynamicXPath, params)))));
        setGlobalImplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getLongTimeout()));
        return element;
    }

    protected List<WebElement> waitForWebElementsPresence(String dynamicXPath, String... params) {
        explicitWait = setExplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getShortTimeout()));
        setGlobalImplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getShortTimeout()));
        List<WebElement> elements = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(getFullXPath(dynamicXPath, params))));
        setGlobalImplicitWait(Duration.ofSeconds(PropertiesConfig.configReader().getLongTimeout()));
        return elements;
    }
    protected boolean isWebElementDisplayed(String fullXPath, String... params) {
        return getWebElement(fullXPath, params).isDisplayed();
    }


    /**
     * Support OrangeHRM
     */
    @Step("Check page complete and ready")
    public boolean checkPageLoaded() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(PropertiesConfig.configReader().getShortTimeout()));
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jsLoad);
    }

    @Step
    public void waitForAllSpinnersLoaded() {
        waitForWebElementsInvisible(BasePageUI.LOADING_SPINNER);
    }

    @Step("Click on {0} in main menu")
    public BasePage clickOnItemInMainMenu(String itemName) {
        waitForWebElementClickable(BasePageUI.DYNAMIC_MAIN_LEFT_MENU, itemName);
        clickOnWebElement(BasePageUI.DYNAMIC_MAIN_LEFT_MENU, itemName);
        return PageObjectManager.getPageObject(driver, itemName);
    }

    @Step("From selector {0} select item {1}")
    public void selectItemFromDropdown(String dropdownName, String itemName) {
        waitForWebElementClickable(BasePageUI.DYNAMIC_DROPDOWN_COMPONENT, dropdownName);
        clickOnWebElement(BasePageUI.DYNAMIC_DROPDOWN_COMPONENT, dropdownName);
        waitForWebElementPresence(BasePageUI.DYNAMIC_DROPDOWN_ITEM_SELECTION, dropdownName, itemName).click();
    }

    @Step("Verify current selected menu is: {0}")
    public boolean currentSelectedItemInMainMenuIs(String itemName) {
        return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getFullXPath(
                BasePageUI.DYNAMIC_CURRENT_SELECTED_MAIN_MENU_ITEM, itemName)))).isDisplayed();
    }



}
