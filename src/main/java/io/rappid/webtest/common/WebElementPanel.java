package io.rappid.webtest.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * User: tony
 * Date: 14.10.13
 * Time: 00:29
 */

public class WebElementPanel extends WebElementPanelBase {

    private final boolean initializedByElement;
    private By by;
    private WebElement element;

    public WebElementPanel(By by) {
        super();

        initializedByElement = false;
        this.by = by;
        startValidation();
    }


    public WebElementPanel(WebElement element) {
        super();

        initializedByElement = true;
        this.element = element;
        startValidation();
    }

    @Override
    protected WebElement getWebElement() {

        if (initializedByElement) {
            return element;
        } else {
            return driver().findElement(by);
        }

    }


    public boolean hasClass(String className) {
        return getWebElement().getAttribute("class").contains(className);
    }

}