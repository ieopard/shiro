/**
 * 
 */
package com.java.framework.conver;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * @author pengluwei
 * @since 2013-6-19下午03:03:19
 * @version 1.0
 */
public class DateConverter implements Converter<String,Date>{

	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public Date convert(String date) {
		if(date != null && date.trim().length() > 0){
			try {
				return df.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
