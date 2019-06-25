package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase
{
    @Test
    public void testCompareSelectedArticle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = ArticlePageObject.getArticleTitle();

        assertEquals(
                "We see unexpected article",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    // 3.1 Swipe: start_x, Touch action, dimensions
    public void testSwipeArticle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        String search_result = "Object-oriented programming language";
        SearchPageObject.clickByArticleWithSubstring(search_result);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeArticle();
    }

    @Test
    // 3.2 Swipe till element found: swipeQuick, counter, error
    public void testSwipeArticleUntilFindElement() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }


    @Test
    // Ex 6. Test. Assert title
    // Checks for article title without waiting for the element (always fails)
//    public void testAssertElementPresent() {
//        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
//
//        SearchPageObject.initSearchInput();
//        String search_line = "Java";
//        SearchPageObject.typeSearchLine(search_line);
//        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
//
//        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
//        ArticlePageObject.ArticlePresentWithoutWait();
//    }
//}
