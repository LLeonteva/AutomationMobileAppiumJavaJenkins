package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
    SEARCH_INIT_ELEMENT ="xpath://*[contains(@text,'Search Wikipedia')]";
    SEARCH_INPUT ="xpath://*[contains(@text,'Search…')]";
    SEARCH_CANCEL_BUTTON ="id:org.wikipedia:id/search_close_btn";
    SEARCH_RESULT_BY_SUBSTRING_TPL ="xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
    SEARCH_RESULT_BY_SUBSTRING_TITLE_DESC_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container' and .//@text='{TITLE_SUBSTRING}' and .//@text='{DESC_SUBSTRING}']";
    SEARCH_RESULT_ELEMENTS ="xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
    SEARCH_EMPTY_RESULTS_LABEL ="xpath://*[@text='No results found']";
    SEARCH_INPUT_TEXT_FIELD ="id:org.wikipedia:id/search_src_text";
    SEARCH_RESULT_TITLES = "id:org.wikipedia:id/page_list_item_title";
}

    public AndroidSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
