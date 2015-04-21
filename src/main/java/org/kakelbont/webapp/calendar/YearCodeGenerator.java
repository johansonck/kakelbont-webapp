package org.kakelbont.webapp.calendar;

import java.util.Calendar;
import java.util.List;

import be.sonck.xml.XmlElement;

public interface YearCodeGenerator {
	
	List<XmlElement> generate(int year, int firstMonth, int lastMonth,
			List<Calendar> feestdagen, List<Calendar> sluitingsdagen);
}
