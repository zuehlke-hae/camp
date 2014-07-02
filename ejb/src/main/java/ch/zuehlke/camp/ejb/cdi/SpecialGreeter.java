package ch.zuehlke.camp.ejb.cdi;

import javax.enterprise.context.SessionScoped;

import ch.zuehlke.camp.ejb.cdi.annotation.SpecialQualifier;

@SpecialQualifier
@SessionScoped
public class SpecialGreeter implements Greeter {

	private static final long serialVersionUID = 415451742542003054L;
	int id;
	
	public SpecialGreeter() {
		this.id = (int) (Math.floor(Math.random() * 100));
	}
	
	@Override
	public String getGreeting(String name) {
		return "This is a special greeting for you, " + name + "!";
	}

	@Override
	public int getId() {
		return this.id;
	}

}
