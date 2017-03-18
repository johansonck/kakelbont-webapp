package org.kakelbont.webapp.calendar;

import be.sonck.xml.XmlElement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NewCalendarCodeGenerator {

    private NewMonthCodeGenerator monthCodeGenerator = new NewMonthCodeGenerator();

    public List<XmlElement> generate(int firstYear, int firstMonth, int lastYear, int lastMonth,
            List<LocalDate> feestdagen, List<LocalDate> sluitingsdagen) {

        List<XmlElement> list = new ArrayList<>();

        if (firstYear == lastYear) {
            list.addAll(getMonths(firstYear, firstMonth, lastMonth, feestdagen, sluitingsdagen));
        } else {
            list.addAll(getMonths(firstYear, firstMonth, 12, feestdagen, sluitingsdagen));

            for (int year = firstYear + 1; year < lastYear; year++) {
                list.addAll(getMonths(year, 1, 12, feestdagen, sluitingsdagen));
            }

            list.addAll(getMonths(lastYear, 1, lastMonth, feestdagen, sluitingsdagen));
        }

        return list;
    }

    private List<XmlElement> getMonths(int firstYear, int firstMonth, int lastMonth, List<LocalDate> feestdagen,
            List<LocalDate> sluitingsdagen) {
        List<XmlElement> list2 = new ArrayList<>();

        for (int month = firstMonth; month <= lastMonth; month++) {
            list2.add(monthCodeGenerator.generate(firstYear, month, feestdagen, sluitingsdagen));
        }
        return list2;
    }
}
