package org.imakhnyk.interview.menuvoting.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateUtils used as common place to work with date and format
 * 
 * @author Ivan Makhnyk
 *
 */
public class DateUtils {
	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyyMMdd");

	public static String getStringOfCurentDate() {
		return getStringOfDate(new Date());
	}

	public static String getStringOfDate(Date date) {
		return DATE_FORMAT.format(date);
	}
}
