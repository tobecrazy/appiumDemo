package main.java.com.dbyl.appiumCore.tests;



import io.appium.java_client.android.AndroidDriver;

import com.google.common.base.Function;


/**
 * Models a condition that might reasonably be expected to eventually evaluate to something that is
 * neither null nor false. Examples would include determining if a web page has loaded or that an
 * element is visible.
 * <p>
 * Note that it is expected that ExpectedConditions are idempotent. They will be called in a loop by
 * the {@link WebDriverWait} and any modification of the state of the application under test may
 * have unexpected side-effects.
 * 
 * @param <T> The return type
 */
public interface ExpectedCondition<T> extends Function<AndroidDriver, T> {}
