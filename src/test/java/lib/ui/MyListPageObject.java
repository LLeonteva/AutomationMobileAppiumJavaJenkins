package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListPageObject extends MainPageObject {

    protected static String
        FOLDER_BY_NAME_TPL,
        ARTICLE_BY_TITLE_TML,
        ARTICLE_ELEMENT;

    public MyListPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    public static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    public static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TML.replace("{TITLE}", article_title);
    }
    /* TEMPLATES METHODS */

    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(folder_name_xpath, "Cannot find created folder", 30);
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(article_xpath, "Cannot find saved article by title: " + article_title, 30);
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(article_xpath, "Saved article still present with title: " + article_title, 15);
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(article_xpath, "Cannot find saved article");
        if (Platform.getInstance().isIOS()){
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void waitForArticleAndClick(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementAndClick(article_xpath, "Cannot find and click at second article title after deleting the first", 15);
    }

    public int getAmountOfListedArticles() {
        this.waitForElementPresent(ARTICLE_ELEMENT, "Cannot find saved articles", 15);
        return this.getAmountOfElements(ARTICLE_ELEMENT);
    }
}
