package ch.zuehlke.camp.war.concurrent;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedTask;
import javax.enterprise.concurrent.ManagedTaskListener;
import javax.enterprise.event.Event;

public class MathPow implements Callable<Integer>, ManagedTask, ManagedTaskListener  {

	public MathPow(int base, int exp, Event<MathPowEvent> observableManager) {
		super();
		this.base = base;
		this.exp = exp;
		this.observableManager = observableManager;
	}

	private final int base;
	private final int exp;
	private final Event<MathPowEvent> observableManager;


	@Override
	public Integer call() throws Exception {
		Thread.sleep(5000);
		Integer result = Integer.valueOf((int)Math.pow(base, exp));
		observableManager.fire(new MathPowEvent(result));
		return result;
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
