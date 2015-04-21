package org.kakelbont.webapp.calendar;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

public class CalendarFileLineReaderTest {

	@Test
	public void testRead() throws Exception {
		CalendarFileLineReader reader = new CalendarFileLineReader("/test-datums.txt");
		
		assertDate(reader.readLine(), 2011, Calendar.JULY, 19);
		assertDate(reader.readLine(), 2011, Calendar.JULY, 20);
		assertDate(reader.readLine(), 2011, Calendar.OCTOBER, 30);
		assertDate(reader.readLine(), 2011, Calendar.DECEMBER, 26);
		assertDate(reader.readLine(), 2011, Calendar.DECEMBER, 28);
		
		Assert.assertNull(reader.readLine());
		Assert.assertNull(reader.readLine());
		
		reader.close();
	}

	private void assertDate(Calendar calendar, int year, int month, int day) {
		Assert.assertEquals(year, calendar.get(Calendar.YEAR));
		Assert.assertEquals(month, calendar.get(Calendar.MONTH));
		Assert.assertEquals(day, calendar.get(Calendar.DAY_OF_MONTH));
	}
}
