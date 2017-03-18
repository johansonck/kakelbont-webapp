package org.kakelbont.webapp.calendar.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter {

	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public Date toDate(String string) {
		try {
			return FORMAT.parse(string);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String toString(Date date) {
		return FORMAT.format(date);
	}
}
