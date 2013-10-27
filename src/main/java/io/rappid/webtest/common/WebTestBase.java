package io.rappid.webtest.common;

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

}
