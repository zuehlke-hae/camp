package ch.zuehlke.camp.war.cdi;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

@SessionScoped
public class CounterService implements Serializable {

	private static final long serialVersionUID = -2670943955687663715L;

	private static int noOfInstances = 0;
	
	private final int instanceNo = ++noOfInstances;
	private int instanceCalled = 0;

	public String calculateIdentifier() {
		return "" + instanceNo + ":" + ++instanceCalled;
	}

}
