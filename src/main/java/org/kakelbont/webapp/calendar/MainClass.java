package org.kakelbont.webapp.calendar;

import java.util.Calendar;
import java.util.List;

import be.sonck.xml.XmlElement;

public class MainClass {
	
	public static void main(String[] args) throws Exception {
		CalendarFileReader reader = new CalendarFileReader();
		
		List<XmlElement> list = new DefaultCalendarCodeGenerator().generate(2014, Calendar.JANUARY,
				2014, Calendar.DECEMBER, reader.readFile("/feestdagen.txt"),
				reader.readFile("/verlof.txt"));
		
		for (XmlElement element : list) {
			System.out.println(element.prettyPrint(4));
		}
	}
}
