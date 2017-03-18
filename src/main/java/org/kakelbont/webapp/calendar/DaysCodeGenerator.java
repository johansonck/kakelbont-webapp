package org.kakelbont.webapp.calendar;

import be.sonck.xml.XmlElement;
import org.kakelbont.webapp.calendar.NewDayCodeGenerator.Style;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by johansonck on 18/03/2017.
 */
public class DaysCodeGenerator {

    private NewDayCodeGenerator dayCodeGenerator = new NewDayCodeGenerator();

    public XmlElement generate(int year, int month, List<LocalDate> feestdagen, List<LocalDate> sluitingsdagen) {
        XmlElement rootElement = new XmlElement("ul");
        rootElement.addAttribute("class", "dagen");

        LocalDate firstDayOfTheMonth = LocalDate.of(year, month, 1);

        getEmptyDaysBefore(firstDayOfTheMonth).forEach(rootElement::addChild);
        getDayElements(firstDayOfTheMonth, feestdagen, sluitingsdagen).forEach(rootElement::addChild);
        getEmptyDaysAfter(firstDayOfTheMonth).forEach(rootElement::addChild);

        return rootElement;
    }

    private List<XmlElement> getDayElements(LocalDate firstDayOfTheMonth, List<LocalDate> feestdagen,
            List<LocalDate> sluitingsdagen) {

        List<XmlElement> elements = new ArrayList<>();

        int numberOfDays = firstDayOfTheMonth.getMonth().maxLength();
        for (int dayOfMonth = 1; dayOfMonth <= numberOfDays; dayOfMonth++) {
            LocalDate day = firstDayOfTheMonth.withDayOfMonth(dayOfMonth);
            XmlElement element = dayCodeGenerator.generate(day, determineStyle(day, feestdagen, sluitingsdagen));
            elements.add(element);
        }
        return elements;
    }

    private Style determineStyle(LocalDate day, List<LocalDate> feestdagen, List<LocalDate> sluitingsdagen) {
        if (feestdagen != null && feestdagen.contains(day)) {
            return Style.FEESTDAG;
        }

        if (sluitingsdagen != null && sluitingsdagen.contains(day)) {
            return Style.SLUITINGSDAG;
        }

        return null;
    }

    private List<XmlElement> getEmptyDaysBefore(LocalDate firstDayOfTheMonth) {
        List<XmlElement> emptyDays = new ArrayList<>();

        DayOfWeek dayOfTheWeek = firstDayOfTheMonth.getDayOfWeek();

        if (dayOfTheWeek == DayOfWeek.MONDAY) return emptyDays;
        emptyDays.add(dayCodeGenerator.generateEmpty());

        if (dayOfTheWeek == DayOfWeek.TUESDAY) return emptyDays;
        emptyDays.add(dayCodeGenerator.generateEmpty());

        if (dayOfTheWeek == DayOfWeek.WEDNESDAY) return emptyDays;
        emptyDays.add(dayCodeGenerator.generateEmpty());

        if (dayOfTheWeek == DayOfWeek.THURSDAY) return emptyDays;
        emptyDays.add(dayCodeGenerator.generateEmpty());

        if (dayOfTheWeek == DayOfWeek.FRIDAY) return emptyDays;
        emptyDays.add(dayCodeGenerator.generateEmpty());

        if (dayOfTheWeek == DayOfWeek.SATURDAY) return emptyDays;
        emptyDays.add(dayCodeGenerator.generateEmpty(Style.WEEKEND));

        return emptyDays;
    }

    private List<XmlElement> getEmptyDaysAfter(LocalDate firstDayOfTheMonth) {
        List<XmlElement> emptyDays = new ArrayList<>();

        LocalDate firstDayOfNextMonth = firstDayOfTheMonth.plusMonths(1);
        DayOfWeek dayOfTheWeek = firstDayOfNextMonth.getDayOfWeek();

        if (dayOfTheWeek == DayOfWeek.MONDAY) return emptyDays;
        emptyDays.add(0, dayCodeGenerator.generateEmpty(Style.WEEKEND));

        if (dayOfTheWeek == DayOfWeek.SUNDAY) return emptyDays;
        emptyDays.add(0, dayCodeGenerator.generateEmpty(Style.WEEKEND));

        if (dayOfTheWeek == DayOfWeek.SATURDAY) return emptyDays;
        emptyDays.add(0, dayCodeGenerator.generateEmpty());

        if (dayOfTheWeek == DayOfWeek.FRIDAY) return emptyDays;
        emptyDays.add(0, dayCodeGenerator.generateEmpty());

        if (dayOfTheWeek == DayOfWeek.THURSDAY) return emptyDays;
        emptyDays.add(0, dayCodeGenerator.generateEmpty());

        if (dayOfTheWeek == DayOfWeek.WEDNESDAY) return emptyDays;
        emptyDays.add(0, dayCodeGenerator.generateEmpty());

        if (dayOfTheWeek == DayOfWeek.TUESDAY) return emptyDays;
        emptyDays.add(0, dayCodeGenerator.generateEmpty());

        return emptyDays;
    }
}
