package io.rappid.webtest.rappidjs.js.core;

import io.rappid.webtest.common.WebElementPanel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * User: tony
 * Date: 14.10.13
 * Time: 16:15
 */
public class Window extends WebElementPanel {
    public Window(By by) {
        super(by);
    }

    public Window(WebElement element) {
        super(element);
    }
}
