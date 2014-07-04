package ch.zuehlke.camp.factories;

import ch.zuehlke.camp.jpa.NutrimentSource;

public class NutrimentSourceFactory {

	public static NutrimentSource createNutrimentSource(String[] tokens) {
		String NT_SRC_ID = tokens[0];
		String NT_SRC_COD = tokens[1];
		String NT_SRC_NME = tokens[2];
		String NT_SRC_NMF = tokens[3];
		
		Double NT_SRC_ID_DB = Double.parseDouble(NT_SRC_ID);
		NutrimentSource source = new NutrimentSource(NT_SRC_ID_DB.longValue(), NT_SRC_NME);
		return source;
	}

}
