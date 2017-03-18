package org.kakelbont.webapp.calendar;

import be.sonck.xml.XmlElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NewMonthCodeGenerator {

    private final DaysCodeGenerator daysCodeGenerator = new DaysCodeGenerator();

    public XmlElement generate(int year, int month, List<LocalDate> feestdagen, List<LocalDate> sluitingsdagen) {
        XmlElement kalenderElement = new XmlElement("div").addAttribute("class", "kalender")
                .addChild(generateMaandHoofding(year, month))
                .addChild(generateDagenHoofding())
                .addChild(daysCodeGenerator.generate(year, month, feestdagen, sluitingsdagen));

        return new XmlElement("div").addAttribute("class", "span-4")
                .addChild(kalenderElement);
    }

    private XmlElement generateDagenHoofding() {
        return new XmlElement("ul").addAttribute("class", "dagenHoofding")
                .addChild(new XmlElement("li", "ma"))
                .addChild(new XmlElement("li", "di"))
                .addChild(new XmlElement("li", "wo"))
                .addChild(new XmlElement("li", "do"))
                .addChild(new XmlElement("li", "vr"))
                .addChild(new XmlElement("li", "za"))
                .addChild(new XmlElement("li", "zo"));
    }

    private XmlElement generateMaandHoofding(int year, int month) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        String formattedDate = LocalDate.of(year, month, 1).format(formatter);

        return new XmlElement("div", formattedDate)
                .addAttribute("class", "maandHoofding");
    }
}