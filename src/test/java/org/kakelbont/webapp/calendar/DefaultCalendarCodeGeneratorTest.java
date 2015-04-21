package org.kakelbont.webapp.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import be.sonck.xml.XmlElement;

public class DefaultCalendarCodeGeneratorTest {

	@Test
	public void test() throws Exception {
		YearCodeGenerator mock = EasyMock.createStrictMock(YearCodeGenerator.class);
		expect(mock, 2009, Calendar.MARCH, Calendar.DECEMBER);
		expect(mock, 2010, Calendar.JANUARY, Calendar.DECEMBER);
		expect(mock, 2011, Calendar.JANUARY, Calendar.OCTOBER);
		EasyMock.replay(mock);

		DefaultCalendarCodeGenerator calendarCodeGenerator = new DefaultCalendarCodeGenerator();
		calendarCodeGenerator.setYearCodeGenerator(mock);
		List<XmlElement> list = calendarCodeGenerator.generate(
				2009, Calendar.MARCH, 
				2011, Calendar.OCTOBER,
				new ArrayList<Calendar>(), new ArrayList<Calendar>());

		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() > 0);
		
		XmlElement actual = new XmlElement("div");
		for (XmlElement xmlElement : list) {
			actual.addChild(xmlElement);
		}

		XmlElement expected = new XmlElement("div");
		expected.addChild(createBookmarkElement(2009));
		expected.addChild(createBookmarkElement(2010));
		expected.addChild(createBookmarkElement(2011));
		expected.addChild(new XmlElement("p", String.valueOf(2009)));
		expected.addChild(new XmlElement("p", String.valueOf(2010)));
		expected.addChild(new XmlElement("p", String.valueOf(2011)));

		Assert.assertEquals(expected.toString(false), actual.toString(false));
		
		EasyMock.verify(mock);
	}

	private XmlElement createBookmarkElement(int year) {
		XmlElement xmlElement = new XmlElement("p");
		XmlElement hrefElement = new XmlElement(xmlElement, "a", String.valueOf(year));
		hrefElement.addAttribute("href", "#" + String.valueOf(year));
		
		return xmlElement;
	}

	private void expect(YearCodeGenerator mock, int year, int firstMonth, int lastMonth) {
		List<XmlElement> list = new ArrayList<XmlElement>();
		list.add(new XmlElement("p", String.valueOf(year)));
		
		EasyMock.expect(mock.generate(
					EasyMock.eq(year), EasyMock.eq(firstMonth), EasyMock.eq(lastMonth), 
					EasyMock.eq(new ArrayList<Calendar>()), EasyMock.eq(new ArrayList<Calendar>())))
				.andReturn(list);
	}
}
