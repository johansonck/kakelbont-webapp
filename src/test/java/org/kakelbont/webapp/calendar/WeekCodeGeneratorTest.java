package org.kakelbont.webapp.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import be.sonck.xml.XmlElement;

public class WeekCodeGeneratorTest {

	@Test
	public void test() throws Exception {
		XmlElement tableRow = new XmlElement("tr");
		
		new XmlElement(tableRow, "td", "8");
		new XmlElement(tableRow, "td", "9");
		new XmlElement(tableRow, "td", "10");
		new XmlElement(tableRow, "td", "11");
		new XmlElement(tableRow, "td", "12");
		
		XmlElement tableCell = new XmlElement(tableRow, "td", "13");
		tableCell.addAttribute("class", "we");
		
		tableCell = new XmlElement(tableRow, "td", "14");
		tableCell.addAttribute("class", "we");
		
		XmlElement actual = new WeekCodeGenerator().generate(new GregorianCalendar(2011, Calendar.AUGUST, 10), null, null);
		
		Assert.assertEquals(tableRow.toString(false), actual.toString(false));
	}
	
	@Test
	public void testEmptyDaysBefore() throws Exception {
		XmlElement tableRow = new XmlElement("tr");
		
		new XmlElement(tableRow, "td");
		new XmlElement(tableRow, "td");
		new XmlElement(tableRow, "td");
		new XmlElement(tableRow, "td", "1");
		new XmlElement(tableRow, "td", "2");
		
		XmlElement tableCell = new XmlElement(tableRow, "td", "3");
		tableCell.addAttribute("class", "we");
		
		tableCell = new XmlElement(tableRow, "td", "4");
		tableCell.addAttribute("class", "we");
		
		XmlElement actual = new WeekCodeGenerator().generate(new GregorianCalendar(2011, Calendar.SEPTEMBER, 2), null, null);
		
		Assert.assertEquals(tableRow.toString(false), actual.toString(false));
	}
	
	@Test
	public void testEmptyDaysBefore2() throws Exception {
		XmlElement tableRow = new XmlElement("tr");
		
		new XmlElement(tableRow, "td");
		new XmlElement(tableRow, "td");
		new XmlElement(tableRow, "td");
		new XmlElement(tableRow, "td");
		new XmlElement(tableRow, "td");
		
		XmlElement tableCell = new XmlElement(tableRow, "td");
		tableCell.addAttribute("class", "we");
		
		tableCell = new XmlElement(tableRow, "td", "1");
		tableCell.addAttribute("class", "we");
		
		XmlElement actual = new WeekCodeGenerator().generate(new GregorianCalendar(2011, Calendar.MAY, 1), null, null);
		
		Assert.assertEquals(tableRow.toString(false), actual.toString(false));
	}
	
	@Test
	public void testEmptyDaysAfter() throws Exception {
		XmlElement tableRow = new XmlElement("tr");
		
		new XmlElement(tableRow, "td", "31");
		new XmlElement(tableRow, "td");
		new XmlElement(tableRow, "td");
		new XmlElement(tableRow, "td");
		new XmlElement(tableRow, "td");
		
		XmlElement tableCell = new XmlElement(tableRow, "td");
		tableCell.addAttribute("class", "we");
		
		tableCell = new XmlElement(tableRow, "td");
		tableCell.addAttribute("class", "we");
		
		XmlElement actual = new WeekCodeGenerator().generate(new GregorianCalendar(2011, Calendar.OCTOBER, 31), null, null);
		
		Assert.assertEquals(tableRow.toString(false), actual.toString(false));
	}

	@Test
	public void testHolidays() throws Exception {
		XmlElement tableRow = new XmlElement("tr");
		
		XmlElement tableCell = new XmlElement(tableRow, "td", "8");
		tableCell.addAttribute("class", "verlof");
		
		tableCell = new XmlElement(tableRow, "td", "9");
		tableCell.addAttribute("class", "verlof");
		
		tableCell = new XmlElement(tableRow, "td", "10");
		tableCell.addAttribute("class", "feest");
		
		tableCell = new XmlElement(tableRow, "td", "11");
		tableCell.addAttribute("class", "feest");
		
		new XmlElement(tableRow, "td", "12");
		
		tableCell = new XmlElement(tableRow, "td", "13");
		tableCell.addAttribute("class", "we");
		
		tableCell = new XmlElement(tableRow, "td", "14");
		tableCell.addAttribute("class", "we");
		
		List<Calendar> sluitingsdagen = new ArrayList<Calendar>();
		sluitingsdagen.add(new GregorianCalendar(2011, Calendar.AUGUST, 8));
		sluitingsdagen.add(new GregorianCalendar(2011, Calendar.AUGUST, 9));
		
		List<Calendar> feestdagen = new ArrayList<Calendar>();
		feestdagen.add(new GregorianCalendar(2011, Calendar.AUGUST, 10));
		feestdagen.add(new GregorianCalendar(2011, Calendar.AUGUST, 11));
		
		XmlElement actual = new WeekCodeGenerator().generate(
				new GregorianCalendar(2011, Calendar.AUGUST, 10),
				feestdagen, sluitingsdagen);
		
		Assert.assertEquals(tableRow.toString(false), actual.toString(false));
	}
}
