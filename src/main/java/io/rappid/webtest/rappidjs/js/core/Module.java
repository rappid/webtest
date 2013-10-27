package io.rappid.webtest.rappidjs.js.core;

import io.rappid.webtest.rappidjs.js.ui.View;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * User: tony
 * Date: 27.10.13
 * Time: 11:15
 */
public class Module extends View{
    public Module(By by) {
        super(by);
    }

    public Module(WebElement element) {
        super(element);
    }
}
