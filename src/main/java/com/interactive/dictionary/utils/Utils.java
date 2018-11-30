package com.interactive.dictionary.utils;

import org.joda.time.DateTime;

public class Utils {

	public static long getMillis() {
		DateTime time = new DateTime();
		return time.getMillis();
	}
	
}
