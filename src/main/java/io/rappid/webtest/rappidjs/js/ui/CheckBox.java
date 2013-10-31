package io.rappid.webtest.rappidjs.js.ui;

import io.rappid.webtest.common.WebElementPanel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * User: tony
 * Date: 31.10.13
 * Time: 14:58
 */
public class Checkbox extends WebElementPanel{
    public Checkbox(By by) {
        super(by);
    }

    public Checkbox(WebElement element) {
        super(element);
    }
}
