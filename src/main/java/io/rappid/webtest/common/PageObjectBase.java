package io.rappid.webtest.common;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * User: tony
 * Date: 14.10.13
 * Time: 00:03
 */
public abstract class PageObjectBase extends WebTestBase implements WebElement, Locatable {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    protected PageObjectBase(boolean startValidation) {
        if (startValidation) {
            startValidation();
        }
    }

    public abstract WebElement getWebElement();

    protected abstract void startValidation();

    @Override
    public WebDriver driver() {
        return WebTest.getWebTest().driver();
    }

    @Override
    public JavascriptExecutor jsExecutor() {
        return WebTest.getWebTest().jsExecutor();
    }

    @Override
    public WebTest webTest() {
        return WebTest.getWebTest();
    }

    public WebElement get(By by) {
        return driver().findElement(by);
    }

    public <T> T waitUntil(Function<WebDriver, T> condition) {
        return waitUntil(10, condition);
    }

    public <T> T waitUntil(int timeout, Function<WebDriver, T> condition) {
        return new WebDriverWait(driver(), timeout).until(condition);
    }

    protected abstract void validate();

    protected void navigate(String url) {
        driver().get(url);
    }

    protected void navigateToHash(String hash) {
        jsExecutor().executeScript(String.format("window.location.hash='%s'", hash));
    }

    public WebElement getParent() {
        return getChild(By.xpath("src/main"));
    }

    public WebElement getChild(String cssSelector) {
        return getChild(By.cssSelector(cssSelector));
    }

    public Boolean hasChild(By by) {
        return getChildren(by).size() > 0;
    }

    public Boolean hasChild(String cssSelector) {
        try {
            webTest().disableImplicitlyWait();
            return hasChild(By.cssSelector(cssSelector));
        } finally {
            webTest().enableImplicitlyWait();
        }
    }

    public WebElement getChild(By by) {
        return getWebElement().findElement(by);
    }

    public List<WebElement> getChildren(By by) {
        return getWebElement().findElements(by);
    }

    public List<WebElement> getChildren(String cssSelector) {
        return getWebElement().findElements(By.cssSelector(cssSelector));
    }

    public WebElementPanel getChildPanel(By by) {
        return new WebElementPanel(getWebElement().findElement(by));
    }

    public <T extends Panel> T getChildPanel(String cssSelector, Class<T> factory) {
        return getChildPanel(getChild(By.cssSelector(cssSelector)), factory);
    }

    public <T extends Panel> T getChildPanel(By by, Class<T> factory) {
        return getChildPanel(getChild(by), factory);
    }

    public <T extends Panel> T getChildPanel(WebElement element, Class<T> factory) {
        return getPanel(factory, element);
    }

    protected <T extends Panel> T getPanel(Class<T> factory, WebElement rootElement) {
        try {
            return factory.getConstructor(WebElement.class).newInstance(rootElement);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void click() {
        getWebElement().click();
    }

    public void clickAt(int offsetX, int offsetY) {
        new Actions(driver())
                .moveToElement(this, offsetX, offsetY)
                .click()
                .perform();
    }

    @Override
    public void submit() {
        getWebElement().submit();
    }

    @Override
    public void sendKeys(CharSequence... charSequences) {
        getWebElement().sendKeys(charSequences);
    }

    @Override
    public void clear() {
        getWebElement().clear();
    }

    @Override
    public String getTagName() {
        return getWebElement().getTagName();
    }

    @Override
    public String getAttribute(String s) {
        return getWebElement().getAttribute(s);
    }

    @Override
    public boolean isSelected() {
        return getWebElement().isSelected();
    }

    @Override
    public boolean isEnabled() {
        return getWebElement().isEnabled();
    }

    @Override
    public String getText() {
        return getWebElement().getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return getWebElement().findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return getWebElement().findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return getWebElement().isDisplayed();
    }

    @Override
    public Point getLocation() {
        return getWebElement().getLocation();
    }

    @Override
    public Dimension getSize() {
        return getWebElement().getSize();
    }

    @Override
    public String getCssValue(String s) {
        return getWebElement().getCssValue(s);
    }

    @Override
    public Coordinates getCoordinates() {
        return ((Locatable) getWebElement()).getCoordinates();
    }
}
