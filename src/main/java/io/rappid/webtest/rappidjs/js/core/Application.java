package io.rappid.webtest.rappidjs.js.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * User: tony
 * Date: 31.10.13
 * Time: 15:44
 */
public class Application extends Window {
    public Application() {
        this(By.cssSelector(".stage"));
    }
    public Application(By by) {
        super(by);
    }

    public Application(WebElement element) {
        super(element);
    }
}
