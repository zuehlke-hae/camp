package ch.zuehlke.camp.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FoodGroup {
	
	@Id
	private Long groupId;
	
	private String name;
	
	public FoodGroup()  {
		
	}
	
	public FoodGroup(Long id, String name) {
		this.groupId = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return groupId;
	}
	
	@Override
	public String toString() {
		return "FoodGroup [groupId=" + groupId + ", name=" + name + "]";
		
	}

}
