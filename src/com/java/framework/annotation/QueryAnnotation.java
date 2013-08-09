/**
 * 
 */
package com.java.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author pengluwei
 * @since 2013-6-19下午05:09:17
 * @version 1.0
 */

@Retention(RetentionPolicy.RUNTIME) 
@Target({ElementType.FIELD})
public @interface QueryAnnotation {
	
	public String name() default "=";
	
	public Class<?> type() default String.class;
	
	public String alias() default "";
	
	public String between() default "";
	
}
