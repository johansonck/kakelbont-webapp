package org.kakelbont.webapp.feestdag;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class VasteFeestdag extends Feestdag {

	public VasteFeestdag(Name name, int year) {
		super(name, determineCalendar(name, year));
	}

	private static Calendar determineCalendar(Name name, int year) {
		switch (name) {
		case NIEUWJAAR : return new GregorianCalendar(year, Calendar.JANUARY, 1);
		case FEEST_VAN_DE_ARBEID : return new GregorianCalendar(year, Calendar.MAY, 1);
		case FEEST_VAN_DE_VLAAMSE_GEMEENSCHAP : return new GregorianCalendar(year, Calendar.JULY, 11);
		case NATIONALE_FEESTDAG : return new GregorianCalendar(year, Calendar.JULY, 21);
		case O_L_V_HEMELVAART : return new GregorianCalendar(year, Calendar.AUGUST, 15);
		case ALLERHEILIGEN : return new GregorianCalendar(year, Calendar.NOVEMBER, 1);
		case WAPENSTILSTAND : return new GregorianCalendar(year, Calendar.NOVEMBER, 11);
		case KERSTMIS : return new GregorianCalendar(year, Calendar.DECEMBER, 25);
		default:
			throw new IllegalArgumentException("No fixed date available for " + name);
		}
	}
}
