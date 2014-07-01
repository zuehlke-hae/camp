package ch.zuehlke.camp.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.BeforeClass;

public class ContactEntityTest {
	private static EntityManager em = null;

	@BeforeClass
	public static void setUpClass() throws Exception {
		if (em == null) {
			em = (EntityManager) Persistence.createEntityManagerFactory(
					"testPU").createEntityManager();
		}
	}
	
	/*@Test
	public void testNecessaryOps(){
	    // Start a transaction
	    em.getTransaction().begin();

	    // ------------  Create a Company C1 ---------
	    Contact c1 = new Contact();
	    c1.setFirstName("Portugal");
	    c1.setLastName("Portugal");
	    
	    // At this Point the Entity does not have a
	    // Persistent Identity and is not associated
	    // with a persistent Context
	    em.persist(c1); // Persist the Entity
	    em.flush();
	    // At this point the Entity has a Persistent
	    // Identity and is associated with a Persistent
	    // context.

	    // ------------  Create a Company C2 ---------
	    Contact c2 = new Contact();
	    c2.setFirstName("US");
	    c2.setLastName("US");
	    
	    em.persist(c2);
	    em.flush();

	    System.out.println("Company 1 Id : " + c1.getId());
	    System.out.println("Company 2 Id : " + c2.getId());
	}*/
}
