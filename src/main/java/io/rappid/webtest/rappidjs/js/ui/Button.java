package io.rappid.webtest.rappidjs.js.ui;

import io.rappid.webtest.common.WebElementPanel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * User: tony
 * Date: 14.10.13
 * Time: 15:42
 */
public class Button extends WebElementPanel {

    public Button(By by) {
        super(by);
    }

    public Button(WebElement element) {
        super(element);
    }
}
