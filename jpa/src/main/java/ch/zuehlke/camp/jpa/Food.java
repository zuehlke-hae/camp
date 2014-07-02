package ch.zuehlke.camp.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Food {
	
	@Id
	private Long foodId;
	
	@ManyToOne
    @JoinColumn(name="groupId")
	private FoodGroup foodGroup;
	
	@ManyToOne
    @JoinColumn(name="sourceId")
	private FoodGroup foodSource;
	
	private String name;
	
	public Food()  {
		
	}
	
	public Food(Long id, String name) {
		this.foodId = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return foodId;
	}
	
	@Override
	public String toString() {
		return "Food [foodId=" + foodId + ",foodGroup=" + foodGroup + ",foodSource=" + foodSource + ", name=" + name + "]";
		
	}

}
