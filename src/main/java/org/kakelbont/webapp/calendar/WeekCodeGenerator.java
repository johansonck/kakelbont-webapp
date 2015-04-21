package org.kakelbont.webapp.calendar;

import java.util.Calendar;
import java.util.List;

import org.kakelbont.webapp.calendar.DayCodeGenerator.Style;

import be.sonck.xml.XmlElement;

public class WeekCodeGenerator {

	private DayCodeGenerator dayCodeGenerator = new DayCodeGenerator();

	public XmlElement generate(Calendar calendar,
			List<Calendar> feestdagen, List<Calendar> sluitingsdagen) {
		
		Calendar firstDayOfTheWeek = findFirstDayOfTheWeek((Calendar) calendar.clone());

		XmlElement xmlElement = new XmlElement("tr");

		addEmptyDaysBefore(xmlElement, firstDayOfTheWeek.get(Calendar.DAY_OF_WEEK));
		addNonEmptyDays(xmlElement, firstDayOfTheWeek, feestdagen, sluitingsdagen);
		addEmptyDaysAfter(xmlElement, firstDayOfTheWeek.get(Calendar.DAY_OF_WEEK));

		return xmlElement;
	}

	private void addEmptyDaysAfter(XmlElement weekElement, int dayOfTheWeek) {
		int numberOfDays = calculateNumberOfDays(dayOfTheWeek);
		
		while (numberOfDays > 2) {
			weekElement.addChild(dayCodeGenerator.generateEmpty());
			numberOfDays--;
		}
		
		while (numberOfDays > 0) {
			weekElement.addChild(dayCodeGenerator.generateEmpty(Style.WEEKEND));
			numberOfDays--;
		}
	}

	private int calculateNumberOfDays(int dayOfTheWeek) {
		switch (dayOfTheWeek) {
		case Calendar.SUNDAY: return 1;
		case Calendar.SATURDAY: return 2;
		case Calendar.FRIDAY: return 3;
		case Calendar.THURSDAY: return 4;
		case Calendar.WEDNESDAY: return 5;
		case Calendar.TUESDAY: return 6;
		default: return 0;
		}
	}

	private void addNonEmptyDays(XmlElement weekElement, Calendar calendar, List<Calendar> feestdagen, List<Calendar> sluitingsdagen) {
		do {
			weekElement.addChild(dayCodeGenerator.generate(calendar, determineStyle(calendar, feestdagen, sluitingsdagen)));
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			
		} while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY
				&& calendar.get(Calendar.DAY_OF_MONTH) != 1);
	}

	private Style determineStyle(Calendar calendar, List<Calendar> feestdagen,
			List<Calendar> sluitingsdagen) {
		
		if (feestdagen != null && feestdagen.contains(calendar)) {
			return Style.FEESTDAG;
		}
		
		if (sluitingsdagen != null && sluitingsdagen.contains(calendar)) {
			return Style.SLUITINGSDAG;
		}
		
		return null;
	}

	private void addEmptyDaysBefore(XmlElement weekElement, int firstDayOfTheWeek) {
		if (firstDayOfTheWeek == Calendar.MONDAY) return;
		weekElement.addChild(dayCodeGenerator.generateEmpty());

		if (firstDayOfTheWeek == Calendar.TUESDAY) return;
		weekElement.addChild(dayCodeGenerator.generateEmpty());

		if (firstDayOfTheWeek == Calendar.WEDNESDAY) return;
		weekElement.addChild(dayCodeGenerator.generateEmpty());

		if (firstDayOfTheWeek == Calendar.THURSDAY) return;
		weekElement.addChild(dayCodeGenerator.generateEmpty());

		if (firstDayOfTheWeek == Calendar.FRIDAY) return;
		weekElement.addChild(dayCodeGenerator.generateEmpty());

		if (firstDayOfTheWeek == Calendar.SATURDAY) return;
		weekElement.addChild(dayCodeGenerator.generateEmpty(Style.WEEKEND));
	}

	private Calendar findFirstDayOfTheWeek(Calendar calendar) {
		if (calendar.get(Calendar.DAY_OF_MONTH) == 1
				|| calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {

			return calendar;
		}

		calendar.add(Calendar.DAY_OF_MONTH, -1);

		return findFirstDayOfTheWeek(calendar);
	}
}
