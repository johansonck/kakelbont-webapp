package org.kakelbont.webapp.calendar.generator;

import be.sonck.xml.XmlElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static org.apache.commons.lang3.text.WordUtils.capitalize;

public class MonthCodeGenerator {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("MMMM yyyy", Locale.forLanguageTag("nl"));

    private final DaysCodeGenerator daysCodeGenerator = new DaysCodeGenerator();

    public XmlElement generate(int year, int month, List<LocalDate> feestdagen, List<LocalDate> sluitingsdagen) {
        XmlElement kalenderElement = new XmlElement("div").setAttribute("class", "kalender")
                .addChild(generateMaandHoofding(year, month))
                .addChild(generateDagenHoofding())
                .addChild(daysCodeGenerator.generate(year, month, feestdagen, sluitingsdagen));

        return new XmlElement("div").setAttribute("class", "span-4")
                .addChild(kalenderElement);
    }

    private XmlElement generateDagenHoofding() {
        return new XmlElement("ul").setAttribute("class", "dagenHoofding")
                .addChild(new XmlElement("li", "ma"))
                .addChild(new XmlElement("li", "di"))
                .addChild(new XmlElement("li", "wo"))
                .addChild(new XmlElement("li", "do"))
                .addChild(new XmlElement("li", "vr"))
                .addChild(new XmlElement("li", "za"))
                .addChild(new XmlElement("li", "zo"));
    }

    private XmlElement generateMaandHoofding(int year, int month) {
        String formattedDate = capitalize(LocalDate.of(year, month, 1).format(DATE_TIME_FORMATTER));

        return new XmlElement("div", formattedDate)
                .setAttribute("class", "maandHoofding");
    }
}