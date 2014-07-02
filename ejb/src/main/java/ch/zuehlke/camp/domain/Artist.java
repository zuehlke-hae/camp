package ch.zuehlke.camp.domain;

import java.util.List;

public class Artist {

	private final int id;
	private final String name;
	private final List<String> members;
	
	public Artist(int id, String name, List<String> members) {
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
