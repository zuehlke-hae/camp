package ch.zuehlke.camp.ejb.cdi;

public class StandardGreeter implements Greeter {

	private static final long serialVersionUID = -5740740564025351266L;
	int id;
	
	public StandardGreeter() {
		this.id = (int) (Math.floor(Math.random() * 100));
	}
	
	@Override
	public String getGreeting(String name) {
		return "Hi there, " + name + "!";
	}

	@Override
	public int getId() {
		
		return this.id;
	}

}
