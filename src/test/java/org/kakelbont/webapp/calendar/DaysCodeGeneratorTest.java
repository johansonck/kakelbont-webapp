package org.kakelbont.webapp.calendar;

import be.sonck.xml.XmlElement;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.kakelbont.webapp.calendar.generator.DaysCodeGenerator;
import org.kakelbont.webapp.calendar.reader.LocalDateFileReader;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

/**
 * Created by johansonck on 18/03/2017.
 */
public class DaysCodeGeneratorTest {

    @Test
    public void januari2016() throws Exception {
        LocalDateFileReader reader = new LocalDateFileReader();

        XmlElement actual = new DaysCodeGenerator().generate(2016, 1,
                reader.readFile("/test-kalender-januari2016-feestdagen.txt"),
                reader.readFile("/test-kalender-januari2016-sluitingsdagen.txt"));

        assertNotNull(actual);

        String expected = IOUtils.toString(getClass().getResourceAsStream("/test-kalender-januari2016-dagen.txt"));
        assertThat(actual.toString(false)).isEqualTo(expected);
    }

    @Test
    public void oktober2017() throws Exception {
        LocalDateFileReader reader = new LocalDateFileReader();

        XmlElement actual = new DaysCodeGenerator().generate(2017, 10, emptyList(), emptyList());

        assertNotNull(actual);

        String expected = IOUtils.toString(getClass().getResourceAsStream("/test-kalender-oktober2017-dagen.txt"));
        assertThat(actual.toString(false)).isEqualTo(expected);
    }

    @Test
    public void februari2018() {
        new DaysCodeGenerator().generate(2018, 2, emptyList(), emptyList());
    }
}