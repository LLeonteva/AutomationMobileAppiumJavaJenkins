package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    private static final String name_of_folder = "Learning programming";

    @Test
    // 3.3 Save first article: overlay, swipe left
    // 3.4 Swipe: debugging. Variable
    public void testSaveFirstArticleToMyListAndDelete() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isIOS()) {
            ArticlePageObject.closeArticle();
            ArticlePageObject.closeArticle();
        } else {
            ArticlePageObject.closeArticle();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyList();

        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()){
            MyListPageObject.openFolderByName(name_of_folder);
        }
        MyListPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    // Ex 11. Refactoring ex.5 for Android & iOS
    public void testCheckSecondArticleSavedInFolderAfterDeletedFirstArticle_2() {
        if (Platform.getInstance().isAndroid()) {
            return;
        }
        String search_text_1 = "Java";
        String search_result_locator_1 = "Java (programming language)";
        String search_text_2 = "Appium";
        String search_result_locator_2 = "Appium";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_text_1);
        SearchPageObject.clickByArticleWithSubstring(search_result_locator_1);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isIOS()) {
            ArticlePageObject.closeArticle();
            ArticlePageObject.closeArticle();
        } else {
            ArticlePageObject.closeArticle();
        }

        // Add 2-nd article
        SearchPageObject.initSearchInput();

        if (Platform.getInstance().isIOS()) {
            SearchPageObject.clickClearMiniButton();
        }

        SearchPageObject.typeSearchLine(search_text_2);
        SearchPageObject.clickByArticleWithSubstring(search_result_locator_2);

        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();

        // Go to My list
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyList();

        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
            MyListPageObject.openFolderByName(name_of_folder);
        }

        int amount_of_articles_before = MyListPageObject.getAmountOfListedArticles();
        MyListPageObject.swipeByArticleToDelete(search_result_locator_2);

        int amount_of_article_after = MyListPageObject.getAmountOfListedArticles();

        assertEquals(
                "We found wrong amount of articles on the list",
                amount_of_articles_before -1,
                amount_of_article_after
        );
        MyListPageObject.waitForArticleAndClick(search_result_locator_1);
        String actual_title = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Article title does not match",
                search_result_locator_1,
                actual_title
        );
        ArticlePageObject.waitForTitleElement();
    }
}



