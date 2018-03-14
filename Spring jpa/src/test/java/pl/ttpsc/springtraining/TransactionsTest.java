package pl.ttpsc.springtraining;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import pl.ttpsc.springtraining.customer.Customer;
import pl.ttpsc.springtraining.customer.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionsTest {

	private @Autowired CustomerRepository customerRepository;
	private @Autowired TestComponent component;
	private @Autowired TestComponent2 component2;

	@Component
	public static class TestComponent {
		private @Autowired TransactionTemplate transactionTemplate;
		private @Autowired PlatformTransactionManager transactionManager;
		private @Autowired CustomerRepository customerRepository;
		private @Autowired EntityManager entityManager;

		public void manualTransactionCommitExample() {
			transactionTemplate.execute((status) -> {
				Customer customer1 = Customer.newInstance("Tester", "Testowy", "tester.testowy@example.com", true);
				customerRepository.save(customer1);
				return null;
			});
		}

		public void manualTransactionRollbackExample() {
			transactionTemplate.execute((status) -> {
				Customer customer1 = Customer.newInstance("Tester", "Testowy", "tester.testowy@example.com", true);
				customerRepository.save(customer1);
				status.setRollbackOnly();
				return null;
			});
		}

		public void manualTransactionCommitExample2() throws Exception {
			TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

			Customer customer1 = Customer.newInstance("Tester", "Testowy", "tester.testowy@example.com", true);
			customerRepository.save(customer1);

			transactionManager.commit(transactionStatus);
		}

		public void manualTransactionRollbackExample2() throws Exception {
			TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

			Customer customer1 = Customer.newInstance("Tester", "Testowy", "tester.testowy@example.com", true);
			customerRepository.save(customer1);

			transactionManager.rollback(transactionStatus);
		}

		public void manualTransactionExample3() throws Exception {
			EntityTransaction transaction = entityManager.getTransaction();

			Customer customer1 = Customer.newInstance("Tester", "Testowy", "tester.testowy@example.com", true);
			customerRepository.save(customer1);

			transaction.rollback();
		}

		@Transactional(propagation = Propagation.REQUIRED)
		public void autoTransactionByAnnotation() {
			Customer customer1 = Customer.newInstance("Tester", "Testowy", "tester.testowy@example.com", true);
			customerRepository.save(customer1);

			throw new RuntimeException("rollback on exception");
		}

		@Transactional(propagation = Propagation.REQUIRED)
		public void manualTransactionExampleWithExceptionInTransaction() {
			Customer customer1 = Customer.newInstance("Tester", "Testowy", "tester.testowy@example.com", true);
			customerRepository.save(customer1);
			try {
				autoTransactionByAnnotation();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Component
	public static class TestComponent2 {
		private @Autowired CustomerRepository customerRepository;
		private @Autowired TestComponent component;

		@Transactional(propagation = Propagation.REQUIRED)
		public void manualTransactionExampleWithExceptionInTransaction() {
			Customer customer1 = Customer.newInstance("Tester", "Testowy", "tester.testowy@example.com", true);
			customerRepository.save(customer1);
			try {
				component.autoTransactionByAnnotation();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Test
	public void shouldPrintOneCustomer() {
		component.manualTransactionRollbackExample();

		Collection<Customer> result = customerRepository.findAllByActive(true);
		assertThat(result).hasSize(1);
	}

	@Test
	public void shouldPrintOneCustomer2() throws Exception {
		component.manualTransactionRollbackExample2();

		Collection<Customer> result = customerRepository.findAllByActive(true);
		assertThat(result).hasSize(1);
	}

	@Test(expected = IllegalStateException.class)
	public void shouldPrintOneCustomer3() throws Exception {
		component.manualTransactionExample3();

		Collection<Customer> result = customerRepository.findAllByActive(true);
		assertThat(result).hasSize(1);
	}

	@Test
	public void shouldPrintOneCustomer4() throws Exception {
		try {
			component.autoTransactionByAnnotation();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Collection<Customer> result = customerRepository.findAllByActive(true);
		assertThat(result).hasSize(1);
	}

	@Test(expected = TransactionSystemException.class)
	public void shouldPrintOneCustomer6() throws Exception {
		component2.manualTransactionExampleWithExceptionInTransaction();

		Collection<Customer> result = customerRepository.findAllByActive(true);
		assertThat(result).hasSize(1);
	}

	@Test
	@Transactional
	@Rollback
	public void shouldPrintThreeCustomers() throws Exception {
		component.manualTransactionExampleWithExceptionInTransaction();

		Collection<Customer> result = customerRepository.findAllByActive(true);
		assertThat(result).hasSize(3);
	}

	@Test
	@Transactional
	@Rollback
	public void shouldPrintTwoCustomers1() throws Exception {
		component.manualTransactionCommitExample();

		Collection<Customer> result = customerRepository.findAllByActive(true);
		assertThat(result).hasSize(2);
	}

	@Test
	@Transactional
	@Rollback
	public void shouldPrintTwoCustomers2() throws Exception {
		component.manualTransactionCommitExample2();

		Collection<Customer> result = customerRepository.findAllByActive(true);
		assertThat(result).hasSize(2);
	}

}
