package org.kakelbont.webapp.calendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

import org.apache.commons.io.IOUtils;

public class CalendarFileLineReader {

	private BufferedReader bufferedReader;
	
	private final StringToCalendarConverter converter;
	private final String resource;


	public CalendarFileLineReader(String resource) {
		this.resource = resource;
		this.converter = new StringToCalendarConverter();
	}

	public Calendar readLine() throws IOException {
		String line = getBufferedReader().readLine();
		if (line == null || line.trim().length() == 0) return null;
		
		return converter.toCalendar(line);
	}

	private BufferedReader getBufferedReader() throws IOException {
		if (bufferedReader == null) {
			InputStream inputStream = CalendarFileLineReader.class.getResource(resource).openStream();
			InputStreamReader reader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(reader);
		}
		
		return bufferedReader;
	}
	
	public void close() {
		if (bufferedReader != null) {
			IOUtils.closeQuietly(bufferedReader);
		}
	}
}
