package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class AndroidMyListPageObject extends MyListPageObject {

    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TML = "xpath://*[@text='{TITLE}']";
        ARTICLE_ELEMENT = "id:org.wikipedia:id/page_list_item_container";
    }

    public AndroidMyListPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
