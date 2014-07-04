package ch.zuehlke.camp.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NutrimentSource {
	
	@Id
	private Long id;
	
	private String name;
	
	public NutrimentSource()  {
		
	}
	
	public NutrimentSource(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}
	
	public String asString() {
		return "NutrimentSource.java [id=" + id + ", name=" + name + "]";
	}
}
