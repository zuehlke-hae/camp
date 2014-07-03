package ch.zuehlke.camp.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Food {
	
	@Id
	private Long foodId;
	
	@ManyToOne
	private FoodGroup foodGroup;
	
	@ManyToOne
	private FoodSource foodSource;
	
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
		return "Food [foodId=" + foodId + ",foodGroup=" + getFoodGroup() + ",foodSource=" + getFoodSource() + ", name=" + name + "]";
		
	}

	public FoodGroup getFoodGroup() {
		return foodGroup;
	}

	public void setFoodGroup(FoodGroup foodGroup) {
		this.foodGroup = foodGroup;
	}

	public FoodSource getFoodSource() {
		return foodSource;
	}

	public void setFoodSource(FoodSource foodSource) {
		this.foodSource = foodSource;
	}

}
