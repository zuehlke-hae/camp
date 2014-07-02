package ch.zuehlke.camp.war.cdi;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;

@ApplicationScoped
@Default
public class SimpleGreeting implements Greeting, Serializable {

	private static final long serialVersionUID = -6260547564090029957L;

	public String greet(String name) {
		return "Hello " + name;
	}

}