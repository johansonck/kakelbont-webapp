package org.kakelbont.webapp.calendar;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by johansonck on 18/03/2017.
 */
public class StringToLocalDateConverterTest {

    @Test
    public void test() {
        LocalDate localDate = new StringToLocalDateConverter().toLocalDate("2010-11-01");
        assertThat(localDate).isEqualTo(LocalDate.of(2010, 11, 1));
    }
}