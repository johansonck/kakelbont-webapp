package org.kakelbont.webapp.calendar;

import be.sonck.xml.XmlElement;

import java.util.Calendar;
import java.util.List;

public class MainClass {
	
	public static void main(String[] args) throws Exception {
		CalendarFileReader reader = new CalendarFileReader();
		
		List<XmlElement> list = new DefaultCalendarCodeGenerator().generate(2016, Calendar.JANUARY,
				2016, Calendar.DECEMBER, reader.readFile("/feestdagen.txt"),
				reader.readFile("/verlof.txt"));
		
		for (XmlElement element : list) {
			System.out.println(element.prettyPrint(4));
		}
	}
}
