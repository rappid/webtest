package io.rappid.webtest.rappidjs.js.ui.field;

import io.rappid.webtest.rappidjs.js.html.Input;
import io.rappid.webtest.rappidjs.js.ui.Field;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * User: tony
 * Date: 14.10.13
 * Time: 01:16
 */
public class Text extends Field {
    public Text(By by) {
        super(by);
    }

    public Text(WebElement element) {
        super(element);
    }

    public Input getInput() {
        return getChildPanel(By.cssSelector("input"), Input.class);
    }

    public WebElement getLabel() {
        return getChild(By.cssSelector("label"));
    }

    public String getLabelText() {
        return getLabel().getText();
    }

}
