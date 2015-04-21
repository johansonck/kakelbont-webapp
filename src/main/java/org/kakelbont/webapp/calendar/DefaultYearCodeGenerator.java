package org.kakelbont.webapp.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import be.sonck.xml.XmlElement;

public class DefaultYearCodeGenerator implements YearCodeGenerator {

	private MonthCodeGenerator monthCodeGenerator = new DefaultMonthCodeGenerator();

	public void setMonthCodeGenerator(MonthCodeGenerator monthCodeGenerator) {
		this.monthCodeGenerator = monthCodeGenerator;
	}

	public List<XmlElement> generate(int year, int firstMonth, int lastMonth,
			List<Calendar> feestdagen, List<Calendar> sluitingsdagen) {
		
		if (lastMonth < firstMonth) {
			throw new IllegalArgumentException("the last month must be equal or later than the first month");
		}
		
		List<XmlElement> list = new ArrayList<XmlElement>();
		
		list.add(new XmlElement("hr"));
		list.add(createTitleElement(year));
		list.add(monthCodeGenerator.generate(year, firstMonth, feestdagen, sluitingsdagen));
		
		if (firstMonth != lastMonth) {
			int counter = 1;
			int nextMonth = firstMonth;
			do {
				if (counter++ == 2) {
					list.add(createSeparator());
					counter = 1;
				}
				
				nextMonth = determineNextMonth(year, nextMonth);
				list.add(monthCodeGenerator.generate(year, nextMonth, feestdagen, sluitingsdagen));
				
			} while (nextMonth != lastMonth);
		}
		
		list.add(createSeparator());
		list.add(createBackToTop());

		return list;
	}

	private XmlElement createBackToTop() {
		XmlElement xmlElement = new XmlElement("p");
		XmlElement hrefElement = new XmlElement(xmlElement, "a", "terug naar boven");
		hrefElement.addAttribute("href", "#wrap");
		
		return xmlElement;
	}

	private XmlElement createSeparator() {
		XmlElement xmlElement = new XmlElement("div");
		xmlElement.addAttribute("style", "clear:both");
		return xmlElement;
	}

	private int determineNextMonth(int year, int month) {
		Calendar calendar = new GregorianCalendar(year, month, 1);
		calendar.add(Calendar.MONTH, 1);
		return calendar.get(Calendar.MONTH);
	}

	private XmlElement createTitleElement(int year) {
		XmlElement xmlElement = new XmlElement("p", String.valueOf(year));
		
		xmlElement.addAttribute("id", String.valueOf(year));
		xmlElement.addAttribute("class", "subtitle");
		
		return xmlElement;
	}
}
