package io.rappid.webtest.rappidjs.js.ui;

import io.rappid.webtest.rappidjs.js.core.Window;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * User: tony
 * Date: 14.10.13
 * Time: 01:13
 */
public class Dialog extends Window {
    public Dialog(By by) {
        super(by);
    }

    public Dialog(WebElement element) {
        super(element);
    }

    public String getTitle() {
        return getChild(By.cssSelector("h3")).getText();
    }
}
