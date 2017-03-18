package org.kakelbont.webapp.calendar;

import be.sonck.xml.XmlElement;
import org.junit.Assert;
import org.junit.Test;
import org.kakelbont.webapp.calendar.NewDayCodeGenerator.Style;

import java.time.LocalDate;

/**
 * Created by johansonck on 14/03/2017.
 */
public class NewDayCodeGeneratorTest {

    @Test
    public void testEmpty() throws Exception {
        XmlElement actual = new NewDayCodeGenerator().generateEmpty();

        Assert.assertNotNull(actual);
        Assert.assertEquals("<li>&nbsp;</li>", actual.toString(false));
    }

    @Test
    public void testEmptyDayWithStyle() throws Exception {
        XmlElement actual = new NewDayCodeGenerator().generateEmpty(Style.WEEKEND);

        Assert.assertNotNull(actual);
        Assert.assertEquals("<li class=\"weekend\">&nbsp;</li>", actual.toString(false));
    }

    @Test
    public void testNormalDay() throws Exception {
        XmlElement actual = new NewDayCodeGenerator().generate(LocalDate.of(2011, 8, 29));

        Assert.assertNotNull(actual);
        Assert.assertEquals("<li>29</li>", actual.toString(false));
    }

    @Test
    public void testWeekendDay() throws Exception {
        XmlElement actual = new NewDayCodeGenerator().generate(LocalDate.of(2011, 8, 28));

        Assert.assertNotNull(actual);
        Assert.assertEquals("<li class=\"weekend\">28</li>", actual.toString(false));
    }

    @Test
    public void testFeestdag() throws Exception {
        XmlElement actual = new NewDayCodeGenerator().generate(LocalDate.of(2016, 4, 21), Style.FEESTDAG);

        Assert.assertNotNull(actual);
        Assert.assertEquals("<li class=\"feestdag\">21</li>", actual.toString(false));
    }

    @Test
    public void testSluitingsdag() throws Exception {
        XmlElement actual = new NewDayCodeGenerator().generate(LocalDate.of(2016, 4, 21), Style.SLUITINGSDAG);

        Assert.assertNotNull(actual);
        Assert.assertEquals("<li class=\"gesloten\">21</li>", actual.toString(false));
    }
}