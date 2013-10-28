package io.rappid.webtest.common;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.concurrent.Callable;

/**
 * User: tony
 * Date: 13.10.13
 * Time: 22:07
 */
public class WebDriverBackend {

    public static final String SAUCE_ACCESS_KEY = "SAUCE_ACCESS_KEY";
    public static final String SAUCE_USERNAME = "SAUCE_USERNAME";

    private static final Logger log = LoggerFactory.getLogger(WebDriverBackend.class);

    private RemoteWebDriver driver;

    private final String host;
    private final int port;
    private final String browser;

    public WebDriverBackend(String host, int port, String browser) throws Exception {

        this.host = host;
        this.port = port;
        this.browser = browser;

        // define Browser for the webdriver
        final DesiredCapabilities capability = new DesiredCapabilities();
        capability.setBrowserName(browser);

        capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);

        String sauceUsername = System.getProperty(SAUCE_USERNAME, System.getenv(SAUCE_USERNAME));
        String sauceAccessKey = System.getProperty(SAUCE_ACCESS_KEY, System.getenv(SAUCE_ACCESS_KEY));

        if (sauceAccessKey != null && sauceUsername != null) {

            log.info(String.format("Setting username '%s' and accessKey for saucelabs as capability", sauceUsername));

            capability.setCapability("username", sauceUsername);
            capability.setCapability("accessKey", sauceAccessKey);
        }

        setCapability(browser, capability);


        final URL hubUrl = new URL(url());

        Logger log = LoggerFactory.getLogger(WebDriverBackend.class);

        new RetriableTask<Object>(log, new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                driver = new RemoteWebDriver(hubUrl, capability);
                return null;
            }
        }).call();

        driver.manage().window().maximize();
    }

    protected String url() {
        return "http://" + getHost() + ":" + getPort() + "/wd/hub";
    }

    @Override
    public String toString() {
        return String.format("%s [%s]", url(), getBrowser());
    }

    private void setCapability(String browser, DesiredCapabilities capability) {
        if (browser.equals(DesiredCapabilities.firefox().getBrowserName())) {
            // create Firefox profile for the tests
            FirefoxProfile fp = new FirefoxProfile();
            fp.setAcceptUntrustedCertificates(true);
            fp.setAssumeUntrustedCertificateIssuer(true);
            fp.setEnableNativeEvents(true);
            capability.setCapability("firefox_profile", fp);
            fp.setPreference("intl.accept_languages", "de-de,de");

            // chrome browser
        } else if (browser.equals(DesiredCapabilities.chrome().getBrowserName())) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized", "--ignore-certificate-errors", "--disable-images");
            capability.setCapability(ChromeOptions.CAPABILITY, options);

            // phantomjs browser
        } else if (browser.equals(DesiredCapabilities.phantomjs().getBrowserName())) {
            DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
            capability.merge(capabilities);

            // internet explorer 8 on windows 7
        } else if (browser.equals("iexplore8")) {
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability("platform", "Windows 7");
            capabilities.setCapability("version", "8");
            capability.merge(capabilities);

            // internet explorer 9 on windows 7
        } else if (browser.equals("iexplore9")) {
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability("platform", "Windows 7");
            capabilities.setCapability("version", "9");
            capability.merge(capabilities);

            // internet explorer 10 on windows 8
        } else if (browser.equals("iexplore10")) {
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability("platform", "Windows 8");
            capabilities.setCapability("version", "10");
            capability.merge(capabilities);

            // iphone with OS 6
        } else if (browser.equals(DesiredCapabilities.iphone().getBrowserName())) {
            DesiredCapabilities capabilities = DesiredCapabilities.iphone();
            capabilities.setCapability("platform", "OS X 10.8");
            capabilities.setCapability("version", "6");
            capability.merge(capabilities);

            // iphone with OS 7 (upcoming OS)
        } else if (browser.equals(DesiredCapabilities.iphone().getBrowserName() + "7")) {
            DesiredCapabilities capabilities = DesiredCapabilities.iphone();
            capabilities.setCapability("platform", "OS X 10.8");
            capabilities.setCapability("version", "7");
            capability.merge(capabilities);

            // ipad with OS 6
        } else if (browser.equals(DesiredCapabilities.ipad().getBrowserName())) {
            DesiredCapabilities capabilities = DesiredCapabilities.ipad();
            capabilities.setCapability("platform", "OS X 10.8");
            capabilities.setCapability("version", "6");
            capability.merge(capabilities);

            // ipad with OS 7
        } else if (browser.equals(DesiredCapabilities.ipad().getBrowserName() + "7")) {
            DesiredCapabilities capabilities = DesiredCapabilities.ipad();
            capabilities.setCapability("platform", "OS X 10.8");
            capabilities.setCapability("version", "7");
            capability.merge(capabilities);

            // android 4 phone form
        } else if (browser.equals(DesiredCapabilities.android().getBrowserName())) {
            DesiredCapabilities capabilities = DesiredCapabilities.android();
            capabilities.setCapability("platform", "Linux");
            capabilities.setCapability("version", "4.0");
            capability.merge(capabilities);

            // android 4 tablet form
        } else if (browser.equals(DesiredCapabilities.android().getBrowserName() + "tablet")) {
            DesiredCapabilities capabilities = DesiredCapabilities.android();
            capabilities.setCapability("platform", "Linux");
            capabilities.setCapability("version", "4.0");
            capabilities.setCapability("device-type", "tablet");
            capability.merge(capabilities);

            // safari 5 on windows 7
        } else if (browser.equals("safari5win7")) {
            DesiredCapabilities capabilities = DesiredCapabilities.safari();
            capabilities.setCapability("platform", "Windows 7");
            capabilities.setCapability("version", "5");
            capability.merge(capabilities);

            // safari 6 on macosx mountain lion
        } else if (browser.equals("safari6macosx108")) {
            DesiredCapabilities capabilities = DesiredCapabilities.safari();
            capabilities.setCapability("platform", "OS X 10.8");
            capabilities.setCapability("version", "6");
            capability.merge(capabilities);

            // chrome on linux
        } else if (browser.equals("chromelinux")) {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("platform", "Linux");
            capabilities.setCapability("version", "");
            capability.merge(capabilities);

        } else {
            throw new RuntimeException("Sorry I do have no idea what kind of browser " + browser + " is. I currently" +
                    "am aware only about: chrome, firefox, phantomjs, iexplore8, iexplore9, iexplore10,  iphone " +
                    "(which is iOS6), iphone7, ipad (same iOS6), ipad7, android, androidtablet, safari5win7, " +
                    "safari6macosx108, chromelinux. Read here about configurations: https://saucelabs.com/docs/" +
                    "platforms/webdriver");
        }

    }

    public RemoteWebDriver driver() {
        return driver;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getBrowser() {
        return browser;
    }

    public void close() {
        if (driver != null) {
            driver.quit();
//            driver.close();
        }
    }
}
