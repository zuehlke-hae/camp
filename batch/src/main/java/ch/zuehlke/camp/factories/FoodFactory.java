package ch.zuehlke.camp.factories;

import ch.zuehlke.camp.jpa.Food;

public class FoodFactory {

	public static Food createFood(String[] tokens) {
		String FD_ID = tokens[0];
		String FD_CODE = tokens[1];
		String FD_GRP_ID = tokens[2];
		String FD_SRC_ID = tokens[3];
		String A_FD_NME = tokens[4]; // English
		String A_FD_NMF = tokens[5]; // French
		String L_FD_NME = tokens[6]; // English, long
		String L_FD_NMF = tokens[7]; // French, long
		String COUNTRY_C = tokens[8]; // Country Code
		String FD_DT_ENT = tokens[9]; // Entry Date?
		String FD_DT_PUB = tokens[10]; // Publish Date?
		String SCI_NM = tokens[11]; // Scientific name

		return new Food(Long.parseLong(FD_ID), L_FD_NME);
	}

}
