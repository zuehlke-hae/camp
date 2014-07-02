package ch.zuehlke.camp.ejb;

import java.util.Properties;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Stateless
@Path("setup")
@PersistenceContext(name = "ExampleDS")
public class Setup {
	
	@PersistenceContext
	EntityManager em;

	@GET
	public String setup() {
	    Query q1 = em.createNativeQuery("DELETE FROM Food");
	    q1.executeUpdate();
	    
		JobOperator jo = BatchRuntime.getJobOperator();
		long id = jo.start("setup", new Properties());

		return "Job started";
	}
}
