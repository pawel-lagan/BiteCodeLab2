package pl.ttpsc.springtraining;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.ttpsc.ParallelTransactionSimulation;
import pl.ttpsc.springtraining.AdvancedLockingTest.PessimisticLockingDemo.TooLongTransaction;
import pl.ttpsc.springtraining.customer.Customer;
import pl.ttpsc.springtraining.product.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaRepositories
@EnableTransactionManagement
public class AdvancedLockingTest {
	private static final String TEST_OPTIMISTIC = "TEST_OPTIMISTIC";
	private static final String TEST_NONE = "TEST_NONE";
	private @Autowired OptimisticLockingDemo optimisticLocking;
	private @Autowired NoLockingDemo noLocking;
	private @Autowired PessimisticLockingDemo pessimisticLocking;
	private @Autowired EntityManager em;

	@Test
	@Transactional
	@Rollback
	public void shouldThrowOptimisticLockException() throws Exception {
		optimisticLocking.run(optimisticLocking);

		assertThat(optimisticLocking.getT1Exception()).isNull();
		assertThat(optimisticLocking.getT2Exception()).isInstanceOf(OptimisticLockException.class);
	}

	@Test
	@Transactional
	@Rollback
	public void shouldNotThrowAnyException() throws Exception {
		noLocking.run(noLocking);

		assertThat(noLocking.getT1Exception()).isNull();
		assertThat(noLocking.getT2Exception()).isNull();
	}

	@Test
	@Transactional
	@Rollback
	public void shouldLastLongerThan5000L() throws Exception {
		Customer customer = em.find(Customer.class, 1L);
		em.lock(customer, LockModeType.PESSIMISTIC_WRITE);

		pessimisticLocking.run(pessimisticLocking);

		assertThat(pessimisticLocking.getT1Exception()).isNull();
		assertThat(pessimisticLocking.getT2Exception()).isInstanceOf(TooLongTransaction.class);
	}

	@Component
	public static class OptimisticLockingDemo extends ParallelTransactionSimulation {
		private @Autowired EntityManager em;

		@Transactional(propagation = Propagation.REQUIRES_NEW)
		public void doT1() throws InterruptedException {
			Customer customer = em.find(Customer.class, 1L, LockModeType.OPTIMISTIC);

			customer.setFirstName(TEST_OPTIMISTIC);

			em.persist(customer);
			em.flush();

		}

		@Transactional(propagation = Propagation.REQUIRES_NEW)
		public void doT2() throws InterruptedException {
			Customer customer = em.find(Customer.class, 1L, LockModeType.OPTIMISTIC);

			Thread.sleep(2000);

			customer.setFirstName(TEST_OPTIMISTIC + "20000");

			em.persist(customer);
			em.flush();
		}
	}

	@Component
	public static class NoLockingDemo extends ParallelTransactionSimulation {
		private @Autowired EntityManager em;

		@Transactional(propagation = Propagation.REQUIRES_NEW)
		public void doT1() throws InterruptedException {
			Product customer = em.find(Product.class, 1L, LockModeType.NONE);

			customer.setName(TEST_NONE);

			em.persist(customer);
			em.flush();

		}

		@Transactional(propagation = Propagation.REQUIRES_NEW)
		public void doT2() throws InterruptedException {
			Product customer = em.find(Product.class, 1L, LockModeType.NONE);

			Thread.sleep(2000);

			customer.setName(TEST_NONE + "20000");

			em.persist(customer);
			em.flush();
		}
	}

	@Component
	public static class PessimisticLockingDemo extends ParallelTransactionSimulation {
		private @Autowired EntityManager em;

		@Transactional(propagation = Propagation.REQUIRES_NEW)
		public void doT1() throws InterruptedException {
			Product product = em.find(Product.class, 1L, LockModeType.PESSIMISTIC_WRITE);
			// em.lock(customer, LockModeType.PESSIMISTIC_WRITE);
			System.out.println("T1C:" + product);

			product.setName(TEST_NONE + "20000");

			Thread.sleep(7000);
			em.persist(product);
			System.out.println("T1C2:" + product);

		}

		@Transactional(propagation = Propagation.REQUIRES_NEW)
		public void doT2() throws Exception {
			long startTime = System.currentTimeMillis();

			Thread.sleep(2000);

			Product product = em.find(Product.class, 1L, LockModeType.PESSIMISTIC_WRITE);
			System.out.println("T2C:" + product);

			product.setName(TEST_NONE + "20000");

			em.persist(product);
			System.out.println("T2C2:" + product);
			long duration = (System.currentTimeMillis() - startTime);
			if (duration >= 5000L) {
				throw new TooLongTransaction();
			}
		}

		public static class TooLongTransaction extends Exception {

			private static final long serialVersionUID = -4721098142754788929L;

		}
	}
}
