package org.kakelbont.webapp.calendar;

import java.util.Calendar;

import be.sonck.xml.XmlElement;

public class DayCodeGenerator {
	
	public enum Style {
		WEEKEND("we"), FEESTDAG("feest"), SLUITINGSDAG("verlof");
		
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

	public XmlElement generate(Calendar calendar, Style style) {
		String value = null;
		
		if (calendar != null) {
			value = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		}
		
		XmlElement xmlElement = new XmlElement("td", value);
		
		if (isWeekend(calendar)) {
			xmlElement.addAttribute("class", Style.WEEKEND.toString());
		} else if (style != null) {
			xmlElement.addAttribute("class", style.toString());
		} 
		
		return xmlElement;
	}
	
	private boolean isWeekend(Calendar calendar) {
		if (calendar == null) return false;
		
		int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK);
		
		return (dayOfTheWeek == Calendar.SATURDAY || dayOfTheWeek == Calendar.SUNDAY);
	}
}
