package ch.zuehlke.camp.ejb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
		return "hello world";
	}
}