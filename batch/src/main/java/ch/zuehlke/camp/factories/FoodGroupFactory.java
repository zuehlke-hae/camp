package ch.zuehlke.camp.factories;

import ch.zuehlke.camp.jpa.FoodGroup;

public class FoodGroupFactory {

	public static FoodGroup createFoodGroup(String[] tokens) {
		String FD_GRP_ID = tokens[0];
		String FD_GRP_COD = tokens[1];
		String FD_GRP_NME = tokens[2];
		String FD_GRP_NMF = tokens[3];
		
		Double FD_GRP_ID_DB = Double.parseDouble(FD_GRP_ID);
		return new FoodGroup(FD_GRP_ID_DB.longValue(), FD_GRP_NME);
	}

}
