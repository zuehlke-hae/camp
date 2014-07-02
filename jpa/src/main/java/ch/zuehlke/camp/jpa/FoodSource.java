package ch.zuehlke.camp.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FoodSource {
	
	@Id
	private Long sourceId;
	
	private String name;
	
	public FoodSource()  {
		
	}
	
	public FoodSource(Long id, String name) {
		this.sourceId = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return sourceId;
	}
	
	@Override
	public String toString() {
		return "FoodSource [sourceId=" + sourceId + ", name=" + name + "]";
		
	}

}
