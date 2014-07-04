package ch.zuehlke.camp.ejb.restClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.http.client.ClientProtocolException;

import ch.zuehlke.camp.jpa.EatenFood;
import ch.zuehlke.camp.jpa.Food;

public class RestClientInsertAndReport {

	public static void main(String[] args) throws ClientProtocolException,
			IOException, JAXBException {
		addFirstMeal();
		addSecondMeal();

		List<EatenFood> allFoodsEaten = RestAPI.report();
		for (EatenFood eatenFood : allFoodsEaten) {
			System.out.println("At " + eatenFood.getDate() + " I have eaten "
					+ eatenFood.getValue() + " of "
					+ eatenFood.getFood().asString());
		}

	}

	private static void addFirstMeal() throws ClientProtocolException,
			IOException {
		Food eaten = new Food("Cheese souffle");
		Food eatenAlso = new Food("Burrito, bean and cheese, frozen");
		List<Food> foodsEaten = new ArrayList<Food>();
		foodsEaten.add(eaten);
		foodsEaten.add(eatenAlso);
		List<Double> values = new ArrayList<Double>();
		values.add(new Double(1));
		values.add(new Double(5));

		Date eatenDate = new Date();

		RestAPI.addMeal(eatenDate, foodsEaten, values);
	}

	private static void addSecondMeal() throws ClientProtocolException,
			IOException {
		Food eaten = new Food("Soup, cheese, canned, condensed, water added");
		Food eatenAlso = new Food("Cheesefurter (cheese smokie), pork, beef");
		List<Food> foodsEaten = new ArrayList<Food>();
		foodsEaten.add(eaten);
		foodsEaten.add(eatenAlso);
		List<Double> values = new ArrayList<Double>();
		values.add(new Double(10));
		values.add(new Double(13));

		Date eatenDate = new Date();

		RestAPI.addMeal(eatenDate, foodsEaten, values);
	}

}
