package io.rappid.webtest.rappidjs.js.ui;

import io.rappid.webtest.common.WebElementPanel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * User: tony
 * Date: 14.10.13
 * Time: 01:25
 */
public class Field extends WebElementPanel {
    public Field(By by) {
        super(by);
    }

    public Field(WebElement element) {
        super(element);
    }

}
