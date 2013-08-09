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
 * @since 2013-6-24上午08:55:23
 * @version 1.0
 */

@Retention(RetentionPolicy.RUNTIME) 
@Target({ElementType.TYPE})
public @interface SelfTable {

	public String name();
	
}
