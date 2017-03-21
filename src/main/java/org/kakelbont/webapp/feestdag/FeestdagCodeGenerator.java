package org.kakelbont.webapp.feestdag;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import be.sonck.xml.XmlElement;

public class FeestdagCodeGenerator {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE d MMMMM", new Locale("nl", "BE"));
	
	public XmlElement generate(List<List<Feestdag>> feestdagen) {
		assert feestdagen != null;
		assert feestdagen.size() > 0;
		
		XmlElement divElement = new XmlElement("div");
		divElement.setAttribute("class", "feestdagen");
		
		XmlElement tableElement = new XmlElement(divElement, "table");
		XmlElement tbodyElement = new XmlElement(tableElement, "tbody");
		
		tbodyElement.addChild(createTableHeader(feestdagen));
		
		for (int i = 0; i < 13; i++) {
			tbodyElement.addChild(createFeestdagRow(feestdagen, i));
		}
		
		return divElement;
	}

	private XmlElement createFeestdagRow(List<List<Feestdag>> feestdagen, int i) {
		XmlElement trElement = new XmlElement("tr");
		
		Feestdag feestdag = feestdagen.get(0).get(i);
		
		new XmlElement(trElement, "td", feestdag.getName().toString());
		
		for (List<Feestdag> jaar : feestdagen) {
			new XmlElement(trElement, "td", DATE_FORMAT.format(jaar.get(i).getCalendar().getTime()));
		}
		
		return trElement;
	}

	private XmlElement createTableHeader(List<List<Feestdag>> feestdagen) {
		XmlElement trElement = new XmlElement("tr");
		
		new XmlElement(trElement, "th");
		
		for (List<Feestdag> list : feestdagen) {
			int year = list.get(0).getCalendar().get(Calendar.YEAR);
			new XmlElement(trElement, "th", String.valueOf(year));
		}
		
		return trElement;
	}
}
