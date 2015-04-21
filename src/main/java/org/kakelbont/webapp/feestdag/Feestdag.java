package org.kakelbont.webapp.feestdag;

import java.util.Calendar;

public class Feestdag {

	public enum Name {
		NIEUWJAAR("nieuwjaar"),
		PASEN("pasen"),
		PAASMAANDAG("paasmaandag"),
		FEEST_VAN_DE_ARBEID("feest van de arbeid"),
		O_L_H_HEMELVAART("O.-L.-H.-Hemelvaart"),
		PINKSTEREN("pinksteren"),
		PINKSTERMAANDAG("pinkstermaandag"),
		FEEST_VAN_DE_VLAAMSE_GEMEENSCHAP("feest van de Vlaamse Gemeenschap"),
		NATIONALE_FEESTDAG("nationale feestdag"),
		O_L_V_HEMELVAART("O.-L.-V.-Hemelvaart"),
		ALLERHEILIGEN("allerheiligen"),
		WAPENSTILSTAND("wapenstilstand"),
		KERSTMIS("kerstmis");

		private final String string;

		private Name(String string) {
			this.string = string;
		}
		
		public String toString() {
			return string;
		}
	}
	
	private final Name name;
	private final Calendar calendar;
	
	public Feestdag(Name name, Calendar calendar) {
		this.name = name;
		this.calendar = calendar;
	}

	public Name getName() {
		return name;
	}

	public Calendar getCalendar() {
		return calendar;
	}
}
