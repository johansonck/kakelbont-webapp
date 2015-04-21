package org.kakelbont.webapp.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import be.sonck.xml.XmlElement;

public class DefaultYearCodeGeneratorTest {

	@Test
	public void test() throws Exception {
		MonthCodeGenerator mock = EasyMock.createMock(MonthCodeGenerator.class);
		expect(mock, Calendar.JANUARY, "Januari");
		expect(mock, Calendar.FEBRUARY, "Februari");
		expect(mock, Calendar.MARCH, "Maart");
		expect(mock, Calendar.APRIL, "April");
		expect(mock, Calendar.MAY, "Mei");
		EasyMock.replay(mock);

		DefaultYearCodeGenerator yearCodeGenerator = new DefaultYearCodeGenerator();
		yearCodeGenerator.setMonthCodeGenerator(mock);
		
		List<XmlElement> list = yearCodeGenerator.generate(2011, Calendar.JANUARY,
				Calendar.MAY, new ArrayList<Calendar>(), new ArrayList<Calendar>());

		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() > 0);

		XmlElement actual = new XmlElement("div");
		for (XmlElement xmlElement : list) {
			actual.addChild(xmlElement);
		}
		
		String expected = IOUtils.toString(
				DefaultMonthCodeGeneratorTest.class.getResourceAsStream("/test-2011.txt"));

		Assert.assertEquals(expected, actual.toString(false));
		
		EasyMock.verify(mock);
	}

	private void expect(MonthCodeGenerator mock, int month, String translation) {
		EasyMock.expect(
				mock.generate(EasyMock.eq(2011), EasyMock.eq(month), EasyMock
						.eq(new ArrayList<Calendar>()), EasyMock
						.eq(new ArrayList<Calendar>()))).andReturn(
				new XmlElement("p", translation + " 2011"));
	}
}
