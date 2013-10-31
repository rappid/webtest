package io.rappid.webtest.rappidjs.js.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * User: tony
 * Date: 31.10.13
 * Time: 13:00
 */
public class TabView extends View {
    private final WebElement content;

    public TabView(WebElement tab, WebElement content) {
        super(tab);

        this.content = content;
    }

    public String getLabel() {
        return getChild(".label").getText();
    }

    public WebElement getContent() {
        return content;
    }
}
