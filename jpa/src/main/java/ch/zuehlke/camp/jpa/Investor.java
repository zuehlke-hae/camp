package ch.zuehlke.camp.jpa;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

import org.codehaus.jackson.annotate.JsonBackReference;

@NamedQuery(name = "findAllInvestors", query = "SELECT i FROM Investor i")
@Entity
public class Investor {
	
	@Id @GeneratedValue
	private int id;
	private String name;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="COMPANY_INVESTOR",
		      joinColumns={@JoinColumn(name="INVESTOR_ID", referencedColumnName="ID")},
		      inverseJoinColumns={@JoinColumn(name="COMPANY_ID", referencedColumnName="ID")})
	@JsonBackReference
	private List<Company> companies;
	
	public Investor() {}

	public Investor(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Company> getCompanies() {
		return companies;
	}
	
	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
	
	public static TypedQuery<Investor> createfindInvestorByNameQuery(EntityManager em, String name) {
		return em.createQuery("SELECT i FROM Investor i WHERE i.name = :name", Investor.class)
				.setParameter("name",  name);
	}
	
}
