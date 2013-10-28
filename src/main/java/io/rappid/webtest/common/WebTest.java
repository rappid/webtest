package io.rappid.webtest.common;

import com.google.common.base.Objects;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.xml.XmlTest;

import java.util.concurrent.TimeUnit;

/**
 * User: tony
 * Date: 13.10.13
 * Time: 21:37
 */
public abstract class WebTest extends WebTestBase {

    public final static String DRIVER = "driver";
    protected final static ThreadLocal<WebDriverBackend> backend = new ThreadLocal<WebDriverBackend>();
    protected final static ThreadLocal<WebTest> testBackend = new ThreadLocal<WebTest>();

    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    private static ITestContext testContext;

    public static WebDriverBackend getWebDriverBackend() {
        return backend.get();
    }

    public static WebTest getWebTest() {
        return testBackend.get();
    }

    @Override
    public WebDriver driver() {
        return getWebDriverBackend().driver();
    }

    @Override
    public JavascriptExecutor jsExecutor() {
        return getWebDriverBackend().driver();
    }

    @Override
    public WebTest webTest() {
        return testBackend.get();
    }

    protected int implicitlyWaitTimeout() {
        return 5;
    }


    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext context) {
        testContext = context;
    }

    protected synchronized String getParameter(String key) {
        return testContext.getCurrentXmlTest().getParameter(key);
    }

    @BeforeMethod(alwaysRun = true)
    public synchronized void beforeMethod(ITestResult testResult) throws Exception {

        testBackend.set(this);

        String host = Objects.firstNonNull(getParameter("grid.host"), "localhost");
        int port = Integer.parseInt(Objects.firstNonNull(getParameter("grid.port"), "4444"));
        String browser = Objects.firstNonNull(getParameter("browser"), "chrome");

        WebDriverBackend webDriverBackend = new WebDriverBackend(host, port, browser);
        backend.set(webDriverBackend);

        log.info("Using grid: " + webDriverBackend.toString());

        RemoteWebDriver driver = webDriverBackend.driver();
        testResult.setAttribute(DRIVER, driver);

        enableImplicitlyWait();

    }

    public void enableImplicitlyWait() {
        getWebTest().driver().manage().timeouts().implicitlyWait(implicitlyWaitTimeout(), TimeUnit.SECONDS);
    }

    public void disableImplicitlyWait() {
        getWebTest().driver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @AfterMethod(alwaysRun = true)
    public synchronized void afterMethod() {
        try {
            WebDriverBackend webDriverBackend = backend.get();
            if (webDriverBackend != null) {
                webDriverBackend.close();
            }
        } catch (Throwable e) {
            log.error("Ignoring error in afterMethod: ", e);
        }
    }

    public abstract String getStartUrl();

}
