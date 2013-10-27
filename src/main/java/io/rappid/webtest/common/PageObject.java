package io.rappid.webtest.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * User: tony
 * Date: 14.10.13
 * Time: 00:13
 */
public abstract class PageObject extends PageObjectBase {

    protected PageObject() {
        super(true);
    }

    @Override
    protected WebElement getWebElement() {
        return get(By.tagName("html"));
    }

    protected void startValidation() {
        // validate the page object

        try {
            waitUntil(new ExpectedCondition<Boolean>() {
                public Boolean apply(org.openqa.selenium.WebDriver webDriver) {
                    return jsExecutor().executeScript("return document.readyState").equals("complete");
                }
            });

            validate();
        } catch (Exception e) {
            throw new RuntimeException(String.format("Validation Failed for %s on ", this.getClass(), driver().getCurrentUrl()), e);
        }

        log.info("URL after validation " + driver().getCurrentUrl());

    }

}
