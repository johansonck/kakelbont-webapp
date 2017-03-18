package org.kakelbont.webapp.calendar;

import be.sonck.xml.XmlElement;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

/**
 * Created by johansonck on 14/03/2017.
 */
public class NewMonthCodeGeneratorTest {

    @Test
    public void test() throws Exception {
        LocalDateFileReader reader = new LocalDateFileReader();

        XmlElement actual = new NewMonthCodeGenerator().generate(2010, 11,
                reader.readFile("/test-kalender-november2010-feestdagen.txt"),
                reader.readFile("/test-kalender-november2010-sluitingsdagen.txt"));

        assertNotNull(actual);

        assertThat(actual.toString(false))
                .isEqualTo(readResource("/test-kalender-november2010-new.txt"));
    }

    private String readResource(String name) throws IOException {
        return IOUtils.toString(DefaultMonthCodeGeneratorTest.class.getResourceAsStream(name)).trim();
    }
}