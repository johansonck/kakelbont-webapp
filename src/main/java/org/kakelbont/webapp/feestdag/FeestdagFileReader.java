package org.kakelbont.webapp.feestdag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FeestdagFileReader {

	public List<List<Feestdag>> readFile(String resource) throws IOException {
		List<List<Feestdag>> list = new ArrayList<List<Feestdag>>();
		
		FeestdagFileLineReader lineReader = new FeestdagFileLineReader(resource);
		List<Feestdag> feestdagen = lineReader.readLine();
		while (feestdagen != null) {
			list.add(feestdagen);
			feestdagen = lineReader.readLine();
		}
		
		return list;
	}
}
