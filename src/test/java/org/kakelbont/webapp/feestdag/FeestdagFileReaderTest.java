package org.kakelbont.webapp.feestdag;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.kakelbont.webapp.feestdag.Feestdag.Name;

public class FeestdagFileReaderTest {

	@Test
	public void test() throws Exception {
		List<List<Feestdag>> rootList = new FeestdagFileReader().readFile("/test-feestdagen-overzicht.txt");
		
		Assert.assertNotNull(rootList);
		Assert.assertEquals(2, rootList.size());
		
		List<Feestdag> list = rootList.get(0);
		
		Assert.assertNotNull(list);
		Assert.assertEquals(13, list.size());
		
		assertEquals(list.get(0), Name.NIEUWJAAR, new GregorianCalendar(2011, Calendar.JANUARY, 1));
		assertEquals(list.get(1), Name.PASEN, new GregorianCalendar(2011, Calendar.APRIL, 24));
		assertEquals(list.get(2), Name.PAASMAANDAG, new GregorianCalendar(2011, Calendar.APRIL, 25));
		assertEquals(list.get(3), Name.FEEST_VAN_DE_ARBEID, new GregorianCalendar(2011, Calendar.MAY, 1));
		assertEquals(list.get(4), Name.O_L_H_HEMELVAART, new GregorianCalendar(2011, Calendar.MAY, 2));
		assertEquals(list.get(5), Name.PINKSTEREN, new GregorianCalendar(2011, Calendar.JUNE, 12));
		assertEquals(list.get(6), Name.PINKSTERMAANDAG, new GregorianCalendar(2011, Calendar.JUNE, 13));
		assertEquals(list.get(7), Name.FEEST_VAN_DE_VLAAMSE_GEMEENSCHAP, new GregorianCalendar(2011, Calendar.JULY, 11));
		assertEquals(list.get(8), Name.NATIONALE_FEESTDAG, new GregorianCalendar(2011, Calendar.JULY, 21));
		assertEquals(list.get(9), Name.O_L_V_HEMELVAART, new GregorianCalendar(2011, Calendar.AUGUST, 15));
		assertEquals(list.get(10), Name.ALLERHEILIGEN, new GregorianCalendar(2011, Calendar.NOVEMBER, 1));
		assertEquals(list.get(11), Name.WAPENSTILSTAND, new GregorianCalendar(2011, Calendar.NOVEMBER, 11));
		assertEquals(list.get(12), Name.KERSTMIS, new GregorianCalendar(2011, Calendar.DECEMBER, 25));
		
		list = rootList.get(1);
		
		Assert.assertNotNull(list);
		Assert.assertEquals(13, list.size());
		
		assertEquals(list.get(0), Name.NIEUWJAAR, new GregorianCalendar(2012, Calendar.JANUARY, 2));
		assertEquals(list.get(1), Name.PASEN, new GregorianCalendar(2012, Calendar.APRIL, 8));
		assertEquals(list.get(2), Name.PAASMAANDAG, new GregorianCalendar(2012, Calendar.APRIL, 9));
		assertEquals(list.get(3), Name.FEEST_VAN_DE_ARBEID, new GregorianCalendar(2012, Calendar.MAY, 1));
		assertEquals(list.get(4), Name.O_L_H_HEMELVAART, new GregorianCalendar(2012, Calendar.MAY, 17));
		assertEquals(list.get(5), Name.PINKSTEREN, new GregorianCalendar(2012, Calendar.MAY, 27));
		assertEquals(list.get(6), Name.PINKSTERMAANDAG, new GregorianCalendar(2012, Calendar.MAY, 28));
		assertEquals(list.get(7), Name.FEEST_VAN_DE_VLAAMSE_GEMEENSCHAP, new GregorianCalendar(2012, Calendar.JULY, 11));
		assertEquals(list.get(8), Name.NATIONALE_FEESTDAG, new GregorianCalendar(2012, Calendar.JULY, 21));
		assertEquals(list.get(9), Name.O_L_V_HEMELVAART, new GregorianCalendar(2012, Calendar.AUGUST, 15));
		assertEquals(list.get(10), Name.ALLERHEILIGEN, new GregorianCalendar(2012, Calendar.NOVEMBER, 1));
		assertEquals(list.get(11), Name.WAPENSTILSTAND, new GregorianCalendar(2012, Calendar.NOVEMBER, 11));
		assertEquals(list.get(12), Name.KERSTMIS, new GregorianCalendar(2012, Calendar.DECEMBER, 25));
	}

	private void assertEquals(Feestdag feestdag, Name name, Calendar calendar) {
		Assert.assertEquals(name, feestdag.getName());
		Assert.assertEquals(calendar, feestdag.getCalendar());
	}
}
