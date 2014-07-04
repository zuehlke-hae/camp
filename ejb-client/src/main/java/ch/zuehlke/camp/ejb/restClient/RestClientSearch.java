package ch.zuehlke.camp.ejb.restClient;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.http.client.ClientProtocolException;

import ch.zuehlke.camp.jpa.Food;
import ch.zuehlke.camp.jpa.NutrimentInfo;

public class RestClientSearch {

	public static void main(String[] args) throws ClientProtocolException, IOException, JAXBException {
		String searchString;
		if (args.length == 0) {
			System.out.println("No arguments found, running default query...");
			searchString = "cheese";
		} else {
			searchString = args[0];
		}
		
		System.out.println("Searching for: " + searchString);
		
		List<Food> foods = RestAPI.findFood(searchString);
		for (Food food : foods) {
			System.out.println("Found: " + food.getName() + ", with the following ingredients: ");
			for (NutrimentInfo info : food.getNutrimentInfos()) {
				System.out.println(info.getNutrimentName().getName() + ": " + info.getValue() + info.getNutrimentName().getUnit());
			}
		}
		
	}

}
