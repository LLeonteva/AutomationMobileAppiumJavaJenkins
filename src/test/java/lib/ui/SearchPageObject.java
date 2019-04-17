package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject
{
    protected static String
        SEARCH_INIT_ELEMENT,
        SEARCH_INPUT,
        SEARCH_CANCEL_BUTTON,
        SEARCH_RESULT_BY_SUBSTRING_TPL,
        SEARCH_RESULT_BY_SUBSTRING_TITLE_DESC_TPL,
        SEARCH_RESULT_ELEMENTS,
        SEARCH_RESULT_TITLES,
        SEARCH_EMPTY_RESULTS_LABEL,
        SEARCH_INPUT_TEXT_FIELD,
        SEARCH_INPUT_CLEAR_MINI;


    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
       return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchByTitleAndDescription(String title_substring, String desc_substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TITLE_DESC_TPL
                .replace("{TITLE_SUBSTRING}", title_substring)
                .replace("{DESC_SUBSTRING}", desc_substring);
    }
    /* TEMPLATES METHODS */

    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INPUT, "Cannot find search input after clicking search init element");
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button.", 5);
    }

    public void waitForCancelButtonDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 15);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line,"Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result" + substring);
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        String search_result_locator = getResultSearchByTitleAndDescription(title, description);
        this.waitForElementPresent(search_result_locator,
                "Cannot find search result with title '" + title + "' and desc '" + description + "', locator: " + search_result_locator,
                15);
    }

    public void waitForSearchResultNotPresent(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementNotPresent(search_result_xpath, "Search result is still presented: " + substring, 15);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring: " + substring, 5);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENTS, "Cannot find anything by the request", 15);
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENTS);
    }

    public List<WebElement> getArticleTitles() {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENTS, "Cannot find anything by the request", 15);
        return this.waitAndGetElements(SEARCH_RESULT_TITLES, "Cannot find any search result list items", 15);
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(SEARCH_EMPTY_RESULTS_LABEL, "Cannot find empty result label 'No results found' by the invalid request", 15);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENTS, "We supposed not to found any search results");
    }

    public void clearSearchTextInput() {
        this.waitForElementAndClear(SEARCH_INPUT_TEXT_FIELD, "Cannot find search field", 5);
    }

    public void clickClearMiniButton() {
        this.waitForElementAndClick(SEARCH_INPUT_CLEAR_MINI, "Cannot find clear mini button", 5);
    }

    public WebElement waitForSearchTextInputElement() {
        return this.waitForElementPresent(SEARCH_INPUT, "Cannot find text in search input field", 5);
    }

    public String getTextFromSearchInput() {
        WebElement text_search_input = waitForSearchTextInputElement();
        if (Platform.getInstance().isAndroid()) {
            return text_search_input.getAttribute("text");
        } else {
            return text_search_input.getAttribute("name");
        }
    }
}
