package org.kakelbont.webapp.calendar;

import be.sonck.xml.XmlElement;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kakelbont.webapp.calendar.generator.CalendarCodeGenerator;
import org.kakelbont.webapp.calendar.generator.MonthCodeGenerator;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

/**
 * Created by johansonck on 18/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class CalendarCodeGeneratorTest {

    @Mock
    private MonthCodeGenerator monthCodeGenerator;

    @InjectMocks
    private CalendarCodeGenerator calendarCodeGenerator;


    @Before
    public void setup() {
        when(monthCodeGenerator.generate(anyInt(), anyInt(), anyList(), anyList()))
                .thenAnswer(invocation -> {
                    Integer year = invocation.getArgument(0);
                    Integer month = invocation.getArgument(1);

                    return new XmlElement("" + year + "-" + month);
                });
    }

    @Test
    public void testOneYear() {
        List<LocalDate> feestdagen = singletonList(LocalDate.now());
        List<LocalDate> sluitingsdagen = singletonList(LocalDate.now().plusDays(1));

        List<XmlElement> elements = calendarCodeGenerator.generate(2017, 6, 2017, 8, feestdagen, sluitingsdagen);

        assertThat(elements).contains(new XmlElement("2017-6"));
        assertThat(elements).contains(new XmlElement("2017-7"));
        assertThat(elements).contains(new XmlElement("2017-8"));

        assertThat(elements).hasSize(3);
    }

    @Test
    public void testTwoYears() {
        List<LocalDate> feestdagen = singletonList(LocalDate.now());
        List<LocalDate> sluitingsdagen = singletonList(LocalDate.now().plusDays(1));

        List<XmlElement> elements = calendarCodeGenerator.generate(2017, 10, 2018, 2, feestdagen, sluitingsdagen);

        assertThat(elements).contains(new XmlElement("2017-10"));
        assertThat(elements).contains(new XmlElement("2017-11"));
        assertThat(elements).contains(new XmlElement("2017-12"));
        assertThat(elements).contains(new XmlElement("2018-1"));
        assertThat(elements).contains(new XmlElement("2018-2"));

        assertThat(elements).hasSize(5);
    }

    @Test
    public void testFourYears() {
        List<LocalDate> feestdagen = singletonList(LocalDate.now());
        List<LocalDate> sluitingsdagen = singletonList(LocalDate.now().plusDays(1));

        List<XmlElement> elements = calendarCodeGenerator.generate(2016, 11, 2019, 2, feestdagen, sluitingsdagen);

        assertThat(elements).contains(new XmlElement("2016-11"));
        assertThat(elements).contains(new XmlElement("2016-12"));
        assertThat(elements).contains(new XmlElement("2017-1"));
        assertThat(elements).contains(new XmlElement("2017-2"));
        assertThat(elements).contains(new XmlElement("2017-3"));
        assertThat(elements).contains(new XmlElement("2017-4"));
        assertThat(elements).contains(new XmlElement("2017-5"));
        assertThat(elements).contains(new XmlElement("2017-6"));
        assertThat(elements).contains(new XmlElement("2017-7"));
        assertThat(elements).contains(new XmlElement("2017-8"));
        assertThat(elements).contains(new XmlElement("2017-9"));
        assertThat(elements).contains(new XmlElement("2017-10"));
        assertThat(elements).contains(new XmlElement("2017-11"));
        assertThat(elements).contains(new XmlElement("2017-12"));
        assertThat(elements).contains(new XmlElement("2018-1"));
        assertThat(elements).contains(new XmlElement("2018-2"));
        assertThat(elements).contains(new XmlElement("2018-3"));
        assertThat(elements).contains(new XmlElement("2018-4"));
        assertThat(elements).contains(new XmlElement("2018-5"));
        assertThat(elements).contains(new XmlElement("2018-6"));
        assertThat(elements).contains(new XmlElement("2018-7"));
        assertThat(elements).contains(new XmlElement("2018-8"));
        assertThat(elements).contains(new XmlElement("2018-9"));
        assertThat(elements).contains(new XmlElement("2018-10"));
        assertThat(elements).contains(new XmlElement("2018-11"));
        assertThat(elements).contains(new XmlElement("2018-12"));
        assertThat(elements).contains(new XmlElement("2019-1"));
        assertThat(elements).contains(new XmlElement("2019-2"));

        assertThat(elements).hasSize(28);
    }
}