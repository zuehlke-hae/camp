package ch.zuehlke.camp.ejb;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import ch.zuehlke.camp.jpa.EatenFood;
import ch.zuehlke.camp.jpa.Food;

@Stateless
@Path("addMeal")
@PersistenceContext(name = "ExampleDS")
public class AddMeal {

	@PersistenceContext
	EntityManager em;

	private static ObjectMapper mapper = new ObjectMapper();

	@POST
	@Path("{date}/{foods}/{values}")
	public void find(@PathParam("date") String dateStr, @PathParam("foods") String foodStr, @PathParam("values") String valuesStr)
			throws JsonGenerationException, JsonMappingException, IOException {
		dateStr = URLDecoder.decode(dateStr, "UTF-8");
		foodStr = URLDecoder.decode(foodStr, "UTF-8");
		valuesStr = URLDecoder.decode(valuesStr, "UTF-8");
		
		Date date = mapper.readValue(dateStr, Date.class);
		List<Food> foodList = mapper.readValue(foodStr, new TypeReference<List<Food>>() { });
		List<Double> values = mapper.readValue(valuesStr, new TypeReference<List<Double>>() { });
		
		if (foodList.size() != values.size()) {
			throw new RuntimeException("Expected foodList and values to be of equal size!");
		}
		
		for (int i = 0; i < foodList.size(); i++) {
			Food food = foodList.get(i);
			Double value = values.get(i);
			String sql = "SELECT x FROM Food x WHERE x.name='" + food.getName() + "'";
			TypedQuery<Food> q1 = em.createQuery(
					sql,
					Food.class);
			List<Food> results = q1.getResultList();
			if (results.size() != 1) {
				throw new RuntimeException("Expected exactly 1 Food with the name " + food.getName());
			}
			
			EatenFood eatenFood = new EatenFood(results.get(0), value, date);
			em.persist(eatenFood);
		}
	}

}
