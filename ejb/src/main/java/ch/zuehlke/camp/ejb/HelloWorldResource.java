package ch.zuehlke.camp.ejb;

import java.util.Properties;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;

import ch.zuehlke.camp.jpa.Person;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/helloworld")
@PersistenceContext(name = "ExampleDS")
@Transactional
public class HelloWorldResource {
	
	private static final String encoding  = "UTF-8";
	
	// dependency injection bindet den EM an den Lifecycle der Bean
	// und sein TX an die Beanmethode
	@PersistenceContext
	EntityManager em;

	// The Java method will process HTTP GET requests
	@GET
	// The Java method will produce content identified by the MIME Media
	// type "text/plain"
	@Produces("text/plain;charset=" + encoding)
	public String getClichedMessage() {
		
		JobOperator jo = BatchRuntime.getJobOperator();
		@SuppressWarnings("unused")
		long jid = jo.start("myJob", new Properties());

		
		Person person = new Person();
		person.setFirstName("Hans");
		person.setLastName("Müller");
		
		em.persist(person);
		
		//Now look it up again (for test)
		Person result = em.find(Person.class, person.getId());
		
		return "Created " + person.toString() + ", found " + result;
//		ContactBla test = em.find(ContactBla.class, 1L);
		
		
		
//		if (test == null) {
//			test = new ContactBla("Hello", " World");
//
//
//			// At this Point the Entity does not have a
//			// Persistent Identity and is not associated
//			// with a persistent Context
//			em.persist(test); // Persist the Entity
////			em.flush();
//			// At this point the Entity has a Persistent
//			// Identity and is associated with a Persistent
//			// context.
//			
//
//			return "New contact created.";
//		} else {
//			return "Contact found: " + test.getFirstName() + " "
//					+ test.getLastName();
//		}
	}
}