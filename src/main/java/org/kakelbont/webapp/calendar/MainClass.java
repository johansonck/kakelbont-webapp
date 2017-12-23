package org.kakelbont.webapp.calendar;

import be.sonck.xml.XmlElement;
import org.kakelbont.webapp.calendar.generator.CalendarCodeGenerator;
import org.kakelbont.webapp.calendar.reader.LocalDateFileReader;

import java.util.List;

public class MainClass {
	
	public static void main(String[] args) throws Exception {
		LocalDateFileReader reader = new LocalDateFileReader();
		
		List<XmlElement> list = new CalendarCodeGenerator().generate(2017, 12,
				2019, 1, reader.readFile("/feestdagen.txt"),
				reader.readFile("/verlof.txt"));
		
		for (XmlElement element : list) {
			System.out.println(element.prettyPrint(4));
		}
	}
}
