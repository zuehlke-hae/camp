package ch.zuehlke.camp.service;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.google.common.collect.Lists;

import ch.zuehlke.camp.jpa.Company;
import ch.zuehlke.camp.jpa.Investor;

@Singleton
@Startup
public class SetupCompanies {
	
	@PersistenceContext
	EntityManager em;
	
	// Use RESTClient Add-On (Firefox, Chrome) for example
	@PostConstruct
	public void init() {
		em.persist(new Company("Nestle", "Rue de Bla 2", Lists.newArrayList(new Investor("BP"), new Investor("Shell"))));
		
		Investor shell = Investor.createfindInvestorByNameQuery(em, "Shell").getSingleResult();
		em.persist(new Company("Raiffeisen", "Vadianstrasse 7", Lists.newArrayList(shell)));
	}
}
