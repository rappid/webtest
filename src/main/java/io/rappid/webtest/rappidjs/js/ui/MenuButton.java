package io.rappid.webtest.rappidjs.js.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * User: tony
 * Date: 20.10.13
 * Time: 18:38
 */
public class MenuButton extends View {
    public MenuButton(By by) {
        super(by);
    }

    public MenuButton(WebElement element) {
        super(element);
    }

    public String getLabel() {
        return getChild("span:not(.caret)").getText();
    }
}
