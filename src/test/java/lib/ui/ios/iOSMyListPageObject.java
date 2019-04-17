package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class iOSMyListPageObject extends MyListPageObject
{
    static {
        ARTICLE_BY_TITLE_TML = "xpath://XCUIElementTypeLink[contains(@name,'{TITLE}')]";
        ARTICLE_ELEMENT = "xpath://XCUIElementTypeCell";
    }

    public iOSMyListPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
