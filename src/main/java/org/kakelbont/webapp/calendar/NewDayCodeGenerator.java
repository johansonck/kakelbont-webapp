package org.kakelbont.webapp.calendar;

import be.sonck.xml.XmlElement;
import be.sonck.xml.value.StringValue;
import be.sonck.xml.value.XmlValue;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

public class NewDayCodeGenerator {
	
	public enum Style {
		WEEKEND("weekend"), FEESTDAG("feestdag"), SLUITINGSDAG("gesloten");
		
		private final String string;

		Style(String string) {
			this.string = string;
		}

		@Override
		public String toString() {
			return string;
		}
	}

	public XmlElement generateEmpty() {
		return generateEmpty(null);
	}
	
	public XmlElement generateEmpty(Style style) {
		return generate(null, style);
	}

	public XmlElement generate(LocalDate day, Style style) {
		XmlElement xmlElement = generate(day);

		if (style != null) {
			xmlElement.addAttribute("class", style.toString());
		}

		return xmlElement;
	}

	public XmlElement generate(LocalDate day) {
		XmlValue value = XmlValue.NON_BLANK_SPACE;

		if (day != null) {
			value = new StringValue(String.valueOf(day.getDayOfMonth()));
		}

		XmlElement xmlElement = new XmlElement("li", value);

		if (isWeekend(day)) {
			xmlElement.addAttribute("class", Style.WEEKEND.toString());
		}

		return xmlElement;
	}

	private boolean isWeekend(LocalDate day) {
		if (day == null) return false;

		DayOfWeek dayOfTheWeek = day.getDayOfWeek();

		return (dayOfTheWeek == SATURDAY || dayOfTheWeek == SUNDAY);
	}
}
