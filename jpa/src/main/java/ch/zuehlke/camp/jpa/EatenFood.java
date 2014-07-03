package ch.zuehlke.camp.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class EatenFood {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Food food;
	
	private Double value;
	private Date date;
	
	public EatenFood() {
		
	}
	
	public EatenFood(Food food, Double value, Date date) {
		this.food = food;
		this.value = value;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public Food getFood() {
		return food;
	}

	public Double getValue() {
		return value;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String asString() {
		return "EatenFood [id=" + id + ", value=" + value + ", date=" + date + "]";
	}
}
