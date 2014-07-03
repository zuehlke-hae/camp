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
	private static final String NEWLINE = "<br>";
	private static final String TAB = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	
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

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Food [id=" + id + ", name=" + name + NEWLINE);
		result.append(TAB + "foodGroup=" + foodGroup + NEWLINE);
		result.append(TAB + "foodSource=" + foodSource + NEWLINE);
		result.append(TAB + "nutrimentInfos=[" + NEWLINE);
		for (NutrimentInfo nutriment : nutrimentInfos) {
			result.append(TAB + TAB + nutriment.toString() + NEWLINE);
		}
		result.append(TAB + "]" + NEWLINE);
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
