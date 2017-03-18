package org.kakelbont.webapp.calendar;

import be.sonck.xml.XmlElement;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DefaultMonthCodeGenerator implements MonthCodeGenerator {

	private final DefaultWeekCodeGenerator weekCodeGenerator = new DefaultWeekCodeGenerator();
	
	public XmlElement generate(int year, int month, List<Calendar> feestdagen, List<Calendar> sluitingsdagen) {
		XmlElement rootElement = new XmlElement("div");
		rootElement.addAttribute("class", "calendar");
		
		XmlElement monthElement = generateStaticElements(year, month, rootElement);
		
		addWeekElements(monthElement, year, month, feestdagen, sluitingsdagen);
		
		return rootElement;
	}

	private XmlElement generateStaticElements(int year, int month, XmlElement rootElement) {
		new XmlElement(rootElement, "p", translateMonth(month) + " " + year);
		
		XmlElement tableBodyElement = new XmlElement(new XmlElement(rootElement, "table"), "tbody");
		generateHeaderRow(tableBodyElement);
		
		return tableBodyElement;
	}

	private void generateHeaderRow(XmlElement tableBodyElement) {
		XmlElement tableRowElement = new XmlElement(tableBodyElement, "tr");
		
		new XmlElement(tableRowElement, "th", "ma");
		new XmlElement(tableRowElement, "th", "di");
		new XmlElement(tableRowElement, "th", "wo");
		new XmlElement(tableRowElement, "th", "do");
		new XmlElement(tableRowElement, "th", "vr");
		new XmlElement(tableRowElement, "th", "za");
		new XmlElement(tableRowElement, "th", "zo");
	}

	private void addWeekElements(XmlElement monthElement, int year, int month,
			List<Calendar> feestdagen, List<Calendar> sluitingsdagen) {
		
		Calendar firstDayOfTheMonth = new GregorianCalendar(year, month, 1);
		
		monthElement.addChild(weekCodeGenerator.generate(firstDayOfTheMonth, feestdagen, sluitingsdagen));
		
		Calendar nextMonday = findNextMonday(firstDayOfTheMonth);
		while (nextMonday != null) {
			monthElement.addChild(weekCodeGenerator.generate(nextMonday, feestdagen, sluitingsdagen));
			nextMonday = findNextMonday(nextMonday);
		}
	}

	private Calendar findNextMonday(Calendar calendar) {
		Calendar nextMonday = (Calendar) calendar.clone();
		int month = nextMonday.get(Calendar.MONTH);
		
		if (nextMonday.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
			nextMonday.add(Calendar.DAY_OF_MONTH, 7);
		} else while (nextMonday.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			nextMonday.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		if (nextMonday.get(Calendar.MONTH) == month) {
			return nextMonday;
		} else {
			return null;
		}
	}

	private String translateMonth(int month) {
		switch (month) {
		case Calendar.JANUARY: return "Januari";
		case Calendar.FEBRUARY: return "Februari";
		case Calendar.MARCH: return "Maart";
		case Calendar.APRIL: return "April";
		case Calendar.MAY: return "Mei";
		case Calendar.JUNE: return "Juni";
		case Calendar.JULY: return "Juli";
		case Calendar.AUGUST: return "Augustus";
		case Calendar.SEPTEMBER: return "September";
		case Calendar.OCTOBER: return "Oktober";
		case Calendar.NOVEMBER: return "November";
		case Calendar.DECEMBER: return "December";
		default: return null;
		}
	}
}
