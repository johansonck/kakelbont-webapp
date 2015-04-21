package org.kakelbont.webapp.calendar;

import java.util.Calendar;
import java.util.List;

import be.sonck.xml.XmlElement;

public interface CalendarCodeGenerator {

	List<XmlElement> generate(int firstYear, int firstMonth, int lastYear, int lastMonth,
			List<Calendar> feestdagen, List<Calendar> sluitingsdagen);

}