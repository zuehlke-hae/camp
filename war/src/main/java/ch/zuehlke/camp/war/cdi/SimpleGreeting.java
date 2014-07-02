package ch.zuehlke.camp.war.cdi;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SimpleGreeting implements Greeting, Serializable {

	private static final long serialVersionUID = -6260547564090029957L;

	public String greet(String name) {
		return "Hello " + name;
	}

}