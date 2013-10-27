package io.rappid.webtest.rappidjs.js.html;

import com.google.common.base.Objects;
import io.rappid.webtest.common.WebElementPanel;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * User: tony
 * Date: 14.10.13
 * Time: 01:17
 */
public class Input extends WebElementPanel {
    public Input(By by) {
        super(by);
    }

    public Input(WebElement element) {
        super(element);
    }

    public String getValue() {
        return getWebElement().getAttribute("value");
    }

    public void setValue(String value) {
        focus();
        getWebElement().clear();
        getWebElement().sendKeys(value);
    }

    public String getType() {
        return Objects.firstNonNull(getWebElement().getAttribute("type"), "text");
    }

    public void pressEnter() {
        focus();
        getWebElement().sendKeys(Keys.ENTER);
    }

    public void focus() {
        if (!getWebElement().isSelected()) {
            getWebElement().click();
        }
    }
}
