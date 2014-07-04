package ch.zuehlke.camp.factories;

import ch.zuehlke.camp.jpa.NutrimentName;

public class NutrimentNameFactory {

	public static NutrimentName createNutrimentName(String[] tokens) {
		String NT_ID = tokens[0];
		String NT_COD = tokens[1];
		String NT_SYM = tokens[2];
		String UNIT = tokens[3];
		String NT_NME = tokens[4];
		String NT_NMF = tokens[5];
		String TAGNAME = tokens[6];
		String NT_DEC = tokens[7];

		Double NT_ID_DB = Double.parseDouble(NT_ID);
		NutrimentName name = new NutrimentName(NT_ID_DB.longValue(), NT_SYM, UNIT, NT_NME);
		return name;
	}

}
