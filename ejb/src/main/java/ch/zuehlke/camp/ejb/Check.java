package ch.zuehlke.camp.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import ch.zuehlke.camp.jpa.Food;
import ch.zuehlke.camp.jpa.FoodGroup;

@Stateless
@Path("check")
@PersistenceContext(name = "ExampleDS")
public class Check {
	
	private final static String NEWLINE = "<br>";
	
	@PersistenceContext
	EntityManager em;

	@GET
	public String check() {
	    StringBuilder returnString = new StringBuilder();

	    appendFoodGroups(returnString);
	    appendFoods(returnString);
	    
		return returnString.toString();
	}

	private void appendFoodGroups(StringBuilder returnString) {
		TypedQuery<FoodGroup> q1 = em.createQuery("SELECT x FROM FoodGroup x", FoodGroup.class);
	    List<FoodGroup> foodGroups = q1.getResultList();
	    
	    returnString.append("Found " + foodGroups.size() + " rows in Table FoodGroup." + NEWLINE);
	    
	    for (Object result : foodGroups) {
	    	returnString.append(result.toString() + NEWLINE);
	    }
	}

	private void appendFoods(StringBuilder returnString) {
		TypedQuery<Food> q1 = em.createQuery("SELECT x FROM Food x", Food.class);
	    List<Food> foods = q1.getResultList();
	    
	    returnString.append("Found " + foods.size() + " rows in Table Food." + NEWLINE);
	    
	    for (Object result : foods) {
	    	returnString.append(result.toString() + NEWLINE);
	    }
	}
}