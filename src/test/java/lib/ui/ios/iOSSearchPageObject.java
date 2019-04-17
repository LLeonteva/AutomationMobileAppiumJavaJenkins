package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT ="xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT ="xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON ="id:Close";
        SEARCH_RESULT_BY_SUBSTRING_TPL ="xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_BY_SUBSTRING_TITLE_DESC_TPL = "xpath://*[normalize-space(@name)='{TITLE_SUBSTRING} {DESC_SUBSTRING}']";
        SEARCH_RESULT_ELEMENTS ="xpath://XCUIElementTypeLink";
        SEARCH_EMPTY_RESULTS_LABEL ="xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_INPUT_TEXT_FIELD ="xpath://XCUIElementTypeSearchField";
        SEARCH_INPUT_CLEAR_MINI = "id:clear mini";
        SEARCH_RESULT_TITLES = "xpath://XCUIElementTypeCell[@visible='true']/XCUIElementTypeLink";
    }

    public iOSSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
