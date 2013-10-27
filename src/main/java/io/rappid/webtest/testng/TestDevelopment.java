package io.rappid.webtest.testng;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 * User: tony
 * Date: 19.10.13
 * Time: 16:53
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({METHOD})
public @interface TestDevelopment {
}
