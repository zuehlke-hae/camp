package ch.zuehlke.camp.ejb;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import ch.zuehlke.camp.jpa.Food;

@Stateless
@Path("findFood")
@PersistenceContext(name = "ExampleDS")
public class FindFood {

	@PersistenceContext
	EntityManager em;

	@GET
	@Path("{something}")
	public String find(@PathParam("something") String approxName)
			throws JsonGenerationException, JsonMappingException, IOException {
		approxName = URLDecoder.decode(approxName, "UTF-8");
		System.out.println("Searching for: " + approxName);
		
		TypedQuery<Food> q1 = em.createQuery(
				"SELECT x FROM Food x WHERE x.name LIKE '%" + approxName + "%'",
				Food.class);
		List<Food> results = q1.getResultList();
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(results);
	}

}
