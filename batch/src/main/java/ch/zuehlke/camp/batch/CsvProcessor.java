package ch.zuehlke.camp.batch;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.zuehlke.camp.factories.FoodFactory;
import ch.zuehlke.camp.factories.FoodGroupFactory;
import ch.zuehlke.camp.factories.FoodSourceFactory;

@Named
public class CsvProcessor implements ItemProcessor {
	
	@PersistenceContext
    EntityManager em;
	
	@Override
	public Object processItem(Object t) {
		@SuppressWarnings("unchecked")
		Pair<String, String> pair = (Pair<String, String>) t;

		String file = pair.getFirst();
		String[] tokens = pair.getSecond().split(";", -1);

		switch (file) {
		case "CONV_FAC.csv":
			// TODO: Strategy
			return null;
		case "FOOD_GRP.csv":
			return FoodGroupFactory.createFoodGroup(tokens);
		case "FOOD_NM.csv":	//THE NAMES OF FOOD
			return FoodFactory.createFood(tokens, em);
		case "FOOD_SRC.csv":
			return FoodSourceFactory.createFoodSource(tokens);
		case "MEASURE.csv":
			// TODO: Strategy
			return null;
		case "NT_AMT.csv":
			// TODO: Strategy
			return null;
		case "NT_NM.csv":
			// TODO: Strategy
			return null;
		case "NT_SRC.csv":
			// TODO: Strategy
			return null;
		case "REFU_NM.csv":
			// TODO: Strategy
			return null;
		case "REFUSE.csv":
			// TODO: Strategy
			return null;
		case "YIELD.csv":
			// TODO: Strategy
			return null;
		case "YLD_NM.csv":
			// TODO: Strategy
			return null;
		default:
			System.out.println("ERROR: Could not parse: " + pair.getFirst() + " : " + pair.getSecond());
			return null;
		}
	}
}
