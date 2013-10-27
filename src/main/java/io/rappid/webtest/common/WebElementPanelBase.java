package io.rappid.webtest.common;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * User: tony
 * Date: 14.10.13
 * Time: 00:21
 */
public abstract class WebElementPanelBase extends Panel {

    @Override
    protected void startValidation() {
        validate();
    }

    @Override
    public void validate() throws AssertionError {
        WebElement element = getWebElement();
        Assert.assertNotNull(element, "WebElement not found.");
    }
}
