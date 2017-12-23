package org.kakelbont.webapp.feestdag;

import be.sonck.xml.XmlElement;

@Deprecated
public class MainClass {

	public static void main(String[] args) throws Exception {
		XmlElement xmlElement = new FeestdagCodeGenerator().generate(
				new FeestdagFileReader().readFile("/feestdagen-overzicht-kort.txt"));

		System.out.println(xmlElement.prettyPrint(2));
	}
}
