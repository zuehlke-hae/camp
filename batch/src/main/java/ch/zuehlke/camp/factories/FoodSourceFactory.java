package ch.zuehlke.camp.factories;

import ch.zuehlke.camp.jpa.FoodSource;

public class FoodSourceFactory {

	public static FoodSource createFoodSource(String[] tokens) {
		String FD_SRC_ID = tokens[0];
		String FD_SRC_COD = tokens[1];
		String FD_SRC_NME = tokens[2];
		String FD_SRC_NMF = tokens[3];

		Double fd_src_id_db = Double.parseDouble(FD_SRC_ID);
		return new FoodSource(fd_src_id_db.longValue(), FD_SRC_NME);
	}
	
}
