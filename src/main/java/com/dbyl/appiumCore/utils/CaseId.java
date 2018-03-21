package main.java.com.dbyl.appiumCore.utils;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Interface CaseId.
 * 
 * @author Young
 * @version V1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ METHOD, TYPE })
public @interface CaseId {

	/**
	 * This is for test case Id
	 * 
	 * @author Young
	 *
	 * @return the string[]
	 */
	public String[] id() default {};
}
