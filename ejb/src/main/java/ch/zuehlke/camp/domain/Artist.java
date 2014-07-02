package ch.zuehlke.camp.domain;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;


public class Artist {

	private final int id;
	private final String name;
	private final List<String> members;
	
	@JsonCreator
	public Artist(
			@JsonProperty("id")
			int id, 
			@JsonProperty("name")
			String name, 
			@JsonProperty("members")
			List<String> members) {
		this.id = id;
		this.name = name;
		this.members = members;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public List<String> getMembers() {
		return members;
	}
	
	
}
