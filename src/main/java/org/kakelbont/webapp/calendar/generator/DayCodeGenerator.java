package org.kakelbont.webapp.calendar.generator;

import be.sonck.xml.XmlElement;
import be.sonck.xml.value.StringValue;
import be.sonck.xml.value.XmlValue;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

public class DayCodeGenerator {
	
	public enum Style {
		WEEKEND("weekend"), FEESTDAG("feestdag"), SLUITINGSDAG("gesloten"), ANDERE_MAAND("andereMaand"), OPTIONEEL("optioneel");
		
		private final String string;

		Style(String string) {
			this.string = string;
		}

		@Override
		public String toString() {
			return string;
		}
	}

	public XmlElement generateEmpty() {
		return generateEmpty(null);
	}
	
	public XmlElement generateEmpty(Style style) {
		return generate(null, style);
	}

	public XmlElement generate(LocalDate day, Style style) {
		return generate(day, Stream.of(style).filter(Objects::nonNull).collect(toList()));
	}

	public XmlElement generate(LocalDate day, List<Style> styles) {
		XmlElement xmlElement = generate(day);

		if (isNotEmpty(styles)) {
            String stylesAsString = styles.stream()
                    .map(Style::toString)
                    .collect(joining(" "));

            String classAttribute = xmlElement.getAttribute("class");
            classAttribute = (classAttribute == null ? stylesAsString : classAttribute + " " + stylesAsString);

            xmlElement.setAttribute("class", classAttribute);
		}

		return xmlElement;
	}

	public XmlElement generate(LocalDate day) {
		XmlValue value = XmlValue.NON_BLANK_SPACE;

		if (day != null) {
			value = new StringValue(String.valueOf(day.getDayOfMonth()));
		}

		return new XmlElement("li", value);
	}
}
