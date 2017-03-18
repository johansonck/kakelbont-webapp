package org.kakelbont.webapp.calendar.reader;

import org.apache.commons.io.IOUtils;
import org.kakelbont.webapp.calendar.converter.StringToLocalDateConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;

/**
 * Created by johansonck on 18/03/2017.
 */
public class LocalDateFileLineReader {

    private BufferedReader bufferedReader;

    private final StringToLocalDateConverter converter;
    private final String resource;


    public LocalDateFileLineReader(String resource) {
        this.resource = resource;
        this.converter = new StringToLocalDateConverter();
    }

    public LocalDate readLine() throws IOException {
        String line = getBufferedReader().readLine();
        if (line == null || line.trim().length() == 0) return null;

        return converter.toLocalDate(line);
    }

    private BufferedReader getBufferedReader() throws IOException {
        if (bufferedReader == null) {
            InputStream inputStream = LocalDateFileLineReader.class.getResource(resource).openStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(reader);
        }

        return bufferedReader;
    }

    public void close() {
        if (bufferedReader != null) {
            IOUtils.closeQuietly(bufferedReader);
        }
    }
}
