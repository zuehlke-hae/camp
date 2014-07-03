package ch.zuehlke.camp.war.concurrent;

public class MathPowEvent {

	private final Integer result;

	public MathPowEvent(Integer result) {
		super();
		this.result = result;
	}

	public Integer getResult() {
		return result;
	}

}
