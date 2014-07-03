package ch.zuehlke.camp.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Food {

	@Id
	private Long id;
	
	@ManyToOne
	private FoodGroup foodGroup;
	
	@ManyToOne
	private FoodSource foodSource;
	
	@OneToMany
	private Collection<NutrimentInfo> nutrimentInfos = new ArrayList<NutrimentInfo>();
	
	private String name;
	
	public Food() {
		
	}
	
	public Food(Long id, String name, FoodGroup group, FoodSource source) {
		this.id = id;
		this.name = name;
		this.foodGroup = group;
		this.foodSource = source;
	}

	public Food(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}
	
	public String asString() {
		StringBuilder result = new StringBuilder();
		result.append("Food [id=" + id + ", name=" + name);
		result.append("foodGroup=" + foodGroup);
		result.append("foodSource=" + foodSource);
		result.append("nutrimentInfos=[");
		for (NutrimentInfo nutriment : nutrimentInfos) {
			result.append(nutriment.asString());
		}
		result.append("]");
		result.append("]");
		return result.toString();
	}

	public FoodGroup getFoodGroup() {
		return foodGroup;
	}

	public FoodSource getFoodSource() {
		return foodSource;
	}

	public void addNutrimentInfo(NutrimentInfo info) {
		nutrimentInfos.add(info);
	}
	
	public void setNutrimentInfos(Collection<NutrimentInfo> nutrimentInfos) {
		this.nutrimentInfos = nutrimentInfos;
	}
	
	public Collection<NutrimentInfo> getNutrimentInfos() {
		return Collections.unmodifiableCollection(nutrimentInfos);
	}
}
