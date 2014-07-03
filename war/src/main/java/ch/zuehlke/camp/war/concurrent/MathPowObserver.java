package ch.zuehlke.camp.war.concurrent;

import javax.enterprise.event.Observes;

public class MathPowObserver {

	void onMathPowEvent(@Observes MathPowEvent event) {
		System.out.println("Observed MathPow with result " + event.getResult());
	}

}
