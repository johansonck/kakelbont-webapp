package org.kakelbont.webapp.feestdag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.kakelbont.webapp.calendar.StringToCalendarConverter;
import org.kakelbont.webapp.feestdag.Feestdag.Name;

public class FeestdagFileLineReader {

	private BufferedReader bufferedReader;
	
	private final StringToCalendarConverter converter;
	private final String resource;


	public FeestdagFileLineReader(String resource) {
		this.resource = resource;
		this.converter = new StringToCalendarConverter();
	}

	public List<Feestdag> readLine() throws IOException {
		String line = getBufferedReader().readLine();
		if (line == null || line.trim().length() == 0) return null;
		
		return convertLine(line);
	}

	public void close() {
		if (bufferedReader != null) {
			IOUtils.closeQuietly(bufferedReader);
		}
	}

	private List<Feestdag> convertLine(String line) {
		String[] split = line.split(";");
		if (split.length == 5) {
			return createDatesFromShortList(split);
		} else if (split.length == 13) {
			return createDatesFromLongList(split);
		} else {
			throw new IllegalArgumentException("the line should contain either 5 or 13 dates separated by ';'");
		}
	}

	private List<Feestdag> createDatesFromLongList(String[] strings) {
		List<Feestdag> list = new ArrayList<>(13);
		
		list.add(new Feestdag(Name.NIEUWJAAR, converter.toCalendar(strings[0])));
		list.add(new Feestdag(Name.PASEN, converter.toCalendar(strings[1])));
		list.add(new Feestdag(Name.PAASMAANDAG, converter.toCalendar(strings[2])));
		list.add(new Feestdag(Name.FEEST_VAN_DE_ARBEID, converter.toCalendar(strings[3])));
		list.add(new Feestdag(Name.O_L_H_HEMELVAART, converter.toCalendar(strings[4])));
		list.add(new Feestdag(Name.PINKSTEREN, converter.toCalendar(strings[5])));
		list.add(new Feestdag(Name.PINKSTERMAANDAG, converter.toCalendar(strings[6])));
		list.add(new Feestdag(Name.FEEST_VAN_DE_VLAAMSE_GEMEENSCHAP, converter.toCalendar(strings[7])));
		list.add(new Feestdag(Name.NATIONALE_FEESTDAG, converter.toCalendar(strings[8])));
		list.add(new Feestdag(Name.O_L_V_HEMELVAART, converter.toCalendar(strings[9])));
		list.add(new Feestdag(Name.ALLERHEILIGEN, converter.toCalendar(strings[10])));
		list.add(new Feestdag(Name.WAPENSTILSTAND, converter.toCalendar(strings[11])));
		list.add(new Feestdag(Name.KERSTMIS, converter.toCalendar(strings[12])));
		
		return list;
	}

	private List<Feestdag> createDatesFromShortList(String[] strings) {
		List<Feestdag> list = new ArrayList<>(13);
		
		int year = converter.toCalendar(strings[0]).get(Calendar.YEAR);
		
		list.add(new VasteFeestdag(Name.NIEUWJAAR, year));
		list.add(new Feestdag(Name.PASEN, converter.toCalendar(strings[0])));
		list.add(new Feestdag(Name.PAASMAANDAG, converter.toCalendar(strings[1])));
		list.add(new VasteFeestdag(Name.FEEST_VAN_DE_ARBEID, year));
		list.add(new Feestdag(Name.O_L_H_HEMELVAART, converter.toCalendar(strings[2])));
		list.add(new Feestdag(Name.PINKSTEREN, converter.toCalendar(strings[3])));
		list.add(new Feestdag(Name.PINKSTERMAANDAG, converter.toCalendar(strings[4])));
		list.add(new VasteFeestdag(Name.FEEST_VAN_DE_VLAAMSE_GEMEENSCHAP, year));
		list.add(new VasteFeestdag(Name.NATIONALE_FEESTDAG, year));
		list.add(new VasteFeestdag(Name.O_L_V_HEMELVAART, year));
		list.add(new VasteFeestdag(Name.ALLERHEILIGEN, year));
		list.add(new VasteFeestdag(Name.WAPENSTILSTAND, year));
		list.add(new VasteFeestdag(Name.KERSTMIS, year));
		
		return list;
	}

	private BufferedReader getBufferedReader() throws IOException {
		if (bufferedReader == null) {
			InputStream inputStream = FeestdagFileLineReader.class.getResource(resource).openStream();
			InputStreamReader reader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(reader);
		}
		
		return bufferedReader;
	}
}
