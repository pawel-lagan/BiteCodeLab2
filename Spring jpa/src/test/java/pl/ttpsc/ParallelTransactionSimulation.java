package pl.ttpsc;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import pl.ttpsc.springtraining.customer.Customer;

public abstract class ParallelTransactionSimulation {
	protected List<Customer> result = Collections.emptyList();
	protected AtomicReference<Exception> eT1 = new AtomicReference<>(null);
	protected AtomicReference<Exception> eT2 = new AtomicReference<>(null);

	public abstract void doT1() throws Exception;

	public abstract void doT2() throws Exception;

	public Exception getT1Exception() {
		return eT1.get();
	}

	public Exception getT2Exception() {
		return eT2.get();
	}

	public List<Customer> run(ParallelTransactionSimulation x) throws Exception {
		eT1.set(null);
		eT2.set(null);
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.submit(() -> {
			try {
				x.doT1();
			} catch (Exception e) {
				e.printStackTrace();
				eT1.set(e);
			}
		});
		executor.submit(() -> {
			try {
				x.doT2();
			} catch (Exception e) {
				e.printStackTrace();
				eT2.set(e);
			}
		});

		executor.shutdown();
		executor.awaitTermination(100000, TimeUnit.MILLISECONDS);

		return result;
	}
}
