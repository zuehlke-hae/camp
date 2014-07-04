package ch.zuehlke.camp.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import ch.zuehlke.camp.jpa.Food;

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

	    appendFoods(returnString);
	    
		return returnString.toString();
	}
	
	private void appendFoods(StringBuilder returnString) {
		TypedQuery<Food> q1 = em.createQuery("SELECT x FROM Food x WHERE x.id=2", Food.class);
	    List<Food> foods = q1.getResultList();
	    
	    returnString.append("Found " + foods.size() + " rows in Table Food with id=2." + NEWLINE);
	    
	    for (Object result : foods) {
	    	returnString.append(result.toString() + NEWLINE);
	    }
	}
}
