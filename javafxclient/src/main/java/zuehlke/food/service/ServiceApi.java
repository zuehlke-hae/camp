package zuehlke.food.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import ch.zuehlke.camp.jpa.EatenFood;
import ch.zuehlke.camp.jpa.Food;

public interface ServiceApi {

	public abstract List<Food> findFood(String searchString) throws IOException;

	public abstract void addMeal(Date eatenDate, List<Food> foodsEaten,
			List<Double> values) throws ClientProtocolException, IOException;

	public abstract List<EatenFood> report() throws ClientProtocolException,
			IOException;

}