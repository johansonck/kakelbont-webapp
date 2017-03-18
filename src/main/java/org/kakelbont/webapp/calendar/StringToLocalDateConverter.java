package org.kakelbont.webapp.calendar;

import java.time.LocalDate;

/**
 * Created by johansonck on 18/03/2017.
 */
public class StringToLocalDateConverter {

    public LocalDate toLocalDate(String string) {
        return LocalDate.parse(string);
    }
}
