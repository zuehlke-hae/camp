package ch.zuehlke.camp.war.concurrent;

import java.util.concurrent.Callable;

public class MathPow implements Callable<Integer> {

	public MathPow(int base, int exp) {
		super();
		this.base = base;
		this.exp = exp;
	}

	private final int base;
	private final int exp;

	@Override
	public Integer call() throws Exception {
		Thread.sleep(5000);
		return Integer.valueOf((int)Math.pow(base, exp));
	}

}
