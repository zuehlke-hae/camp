package ch.zuehlke.camp.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NutrimentName {
	
	@Id
	private Long id;
	
	private String symbol;
	private String unit;
	private String name;
	
	public NutrimentName()  {
		
	}
	
	public NutrimentName(Long id, String symbol, String unit, String name) {
		this.id = id;
		this.symbol = symbol;
		this.unit = unit;
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getUnit() {
		return unit;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}
	
	public String asString() {
		return "NutrimentName [id=" + id + ", name=" + name + ", symbol=" + symbol + ", unit=" + unit + "]";
	}
}
