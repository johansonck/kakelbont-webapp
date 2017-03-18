package org.kakelbont.webapp.calendar;

import be.sonck.xml.XmlElement;

import java.util.Calendar;
import java.util.List;

public interface MonthCodeGenerator {

	XmlElement generate(int year, int month,
			List<Calendar> feestdagen, List<Calendar> sluitingsdagen);

}