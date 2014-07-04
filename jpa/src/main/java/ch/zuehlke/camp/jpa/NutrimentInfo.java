package ch.zuehlke.camp.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class NutrimentInfo {

	@Id
	@GeneratedValue
	private Long id;

	private Double value;
	
	@ManyToOne
	private NutrimentName nutrimentName;
	
	@ManyToOne
	private NutrimentSource nutrimentSource;
	
	public NutrimentInfo() {
		
	}

	public NutrimentInfo(NutrimentName nutrimentName, Double nT_VALUE_DB,
			NutrimentSource nutrimentSource) {
		this.nutrimentName = nutrimentName;
		this.value = nT_VALUE_DB;
		this.nutrimentSource = nutrimentSource;
	}

	public NutrimentName getNutrimentName() {
		return nutrimentName;
	}

	public Double getValue() {
		return value;
	}

	public NutrimentSource getNutrimentSource() {
		return nutrimentSource;
	}

	public Long getId() {
		return id;
	}

	public String asString() {
		return "NutrimentInfo [id=" + id + ", value=" + value + ", nutrimentName=" + nutrimentName + "]";
	}
}
