package io.rappid.webtest.common;

import com.google.common.base.Function;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * User: tony
 * Date: 27.10.13
 * Time: 11:08
 */
public abstract class WebTestBase implements IWebTestBase {

    @Override
    public String getCurrentUrl() {
        return driver().getCurrentUrl();
    }

    @Override
    public URI getUri() {
        try {
            return new URI(getCurrentUrl());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T waitUntil(Function<WebDriver, T> condition) {
        return waitUntil(10, condition);
    }

    public <T> T waitUntil(int timeout, Function<WebDriver, T> condition) {
        return new WebDriverWait(driver(), timeout).until(condition);
    }

}
