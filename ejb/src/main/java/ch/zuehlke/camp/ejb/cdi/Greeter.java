package ch.zuehlke.camp.ejb.cdi;

import java.io.Serializable;



public interface Greeter extends Serializable {

	String getGreeting(String name);

	int getId();
}
