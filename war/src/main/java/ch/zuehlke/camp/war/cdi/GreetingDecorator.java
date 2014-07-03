package ch.zuehlke.camp.war.cdi;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
@Priority(1300)
public class GreetingDecorator implements Greeting, Serializable {

	private static final long serialVersionUID = 3020645757567029251L;

	@Inject @Delegate @Any
	private Greeting innerGreeting;

	@Override
	public String greet(String name) {
		return innerGreeting.greet(name) + " Welcome!";
	}

}
