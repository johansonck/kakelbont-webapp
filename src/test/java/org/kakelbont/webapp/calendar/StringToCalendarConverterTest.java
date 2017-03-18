package org.kakelbont.webapp.calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;
import org.kakelbont.webapp.calendar.converter.StringToCalendarConverter;

public class StringToCalendarConverterTest {

	@Test
	public void testToCalendar() throws Exception {
		Calendar calendar = new StringToCalendarConverter().toCalendar("2011-08-28");
		
		Assert.assertEquals(2011, calendar.get(Calendar.YEAR));
		Assert.assertEquals(Calendar.AUGUST, calendar.get(Calendar.MONTH));
		Assert.assertEquals(28, calendar.get(Calendar.DAY_OF_MONTH));
		Assert.assertEquals(Calendar.SUNDAY, calendar.get(Calendar.DAY_OF_WEEK));
	}
	
	@Test
	public void testToString() throws Exception {
		String string = new StringToCalendarConverter().toString(new GregorianCalendar(2011, Calendar.AUGUST, 28));
		
		Assert.assertEquals("2011-08-28", string);
	}

}
