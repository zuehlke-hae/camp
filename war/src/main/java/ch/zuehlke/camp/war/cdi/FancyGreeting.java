package ch.zuehlke.camp.war.cdi;

import javax.enterprise.context.ApplicationScoped;

@Fancy
@ApplicationScoped
public class FancyGreeting implements Greeting {

	public String greet(String name) {
		return "Nice to meet you, hello " + name;
	}

}