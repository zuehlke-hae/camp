package ch.zuehlke.camp.war.concurrent;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedTask;
import javax.enterprise.concurrent.ManagedTaskListener;

public class MathPow implements Callable<Integer>, ManagedTask, ManagedTaskListener {

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

	@Override
	public void taskAborted(Future<?> future, ManagedExecutorService executor,
			Object task, Throwable exception) {
		System.out.println("taskAborted");
	}

	@Override
	public void taskDone(Future<?> future, ManagedExecutorService executor,
			Object task, Throwable exception) {
		System.out.println("taskDone");
	}

	@Override
	public void taskStarting(Future<?> future, ManagedExecutorService executor,
			Object task) {
		System.out.println("taskStarting");
	}

	@Override
	public void taskSubmitted(Future<?> future,
			ManagedExecutorService executor, Object task) {
		System.out.println("taskSubmitted");
	}

	@Override
	public Map<String, String> getExecutionProperties() {
		return new TreeMap<String, String>();
	}

	@Override
	public ManagedTaskListener getManagedTaskListener() {
		return this;
	}

}
