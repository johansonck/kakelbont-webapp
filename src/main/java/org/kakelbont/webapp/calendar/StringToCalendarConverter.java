package org.kakelbont.webapp.calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class StringToCalendarConverter {
	
	private final StringToDateConverter converter = new StringToDateConverter(); 

	public Calendar toCalendar(String string) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(converter.toDate(string));
		return calendar;
	}

	public String toString(Calendar calendar) {
		return converter.toString(calendar.getTime());
	}

}
