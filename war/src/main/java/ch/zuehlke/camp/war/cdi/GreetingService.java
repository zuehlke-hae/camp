package ch.zuehlke.camp.war.cdi;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;


@SessionScoped
public class GreetingService implements Serializable {

	private static final long serialVersionUID = -5805097361121982143L;
//	@Inject Greeting greeting;
	@Inject @Fancy Greeting greeting;

	public String greet(String name) {
		return greeting.greet(name);
	}

}