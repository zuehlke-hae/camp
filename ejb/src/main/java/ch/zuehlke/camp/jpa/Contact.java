package ch.zuehlke.camp.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

//@Entity
////@Table(name = "contact")
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //@NotNull
    ////@Column(name = "firstName")
    private String firstName;
    //@NotNull
    private String lastName;
    private String email;
    private String mobilePhone;
    private String homePhone;
    //@Temporal(javax.persistence.TemporalType.DATE)
    //@Past
    private Date birthday;
    
    public Contact() {
    	
    }
    
    public Contact(String firstName, String lastName) {
    	this.setFirstName(firstName);
    	this.setLastName(lastName);
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}