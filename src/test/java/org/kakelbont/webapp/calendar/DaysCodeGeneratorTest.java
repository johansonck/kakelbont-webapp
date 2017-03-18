package org.kakelbont.webapp.calendar;

import be.sonck.xml.XmlElement;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.kakelbont.webapp.calendar.generator.DaysCodeGenerator;
import org.kakelbont.webapp.calendar.reader.LocalDateFileReader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

/**
 * Created by johansonck on 18/03/2017.
 */
public class DaysCodeGeneratorTest {

    @Test
    public void test() throws Exception {
        LocalDateFileReader reader = new LocalDateFileReader();

        XmlElement actual = new DaysCodeGenerator().generate(2010, 11,
                reader.readFile("/test-kalender-november2010-feestdagen.txt"),
                reader.readFile("/test-kalender-november2010-sluitingsdagen.txt"));

        assertNotNull(actual);

        String expected = IOUtils.toString(getClass().getResourceAsStream("/test-kalender-november2010-dagen.txt"));
        assertThat(actual.toString(false)).isEqualTo(expected);
    }
}