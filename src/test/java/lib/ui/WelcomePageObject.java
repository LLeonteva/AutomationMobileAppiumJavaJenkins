package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject
{
    private static final String
        STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
        STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
        STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "id:Add or edit preferred languages",
        STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected",
        NEXT_LINK = "id:Next",
        GET_STARTED_BUTTON = "id:Get started",
        SKIP_LINK = "id:Skip";

    public WelcomePageObject (AppiumDriver driver)
    {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        waitForElementPresent(STEP_LEARN_MORE_LINK, "Can not find 'Learn more about Wikipedia' link", 10);
    }

    public void waitForNewWaysToExploreText() {
        waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT, "Can not find 'New ways to explore' link", 10);
    }

    public void waitForAddOrEditPrefLangText() {
        waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK, "Can not find 'Add or edit preferred languages' link", 10);
    }

    public void waitForLearnMoreAboutDataCollectedText() {
        waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK, "Can not find 'Learn more about data collected' link", 10);
    }

    public void clickNextButton() {
        waitForElementAndClick(NEXT_LINK, "Can not find and click 'Next' button", 10);
    }

    public void clickGetStartedButton() {
        waitForElementAndClick(GET_STARTED_BUTTON, "Can not find and click 'Get started' button", 10);
    }

    public void clickSkip() {
        this.waitForElementAndClick(SKIP_LINK,"Cannot find and click Skip button", 5 );
    }
}
