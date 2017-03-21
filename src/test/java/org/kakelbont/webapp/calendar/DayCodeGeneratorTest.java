package org.kakelbont.webapp.calendar;

import be.sonck.xml.XmlElement;
import org.junit.Assert;
import org.junit.Test;
import org.kakelbont.webapp.calendar.generator.DayCodeGenerator;
import org.kakelbont.webapp.calendar.generator.DayCodeGenerator.Style;

import java.time.LocalDate;

import static java.util.Arrays.asList;

/**
 * Created by johansonck on 14/03/2017.
 */
public class DayCodeGeneratorTest {

    @Test
    public void testEmpty() throws Exception {
        XmlElement actual = new DayCodeGenerator().generateEmpty();

        Assert.assertNotNull(actual);
        Assert.assertEquals("<li>&nbsp;</li>", actual.toString(false));
    }

    @Test
    public void testEmptyDayWithStyle() throws Exception {
        XmlElement actual = new DayCodeGenerator().generateEmpty(Style.WEEKEND);

        Assert.assertNotNull(actual);
        Assert.assertEquals("<li class=\"weekend\">&nbsp;</li>", actual.toString(false));
    }

    @Test
    public void testNormalDay() throws Exception {
        XmlElement actual = new DayCodeGenerator().generate(LocalDate.of(2011, 8, 29));

        Assert.assertNotNull(actual);
        Assert.assertEquals("<li>29</li>", actual.toString(false));
    }

    @Test
    public void testFeestdag() throws Exception {
        XmlElement actual = new DayCodeGenerator().generate(LocalDate.of(2016, 4, 21), Style.FEESTDAG);

        Assert.assertNotNull(actual);
        Assert.assertEquals("<li class=\"feestdag\">21</li>", actual.toString(false));
    }

    @Test
    public void testTweeStijlen() throws Exception {
        XmlElement actual = new DayCodeGenerator().generate(LocalDate.of(2016, 4, 21), asList(Style.WEEKEND, Style.ANDERE_MAAND));

        Assert.assertNotNull(actual);
        Assert.assertEquals("<li class=\"weekend andereMaand\">21</li>", actual.toString(false));
    }

    @Test
    public void testSluitingsdag() throws Exception {
        XmlElement actual = new DayCodeGenerator().generate(LocalDate.of(2016, 4, 21), Style.SLUITINGSDAG);

        Assert.assertNotNull(actual);
        Assert.assertEquals("<li class=\"gesloten\">21</li>", actual.toString(false));
    }
}