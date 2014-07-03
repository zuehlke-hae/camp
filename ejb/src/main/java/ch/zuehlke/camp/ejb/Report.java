package ch.zuehlke.camp.ejb;

import java.io.IOException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import ch.zuehlke.camp.jpa.EatenFood;

@Stateless
@Path("report")
@PersistenceContext(name = "ExampleDS")
public class Report {

	@PersistenceContext
	EntityManager em;

	private static ObjectMapper mapper = new ObjectMapper();

	@GET
	public String find() throws JsonGenerationException, JsonMappingException, IOException {
		TypedQuery<EatenFood> q1 = em.createQuery(
				"SELECT x FROM EatenFood x",
				EatenFood.class);
		List<EatenFood> results = q1.getResultList();
		
		return mapper.writeValueAsString(results);
	}

}
