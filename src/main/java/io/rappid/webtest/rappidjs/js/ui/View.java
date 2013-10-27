package io.rappid.webtest.rappidjs.js.ui;

import io.rappid.webtest.common.WebElementPanel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * User: tony
 * Date: 20.10.13
 * Time: 18:38
 */
public class View extends WebElementPanel {
    public View(By by) {
        super(by);
    }

    public View(WebElement element) {
        super(element);
    }
}
