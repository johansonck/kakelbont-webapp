package org.kakelbont.webapp.calendar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarFileReader {

	public List<Calendar> readFile(String resource) throws IOException {
		List<Calendar> list = new ArrayList<Calendar>();
		
		CalendarFileLineReader lineReader = new CalendarFileLineReader(resource);
		Calendar calendar = lineReader.readLine();
		while (calendar != null) {
			list.add(calendar);
			calendar = lineReader.readLine();
		}
		
		return list;
	}
}
