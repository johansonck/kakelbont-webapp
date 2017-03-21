package org.kakelbont.webapp.calendar.generator;

import be.sonck.xml.XmlElement;
import org.kakelbont.webapp.calendar.generator.DayCodeGenerator.Style;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

/**
 * Created by johansonck on 18/03/2017.
 */
public class DaysCodeGenerator {

    private DayCodeGenerator dayCodeGenerator = new DayCodeGenerator();

    public XmlElement generate(int year, int month, List<LocalDate> feestdagen, List<LocalDate> sluitingsdagen) {
        XmlElement rootElement = new XmlElement("ul");
        rootElement.setAttribute("class", "dagen");

        LocalDate firstDayOfTheMonth = LocalDate.of(year, month, 1);

        getDaysBefore(firstDayOfTheMonth).forEach(rootElement::addChild);
        getDaysInMonth(firstDayOfTheMonth, feestdagen, sluitingsdagen).forEach(rootElement::addChild);

        int numberOfMissingDays = 42 - rootElement.getChildren().size();
        getDaysAfter(firstDayOfTheMonth, numberOfMissingDays).forEach(rootElement::addChild);

        return rootElement;
    }

    private List<XmlElement> getDaysInMonth(LocalDate firstDayOfTheMonth, List<LocalDate> feestdagen,
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
        if (isWeekend(day)) {
            return Style.WEEKEND;
        }

        if (feestdagen != null && feestdagen.contains(day)) {
            return Style.FEESTDAG;
        }

        if (sluitingsdagen != null && sluitingsdagen.contains(day)) {
            return Style.SLUITINGSDAG;
        }

        return null;
    }

    private List<XmlElement> getDaysBefore(LocalDate firstDayOfTheMonth) {
        List<XmlElement> daysToAdd = new ArrayList<>();

        DayOfWeek dayOfTheWeek = firstDayOfTheMonth.getDayOfWeek();

        if (dayOfTheWeek == DayOfWeek.MONDAY) return daysToAdd;
        daysToAdd.add(0, generateDayBefore(firstDayOfTheMonth.minusDays(1)));

        if (dayOfTheWeek == DayOfWeek.TUESDAY) return daysToAdd;
        daysToAdd.add(0, generateDayBefore(firstDayOfTheMonth.minusDays(2)));

        if (dayOfTheWeek == DayOfWeek.WEDNESDAY) return daysToAdd;
        daysToAdd.add(0, generateDayBefore(firstDayOfTheMonth.minusDays(3)));

        if (dayOfTheWeek == DayOfWeek.THURSDAY) return daysToAdd;
        daysToAdd.add(0, generateDayBefore(firstDayOfTheMonth.minusDays(4)));

        if (dayOfTheWeek == DayOfWeek.FRIDAY) return daysToAdd;
        daysToAdd.add(0, generateDayBefore(firstDayOfTheMonth.minusDays(5)));

        if (dayOfTheWeek == DayOfWeek.SATURDAY) return daysToAdd;
        daysToAdd.add(0, generateDayBefore(firstDayOfTheMonth.minusDays(6)));

        return daysToAdd;
    }

    private XmlElement generateDayBefore(LocalDate day) {
        return dayCodeGenerator.generate(day, determineStylesForDayBefore(day));
    }

    private List<XmlElement> getDaysAfter(LocalDate firstDayOfTheMonth, int numberOfDays) {
        List<XmlElement> daysToAdd = new ArrayList<>();
        LocalDate firstDayOfNextMonth = firstDayOfTheMonth.plusMonths(1);
        boolean optional = false;

        for (int i = 0; i < numberOfDays; i++) {
            LocalDate currentDay = firstDayOfNextMonth.plusDays(i);

            if (currentDay.getDayOfWeek() == DayOfWeek.MONDAY) {
                optional = true;
            }

            List<Style> styles = determineStylesForDayAfter(currentDay, optional);
            daysToAdd.add(dayCodeGenerator.generate(currentDay, styles));
        }

        return daysToAdd;
    }

    private List<Style> determineStylesForDayAfter(LocalDate day, boolean optional) {
        List<Style> styles = new ArrayList<>();

        styles.add(Style.ANDERE_MAAND);

        if (isWeekend(day)) {
            styles.add(Style.WEEKEND);
        }

        if (optional) {
            styles.add(Style.OPTIONEEL);
        }

        return styles;
    }

    private List<Style> determineStylesForDayBefore(LocalDate day) {
        List<Style> styles = new ArrayList<>();

        styles.add(Style.ANDERE_MAAND);

        if (isWeekend(day)) {
            styles.add(Style.WEEKEND);
        }

        return styles;
    }

    private boolean isWeekend(LocalDate day) {
        if (day == null) return false;

        DayOfWeek dayOfTheWeek = day.getDayOfWeek();

        return (dayOfTheWeek == SATURDAY || dayOfTheWeek == SUNDAY);
    }
}
