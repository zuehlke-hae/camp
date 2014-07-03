package ch.zuehlke.camp.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FoodGroup {
	
	@Id
	private Long id;
	
	private String name;
	
	public FoodGroup()  {
		
	}
	
	public FoodGroup(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "FoodGroup [id=" + id + ", name=" + name + "]";
		
	}

}
