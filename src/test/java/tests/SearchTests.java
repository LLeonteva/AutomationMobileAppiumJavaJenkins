package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchTests extends CoreTestCase
{
    @Test
    public void testListSearchResults() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonDisappear();
    }

    @Test
    public void testClearSearchAndCancel() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clearSearchTextInput();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonDisappear();
    }

    @Test
    // 2. Ex 2. Check text "Search..." in search input text field
    public void testCompareSearchPlaceholderText() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String text_search = SearchPageObject.getTextFromSearchInput();

        if (Platform.getInstance().isAndroid()){
            assertEquals(
                    "We see '" + text_search + "' instead of 'Search…' in search input field",
                    "Search…",
                    text_search);
        } else {
            assertEquals(
                    "We see '" + text_search + "' instead of 'Search Wikipedia' in search input field",
                    "Search Wikipedia",
                    text_search);
        }
    }

    @Test
    // 2. Ex 3. Search text -> Check 3 results -> Cancel -> Check no results
    public void testCancelSearchAfterArticlesFound() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("country");
        int amount_of_found_articles = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "Cannot find > 1 article",
                amount_of_found_articles > 1
        );

        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    // @Test
    // Lesson 2. Ex 4*. Search text -> Check all results contain search text
    public void testSearchResultsContainSearchWord() {
        String search_word = "Android";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_word);
        List<WebElement> search_results = SearchPageObject.getArticleTitles();

        for (WebElement element: search_results) {
            String article_title = element.getText();
            String position = Integer.toString(search_results.indexOf(element));

            assertEquals(
                    "Cannot find '" + search_word + "' in article title '" + article_title + "'. Article index " + position,
                    true,
                    SearchPageObject.isContainsSubstr(article_title, search_word)
            );
        }
    }

    @Test
    // 3.5 Assert: basic
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park discography";
        SearchPageObject.typeSearchLine(search_line);

        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "Cannot find any results",
                amount_of_search_results > 0
        );
    }

    @Test
    // 3.6 Assert: assertion error
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "jfjsljsljflsj";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }


    @Test // ex 9*
    public void testSearchResultsContainTitleAndDescription() {
        String search_word = "Android";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_word);

        SearchPageObject.waitForElementByTitleAndDescription(
                "Android",
                "Wikimedia disambiguation page");
        SearchPageObject.waitForElementByTitleAndDescription(
                "Android version history",
                "Wikipedia list");
        SearchPageObject.waitForElementByTitleAndDescription(
                "Android software development",
                "Process of writing software for Android operating system");
    }
}
