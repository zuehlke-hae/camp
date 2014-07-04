package ch.zuehlke.camp.jpa;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

import org.codehaus.jackson.annotate.JsonManagedReference;

@NamedQuery(name = "findAllCompanies", query = "SELECT c FROM Company c")
@Entity
public class Company {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String street;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="COMPANY_INVESTOR",
		      joinColumns={@JoinColumn(name="COMPANY_ID", referencedColumnName="ID")},
		      inverseJoinColumns={@JoinColumn(name="INVESTOR_ID", referencedColumnName="ID")})
	@JsonManagedReference
	private List<Investor> investors;

	public Company() {
	}

	public Company(String name, String street, List<Investor> investors) {
		this.setName(name);
		this.street = street;
		this.investors = investors;
	}

	public int getId() {
		return id;
	}

	public String getStreet() {
		return street;
	}

	public List<Investor> getInvestors() {
		return investors;
	}

	public void setInvestors(List<Investor> investors) {
		this.investors = investors;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
