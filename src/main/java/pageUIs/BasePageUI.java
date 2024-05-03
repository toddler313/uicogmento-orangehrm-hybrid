package pageUIs;

public class BasePageUI {
    public static final String LOADING_SPINNER = "css=div.oxd-loading-spinner";
    public static final String NO_RECORD_FOUND_IN_TABLE = "xpath=//span[text()='No Records Found']";
    public static final String TOAST_NO_RECORD_FOUND = "css=div.oxd-toast--info";
    public static final String TOAST_SAVE_SUCESS = "css=div.oxd-toast--success";
    public static final String GREEN_SAVE_BUTTONS = "css=button.oxd-button--secondary";
    public static final String WHITE_CANCEL_BUTTONS = "css=button.oxd-button--ghost";

    /**
     *  Dynamic locators
     */
    public static final String DYNAMIC_MAIN_LEFT_MENU = "xpath=//span[text()='%s']/ancestor::li";
    public static final String DYNAMIC_CURRENT_SELECTED_MAIN_MENU_ITEM = "xpath=//span[text()='%s']//parent::a[@class='oxd-main-menu-item active']";
    public static final String DYNAMIC_DROPDOWN_COMPONENT = "xpath=//span[contains(text(),'%s')]/parent::li";
    public static final String DYNAMIC_DROPDOWN_All_ITEMS = "xpath=//span[contains(text(),'%s')]/following-sibling::ul//a";
    public static final String DYNAMIC_DROPDOWN_ITEM_SELECTION = "xpath=//span[contains(text(),'%s')]/following-sibling::ul//a[text()='%s']/parent::li";
}
