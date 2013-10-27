package io.rappid.webtest.testng;

import org.testng.IMethodSelector;
import org.testng.IMethodSelectorContext;
import org.testng.ITestNGMethod;

import java.util.List;

/**
 * User: tony
 * Date: 19.10.13
 * Time: 16:10
 */
public class MethodSelector implements IMethodSelector {
    @Override
    public boolean includeMethod(IMethodSelectorContext context, ITestNGMethod testMethod, boolean isTestMethod) {

        if (!isTestMethod) {
            context.setStopped(true);
            return false;
        }

        String dev = System.getProperty("dev");

        if (dev != null && dev.equals("dev")) {
            if (testMethod.getMethod().getAnnotation(TestDevelopment.class) == null) {
                context.setStopped(true);
            }
        }

        return false;
    }

    @Override
    public void setTestMethods(List<ITestNGMethod> iTestNGMethods) {
    }
}
