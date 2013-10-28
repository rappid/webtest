package io.rappid.webtest.rappidjs.js.ui;

import io.rappid.webtest.common.WebElementPanel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * User: tony
 * Date: 20.10.13
 * Time: 18:38
 */
public class MenuButton extends Button {
    public MenuButton(By by) {
        super(by);
    }

    public MenuButton(WebElement element) {
        super(element);
    }

    public Button button() {
        return getChildPanel(".btn", Button.class);
    }

    public WebElementPanel menu() {
        return getChildPanel(".dropdown-menu", WebElementPanel.class);
    }

    public String getLabel() {
        return getChild("span:not(.caret)").getText();
    }

    public boolean menuIsOpened() {
        return hasClass("open");
    }

    public boolean menuIsClosed() {
        return !menuIsOpened();
    }
}
