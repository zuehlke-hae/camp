package ch.zuehlke.camp.factories;

import javax.persistence.EntityManager;
import ch.zuehlke.camp.jpa.Food;
import ch.zuehlke.camp.jpa.FoodGroup;
import ch.zuehlke.camp.jpa.FoodSource;

public class FoodFactory {

	public static Food createFood(String[] tokens, EntityManager em) {
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
		
		Food food = new Food(Long.parseLong(FD_ID), L_FD_NME);

		FoodGroup group = em.find(FoodGroup.class, Long.parseLong(FD_GRP_ID));
		FoodSource source = em.find(FoodSource.class, Long.parseLong(FD_SRC_ID));

		food.setFoodGroup(group);
		food.setFoodSource(source);
		
		return food;
	}

}
