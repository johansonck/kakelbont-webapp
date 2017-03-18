package org.kakelbont.webapp.calendar;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by johansonck on 18/03/2017.
 */
public class LocalDateFileReader {

    public List<LocalDate> readFile(String resource) throws IOException {
        List<LocalDate> list = new ArrayList<>();

        LocalDateFileLineReader lineReader = new LocalDateFileLineReader(resource);
        LocalDate calendar = lineReader.readLine();
        while (calendar != null) {
            list.add(calendar);
            calendar = lineReader.readLine();
        }

        return list;
    }
}
