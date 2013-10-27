package io.rappid.webtest.testng;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;

import java.util.ArrayList;
import java.util.List;

/**
 * User: tony
 * Date: 19.10.13
 * Time: 15:50
 */
public class TestResultPrinter implements ITestListener, ISuiteListener {
    public final static Logger log = LoggerFactory.getLogger(TestResultPrinter.class);
    private final List<String> failedTests = new ArrayList<String>();
    private final List<String> skippedTests = new ArrayList<String>();

    @Override
    public void onTestStart(final ITestResult result) {
        return;
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        return;
    }

    /**
     * if test failed
     * add the test to the failed test list
     *
     * @param result - the test result for the failed test
     */
    @Override
    public void onTestFailure(ITestResult result) {
        filterStackTrace("failed test ", result.getThrowable());
        addTest(result, failedTests);
    }

    private void filterStackTrace(String msg, Throwable throwable) {

        StringBuilder trace = new StringBuilder(msg + "\n");
        if (throwable != null && throwable.getMessage() != null) {
            trace.append("\t" + throwable.getMessage().split("Command duration or timeout")[0] + "\n");
        } else {
            trace.append("\t " + throwable);
        }

        if (throwable != null && throwable.getStackTrace() != null) {
            StackTraceElement[] stackTrace = throwable.getStackTrace();
            for (StackTraceElement element : stackTrace) {
                if (element != null && element.toString().contains("net.sprd.qa")) {
                    trace.append("\t" + element.toString() + "\n");
                }
            }
        }
        log.error(trace.toString());
    }

    /**
     * if test skipped
     * add the test to the skipped test list
     *
     * @param result - the test result for the skipped test
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        filterStackTrace("skipped test", result.getThrowable());
        addTest(result, skippedTests);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        return;
    }

    @Override
    public void onStart(ITestContext context) {
        return;
    }

    @Override
    public void onFinish(ITestContext context) {
        return;
    }

    private void addTest(ITestResult result, List<String> listOfTests) {
        String line = result.getMethod().getRealClass().getCanonicalName() + "." + result.getMethod().getMethodName()
                + "()";
        if (result.getInstance() instanceof ITest) {
            line += " (" + result.getName() + ")";
        }

        listOfTests.add(line);
    }

    @Override
    public void onStart(ISuite suite) {
        return;
    }

    /**
     * print the failed tests
     *
     * @param suite - the test suite
     */
    @Override
    public void onFinish(ISuite suite) {
        if (!failedTests.isEmpty()) {
            log.info("failed tests");
            for (String line : failedTests) {
                log.info("\t" + line);
            }
        }

        if (!skippedTests.isEmpty()) {
            log.info("skipped tests");
            for (String line : skippedTests) {
                log.info("\t" + line);
            }
        }
    }
}
