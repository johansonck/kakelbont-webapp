package org.kakelbont.webapp.calendar;

import be.sonck.xml.XmlElement;
import org.junit.Assert;
import org.junit.Test;
import org.kakelbont.webapp.calendar.DefaultDayCodeGenerator.Style;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DefaultDayCodeGeneratorTest {

	@Test
	public void testEmpty() throws Exception {
		XmlElement actual = new DefaultDayCodeGenerator().generateEmpty();
		
		Assert.assertNotNull(actual);
		Assert.assertEquals("<td/>", actual.toString(false));
	}
	
	@Test
	public void testEmptyDayWithStyle() throws Exception {
		XmlElement actual = new DefaultDayCodeGenerator().generateEmpty(Style.WEEKEND);
		
		Assert.assertNotNull(actual);
		Assert.assertEquals("<td class=\"we\"/>", actual.toString(false));
	}
	
	@Test
	public void testNormalDay() throws Exception {
		Calendar calendar = new GregorianCalendar(2011, Calendar.AUGUST, 29);
		XmlElement actual = new DefaultDayCodeGenerator().generate(calendar, null);
		
		Assert.assertNotNull(actual);
		Assert.assertEquals("<td>29</td>", actual.toString(false));
	}
	
	@Test
	public void testNormalDayWithParent() throws Exception {
		Calendar calendar = new GregorianCalendar(2011, Calendar.AUGUST, 29);
		XmlElement actual = new DefaultDayCodeGenerator().generate(calendar, null);

		Assert.assertNotNull(actual);
		Assert.assertEquals("<td>29</td>", actual.toString(false));
	}
	
	@Test
	public void testWeekendDay() throws Exception {
		Calendar calendar = new GregorianCalendar(2011, Calendar.AUGUST, 28);
		XmlElement actual = new DefaultDayCodeGenerator().generate(calendar, null);
		
		Assert.assertNotNull(actual);
		Assert.assertEquals("<td class=\"we\">28</td>", actual.toString(false));
	}
	
	@Test
	public void testWeekendDay2() throws Exception {
		Calendar calendar = new GregorianCalendar(2011, Calendar.AUGUST, 28);
		XmlElement actual = new DefaultDayCodeGenerator().generate(calendar, null);
		
		Assert.assertNotNull(actual);
		Assert.assertEquals("<td class=\"we\">28</td>", actual.toString(false));
	}
	
	@Test
	public void testFeestdag() throws Exception {
		Calendar calendar = new GregorianCalendar(2015, Calendar.APRIL, 21);
		XmlElement actual = new DefaultDayCodeGenerator().generate(calendar, Style.FEESTDAG);
		
		Assert.assertNotNull(actual);
		Assert.assertEquals("<td class=\"feest\">21</td>", actual.toString(false));
	}
	
	@Test
	public void testSluitingsdag() throws Exception {
		Calendar calendar = new GregorianCalendar(2015, Calendar.APRIL, 21);
		XmlElement actual = new DefaultDayCodeGenerator().generate(calendar, Style.SLUITINGSDAG);
		
		Assert.assertNotNull(actual);
		Assert.assertEquals("<td class=\"verlof\">21</td>", actual.toString(false));
	}
}
