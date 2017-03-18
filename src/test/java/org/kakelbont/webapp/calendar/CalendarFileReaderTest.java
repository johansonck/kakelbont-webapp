package org.kakelbont.webapp.calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.kakelbont.webapp.calendar.reader.CalendarFileReader;

public class CalendarFileReaderTest {

	@Test
	public void test() throws Exception {
		List<Calendar> list = new CalendarFileReader().readFile("/test-datums.txt");

		Assert.assertNotNull(list);
		Assert.assertEquals(5, list.size());
		
		Assert.assertTrue(list.contains(new GregorianCalendar(2011, Calendar.JULY, 19)));
		Assert.assertTrue(list.contains(new GregorianCalendar(2011, Calendar.JULY, 20)));
		Assert.assertTrue(list.contains(new GregorianCalendar(2011, Calendar.OCTOBER, 30)));
		Assert.assertTrue(list.contains(new GregorianCalendar(2011, Calendar.DECEMBER, 26)));
		Assert.assertTrue(list.contains(new GregorianCalendar(2011, Calendar.DECEMBER, 28)));
	}
}
