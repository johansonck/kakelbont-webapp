package org.kakelbont.webapp.calendar;

import java.util.Calendar;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import be.sonck.xml.XmlElement;

public class DefaultMonthCodeGeneratorTest {

	@Test
	public void test() throws Exception {
		CalendarFileReader reader = new CalendarFileReader();
		
		XmlElement actual = new DefaultMonthCodeGenerator().generate(2010, Calendar.NOVEMBER, 
				reader.readFile("/test-kalender-november2010-feestdagen.txt"), 
				reader.readFile("/test-kalender-november2010-sluitingsdagen.txt"));
		
		Assert.assertNotNull(actual);
		
		String expected = IOUtils.toString(DefaultMonthCodeGeneratorTest.class.getResourceAsStream("/test-kalender-november2010.txt"));
		Assert.assertEquals(expected, actual.toString(false));
	}
}
