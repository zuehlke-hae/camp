package ch.zuehlke.camp.war.cdi;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class SimpleGreeting implements Greeting {

	public String greet(String name) {
		return "Hello " + name;
	}

}