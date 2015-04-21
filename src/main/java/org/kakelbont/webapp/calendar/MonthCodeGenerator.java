package org.kakelbont.webapp.calendar;

import java.util.Calendar;
import java.util.List;

import be.sonck.xml.XmlElement;

public interface MonthCodeGenerator {

	public abstract XmlElement generate(int year, int month,
			List<Calendar> feestdagen, List<Calendar> sluitingsdagen);

}