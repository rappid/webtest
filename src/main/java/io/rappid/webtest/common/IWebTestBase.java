package io.rappid.webtest.common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.net.URI;

/**
 * User: tony
 * Date: 27.10.13
 * Time: 11:00
 */
public interface IWebTestBase {

    public WebDriver driver();

    JavascriptExecutor jsExecutor();

    WebTest webTest();

    String getCurrentUrl();

    URI getUri();
}
