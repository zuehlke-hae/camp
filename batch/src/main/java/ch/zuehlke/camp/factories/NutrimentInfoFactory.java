package ch.zuehlke.camp.factories;

import javax.persistence.EntityManager;

import ch.zuehlke.camp.jpa.Food;
import ch.zuehlke.camp.jpa.NutrimentInfo;
import ch.zuehlke.camp.jpa.NutrimentName;
import ch.zuehlke.camp.jpa.NutrimentSource;

public class NutrimentInfoFactory {

	public static NutrimentInfo createNutrimentInfo(String[] tokens, EntityManager em) {
		String FD_ID = tokens[0];
		String NT_ID = tokens[1];
		String NT_VALUE = tokens[2];
		String STD_ERR = tokens[3];
		String NUM_OBS = tokens[4];
		String NT_SRC_ID = tokens[5];
		String NT_DT_ENT = tokens[6];
		String NT_TR = tokens[7];

		Double FD_ID_DB = Double.parseDouble(FD_ID);
		Double NT_ID_DB = Double.parseDouble(NT_ID);
		
		//Sometimes this is empty!
		Double NT_VALUE_DB;
		if (NT_VALUE.equals("")) {
			NT_VALUE_DB = new Double(0);
		} else {
			NT_VALUE_DB = Double.parseDouble(NT_VALUE);
		}
		
		Double NT_SRC_ID_DB = Double.parseDouble(NT_SRC_ID);
		
		Food food = em.find(Food.class, FD_ID_DB.longValue());
		NutrimentName nutrimentName = em.find(NutrimentName.class, NT_ID_DB.longValue());
		NutrimentSource nutrimentSource = em.find(NutrimentSource.class, NT_SRC_ID_DB.longValue());
		
		NutrimentInfo info = new NutrimentInfo(nutrimentName, NT_VALUE_DB, nutrimentSource);
		
		food.addNutrimentInfo(info);
		
		return info;
	}

}
