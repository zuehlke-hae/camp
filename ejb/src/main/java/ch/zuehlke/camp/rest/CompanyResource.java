package ch.zuehlke.camp.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.zuehlke.camp.jpa.Company;
import ch.zuehlke.camp.jpa.Investor;

@Path("company")
@PersistenceContext(name = "ExampleDS")
@Transactional
@Produces(MediaType.APPLICATION_JSON)
public class CompanyResource {
	
	@PersistenceContext
	EntityManager em;
	
	@GET @Path("companies")
	public List<Company> getCompanies() {
		return em.createNamedQuery("findAllCompanies", Company.class).getResultList();
	}
	
	@GET @Path("investors")
	public List<Investor> getInvestors() {
		return em.createNamedQuery("findAllInvestors", Investor.class).getResultList();
	}
	
}
