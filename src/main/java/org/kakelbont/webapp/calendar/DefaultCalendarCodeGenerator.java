package org.kakelbont.webapp.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import be.sonck.xml.XmlElement;

public class DefaultCalendarCodeGenerator implements CalendarCodeGenerator {

	private YearCodeGenerator yearCodeGenerator = new DefaultYearCodeGenerator();

	public void setYearCodeGenerator(YearCodeGenerator yearCodeGenerator) {
		this.yearCodeGenerator = yearCodeGenerator;
	}

	public List<XmlElement> generate(int firstYear, int firstMonth, int lastYear,
			int lastMonth, List<Calendar> feestdagen,
			List<Calendar> sluitingsdagen) {
		
		List<XmlElement> list = new ArrayList<XmlElement>();
		
		int year = firstYear;
		do {
			list.add(createBookmarkElement(year++));
		} while (year <= lastYear);
		
		if (firstYear == lastYear) {
			list.addAll(yearCodeGenerator.generate(firstYear, firstMonth, lastMonth, feestdagen, sluitingsdagen));
		} else {
			list.addAll(yearCodeGenerator.generate(firstYear, firstMonth, Calendar.DECEMBER, feestdagen, sluitingsdagen));
			
			year = firstYear + 1;
			while (year < lastYear) {
				list.addAll(yearCodeGenerator.generate(year, Calendar.JANUARY, Calendar.DECEMBER, feestdagen, sluitingsdagen));
				year++;
			}
			
			list.addAll(yearCodeGenerator.generate(lastYear, Calendar.JANUARY, lastMonth, feestdagen, sluitingsdagen));
		}
		
		return list;
	}

	private XmlElement createBookmarkElement(int year) {
		XmlElement xmlElement = new XmlElement("p");
		XmlElement hrefElement = new XmlElement(xmlElement, "a", String.valueOf(year));
		hrefElement.addAttribute("href", "#" + String.valueOf(year));
		
		return xmlElement;
	}
}
